/**
 * Copyright 2016 Green Energy Corp.
 *
 * Licensed to Green Energy Corp (www.greenenergycorp.com) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. Green Energy
 * Corp licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.greenenergycorp.openfmb.mapping.mqtt;

import com.greenenergycorp.openfmb.mapping.adapter.PayloadObserver;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Manages the MQTT connection. Handles re-connections, subscribing and resubscribing, and the threading of sending
 * messages.
 */
public class MqttAdapterManager {
    private final static Logger logger = LoggerFactory.getLogger(MqttAdapterManager.class);

    private final MqttConfiguration mqttConfig;
    private int qos;

    private AtomicReference<MqttClient> mqttClientRef = new AtomicReference<MqttClient>();
    private final BlockingQueue<Outgoing> queue = new LinkedBlockingQueue<Outgoing>();
    private Outgoing outstanding = null;

    private final Object controlMutex = new Object();
    private final List<String> topicSubscriptions = new ArrayList<String>();
    private final MqttTopicTrie<PayloadObserver> subscriptionTrie = new MqttTopicTrie<PayloadObserver>();

    private final MqttObserver messageObserver = new MqttObserver() {
        public void publish(final byte[] payload, final String topic) {
            queue.add(new Outgoing(payload, topic));
        }
    };

    /**
     * @param mqttConfig MQTT connection configuration
     * @param qos Default QOS level.
     */
    public MqttAdapterManager(MqttConfiguration mqttConfig, int qos) {
        this.mqttConfig = mqttConfig;
        this.qos = qos;
    }

    /**
     * Subscribe to a set of topics with an associated payload observer.
     *
     * @param controlObservers Map of topics to payload observers.
     */
    public void subscribe(Map<String, PayloadObserver> controlObservers) {
        synchronized (controlMutex) {
            for (Map.Entry<String, PayloadObserver> entry: controlObservers.entrySet()) {
                topicSubscriptions.add(entry.getKey());
                subscriptionTrie.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Run main reconnection/messaging loop.
     */
    public void run() {
        final Thread thread = Thread.currentThread();
        while (true) {

            if (mqttClientRef.get() == null) {
                try {
                    logger.info("Attempting MQTT connection...");
                    final MqttClient client = MqttConnectionFactory.connect(mqttConfig, new MqttCallbackImpl(thread));
                    logger.info("Connected to MQTT broker.");
                    mqttClientRef.set(client);
                    for (String topic: topicSubscriptions) {
                        client.subscribe(topic);
                    }

                } catch (Exception ex) {
                    logger.warn("Could not connect to MQTT broker: " + ex.getMessage());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException iex) {
                    }
                }
            } else {

                try {
                    final Outgoing outgoing;
                    if (outstanding != null) {
                        outgoing = outstanding;
                        outstanding = null;
                    } else {
                        outgoing = queue.take();
                    }
                    if (outgoing != null) {
                        final MqttClient client = mqttClientRef.get();
                        if (client == null) {
                            outstanding = outgoing;
                        } else {
                            try {
                                attemptPublish(client, outgoing);
                            } catch (Throwable ex) {
                                logger.warn("Could not publish MQTT message: " + ex);
                                mqttClientRef.set(null);
                                outstanding = outgoing;
                                Thread.sleep(2000);
                            }
                        }
                    }
                } catch (InterruptedException iex) {
                }
            }
        }
    }

    private void attemptPublish(final MqttClient client, final Outgoing outgoing) throws MqttException {
        logger.debug("Attempting to publish to: " + outgoing.getTopic());
        client.publish(outgoing.getTopic(), outgoing.getPayload(), qos, false);
    }

    private class MqttCallbackImpl implements MqttCallback {
        private final Thread thread;

        public MqttCallbackImpl(Thread thread) {
            this.thread = thread;
        }

        public void connectionLost(Throwable throwable) {
            logger.warn("Lost MQTT connection: " + throwable.getMessage());
            mqttClientRef.set(null);
            thread.interrupt();
        }

        public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
            logger.debug("arrived for topic: " + topic);
            synchronized (controlMutex) {
                final List<PayloadObserver> observers = subscriptionTrie.lookup(topic);
                for (PayloadObserver observer: observers) {
                    try {
                        observer.handle(mqttMessage.getPayload());
                    } catch (Throwable ex) {
                        logger.error("Exception in subscription handler: " + ex);
                    }
                }
            }
        }

        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        }
    }


    public MqttObserver getMessageObserver() {
        return messageObserver;
    }

    private static class Outgoing {
        private final byte[] payload;
        private final String topic;

        public Outgoing(byte[] payload, String topic) {
            this.payload = payload;
            this.topic = topic;
        }

        public byte[] getPayload() {
            return payload;
        }

        public String getTopic() {
            return topic;
        }
    }

}

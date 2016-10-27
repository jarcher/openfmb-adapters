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

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * Helper factory for loading configuration from a file and building an Eclipse Paho client.
 */
public class MqttConnectionFactory {

    public static MqttConnectOptions build(final MqttConfiguration mqttConfig) throws Exception {

        final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();

        if (mqttConfig.getBrokerCrt() != null) {
            if (mqttConfig.getClientCrt() != null)  {
                final SSLSocketFactory socketFactory = SslUtil.getSslSocketFactory(
                        mqttConfig.getBrokerCrt(),
                        mqttConfig.getClientCrt(),
                        mqttConfig.getKeyStore(),
                        mqttConfig.getKeyStorePassword()
                );
                mqttConnectOptions.setSocketFactory(socketFactory);
            } else {
                final SSLContext socketContext = SslUtil.getServerSslContext(mqttConfig.getBrokerCrt());
                mqttConnectOptions.setSocketFactory(socketContext.getSocketFactory());
            }
        }

        mqttConnectOptions.setCleanSession(true);
        if (mqttConfig.getUser() != null) {
            mqttConnectOptions.setUserName(mqttConfig.getUser());
        }
        if (mqttConfig.getPassword() != null) {
            mqttConnectOptions.setPassword(mqttConfig.getPassword().toCharArray());
        }



        return mqttConnectOptions;
    }

    /**
     * Provide a callback implementation and connect to the device.
     *
     * @param mqttConfig Configuration for client
     * @param callback Callback that specifies methods for disconnect and message arrival
     * @return Connected MQTT client.
     * @throws Exception
     */
    public static MqttClient connect(final MqttConfiguration mqttConfig, final MqttCallback callback) throws Exception {

        final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();

        if (mqttConfig.getBrokerCrt() != null) {
            if (mqttConfig.getClientCrt() != null)  {
                final SSLSocketFactory socketFactory = SslUtil.getSslSocketFactory(
                        mqttConfig.getBrokerCrt(),
                        mqttConfig.getClientCrt(),
                        mqttConfig.getKeyStore(),
                        mqttConfig.getKeyStorePassword()
                );
                mqttConnectOptions.setSocketFactory(socketFactory);
            } else {
                final SSLContext socketContext = SslUtil.getServerSslContext(mqttConfig.getBrokerCrt());
                mqttConnectOptions.setSocketFactory(socketContext.getSocketFactory());
            }
        }

        mqttConnectOptions.setCleanSession(true);
        if (mqttConfig.getUser() != null) {
            mqttConnectOptions.setUserName(mqttConfig.getUser());
        }
        if (mqttConfig.getPassword() != null) {
            mqttConnectOptions.setPassword(mqttConfig.getPassword().toCharArray());
        }

        String clientId = "";
        if (mqttConfig.getUser() != null) {
            clientId = mqttConfig.getUser() + System.currentTimeMillis();
        } else {
            clientId = "" + System.currentTimeMillis();
        }

        final MqttClient mqttClient = new MqttClient(mqttConfig.getUri(), clientId);

        if (callback != null) {
            mqttClient.setCallback(callback);
        }

        mqttClient.connect(mqttConnectOptions);

        return mqttClient;
    }

    /**
     * Build an MQTT client for publishing only (no callback provided).
     *
     * @param mqttConfig Configuration for client
     * @return
     * @throws Exception
     */
    public static MqttClient connect(final MqttConfiguration mqttConfig) throws Exception {
        return connect(mqttConfig, null);
    }
}

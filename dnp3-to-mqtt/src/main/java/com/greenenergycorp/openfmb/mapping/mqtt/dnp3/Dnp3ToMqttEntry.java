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
package com.greenenergycorp.openfmb.mapping.mqtt.dnp3;

import com.greenenergycorp.openfmb.mapping.adapter.*;
import com.greenenergycorp.openfmb.mapping.config.xml.DNP3Master;
import com.greenenergycorp.openfmb.mapping.config.xml.OpenFMBDNP3;
import com.greenenergycorp.openfmb.mapping.data.xml.OpenFmbXmlMarshaller;
import com.greenenergycorp.openfmb.mapping.data.xml.XmlDataFormatPublishersLoader;
import com.greenenergycorp.openfmb.mapping.data.xml.XmlDataFormatSubscribersLoader;
import com.greenenergycorp.openfmb.mapping.dnp3.Dnp3AdapterManager;
import com.greenenergycorp.openfmb.mapping.dnp3.XmlConfigReader;
import com.greenenergycorp.openfmb.mapping.model.ControlHandlerMapping;
import com.greenenergycorp.openfmb.mapping.model.ControlMapping;
import com.greenenergycorp.openfmb.mapping.mqtt.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dnp3ToMqttEntry {

    public static void main(String[] args) throws Throwable {

        if (args.length < 1) {
            System.err.println("Must include input file");
            System.exit(1);
        }

        final String filename = args[0];

        // Build the xml marshaller.
        final XmlConfigReader.DnpXmlMarshaller marshaller = XmlConfigReader.DnpXmlMarshaller.build();

        // Parse the XML into a JAXB object.
        final OpenFMBDNP3 xmlConfig = marshaller.unmarshal(new FileInputStream(filename));

        // Find MQTT configuration properties path.
        final String mqttConfigPath = System.getProperty("config.mqtt.path", "mqtt.properties");

        // Load the MQTT configuration data from a file.
        final MqttConfiguration mqttConfiguration = MqttConfiguration.fromFile(mqttConfigPath);

        // Use a topic mapping specific to the OpenFMB.
        final SimpleTopicMapping simpleTopicMapping = new SimpleTopicMapping();

        // Instantiate the MQTT connection manager.
        final MqttAdapterManager mqttAdapterManager = new MqttAdapterManager(mqttConfiguration, 0);

        // Get publishing interface to MQTT client.
        final MqttObserver mqttObserver = mqttAdapterManager.getMessageObserver();

        // Wrap publishing interface for OpenFMB message objects.
        final MessageObserver messageObserver = new MessageObserverAdapter(mqttObserver, simpleTopicMapping);

        // Instantiate JAXB OpenFMB xml data format library.
        final OpenFmbXmlMarshaller openFmbXmlMarshaller = new OpenFmbXmlMarshaller();

        // Load publishing adapters for the OpenFMB XML data format.
        final Map<String, DeviceAdapter> adapterMap = XmlDataFormatPublishersLoader.loadPublishers(xmlConfig.getPublishers(), openFmbXmlMarshaller, messageObserver);

        // Update manager that takes data and pushes to messaging adapters.
        final DeviceUpdateManager updateManager = new DeviceUpdateManager(adapterMap);

        // Threading model manager for data updates.
        final AdapterManager adapterManager = new AdapterManager(updateManager);

        // Manager for instances of DNP3 masters.
        final Dnp3AdapterManager manager = new Dnp3AdapterManager(adapterManager.getObserver());

        final List<ControlHandlerMapping> controlHandlerMappings = new ArrayList<ControlHandlerMapping>();

        // Add a DNP3 master for every entry in the XML configuration, and collect the control handler mapping.
        for (DNP3Master masterXml : xmlConfig.getDNP3Masters().getDNP3Master()) {
            final ControlHandlerMapping controlHandlerMapping = manager.addAdapter(masterXml);
            controlHandlerMappings.add(controlHandlerMapping);
        }

        // Combine all control handler mappings to a global map.
        final ControlMapping controlMapping = ControlMapping.combine(controlHandlerMappings);

        final Map<MessageIdentifier, PayloadObserver> controlObservers = new HashMap<MessageIdentifier, PayloadObserver>();

        // Load subscribing adapters for the OpenFMB XML data format.
        XmlDataFormatSubscribersLoader.loadSubscribers(xmlConfig.getSubscribers(), openFmbXmlMarshaller, controlMapping, controlObservers);

        // Map OpenFMB message identifiers to topic subscriptions.
        final Map<String, PayloadObserver> topicControlObservers = new HashMap<String, PayloadObserver>();
        for (Map.Entry<MessageIdentifier, PayloadObserver> entry: controlObservers.entrySet()) {
            final String messageType = entry.getKey().getMessageType();
            final String messageId = entry.getKey().getMessageId();
            topicControlObservers.put(simpleTopicMapping.toTopic(messageType, messageId), entry.getValue());
        }

        // Subscribe to MQTT topics.
        mqttAdapterManager.subscribe(topicControlObservers);

        final Thread mqttThread = new Thread(new Runnable() {
            public void run() {
                mqttAdapterManager.run();
            }
        }, "mqtt publisher");

        mqttThread.start();

        adapterManager.run();

        System.err.println("Exiting...");

        manager.shutdown();
    }
}

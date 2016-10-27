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

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Configuration struct to configure MQTT clients from a properties file.
 */
public class MqttConfiguration {
    private final String uri;
    private final String user;
    private final String password;
    private final String brokerCrt;
    private final String clientCrt;
    private final String keyStore;
    private final String keyStorePassword;

    public MqttConfiguration(String uri, String user, String password, String brokerCrt, String clientCrt, String keyStore, String keyStorePassword) {
        this.uri = uri;
        this.user = user;
        this.password = password;
        this.brokerCrt = brokerCrt;
        this.clientCrt = clientCrt;
        this.keyStore = keyStore;
        this.keyStorePassword = keyStorePassword;
    }

    public String getUri() {
        return uri;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getBrokerCrt() {
        return brokerCrt;
    }

    public String getClientCrt() {
        return clientCrt;
    }

    public String getKeyStore() {
        return keyStore;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    private static String loadProp(Properties properties, String key) throws Exception {
        String value = properties.getProperty(key);
        return value;
    }

    private static int loadIntProp(Properties properties, String key) throws Exception {
        return Integer.parseInt(loadProp(properties, key));
    }

    public static MqttConfiguration fromFile(String file) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        String uri = loadProp(properties, "mqtt.uri");
        String user = loadProp(properties, "mqtt.user");
        String password = loadProp(properties, "mqtt.password");
        String brokerCrt = loadProp(properties, "mqtt.broker_crt");
        String clientCrt = loadProp(properties, "mqtt.client_crt");
        String keyStore = loadProp(properties, "mqtt.key_store");
        String keyStorePassword = loadProp(properties, "mqtt.key_store_pw");;

        return new MqttConfiguration(uri, user, password, brokerCrt, clientCrt, keyStore, keyStorePassword);
    }
}

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

import com.greenenergycorp.openfmb.mapping.adapter.MessageObserver;

public class MessageObserverAdapter implements MessageObserver {
    private final MqttObserver observer;
    private final MqttTopicMapping topicMapping;

    public MessageObserverAdapter(MqttObserver observer, MqttTopicMapping topicMapping) {
        this.observer = observer;
        this.topicMapping = topicMapping;
    }

    public void publish(byte[] payload, String messageType, String messageId) {
        observer.publish(payload, topicMapping.toTopic(messageType, messageId));
    }
}

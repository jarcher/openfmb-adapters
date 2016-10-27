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
package com.greenenergycorp.openfmb.mapping.adapter;

import com.greenenergycorp.openfmb.mapping.model.MeasValue;
import com.greenenergycorp.openfmb.mapping.model.ReadingId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Implements the OpenFMB device adapter handler interface, uses a MessageConverter to provide a marshaled message payload
 * to a MessageObserver interface.
 */
public class SimpleMessageDeviceAdapter implements DeviceAdapter {
    private final static Logger logger = LoggerFactory.getLogger(SimpleMessageDeviceAdapter.class);

    private final MessageObserver observer;
    private final MessageConverter converter;
    private final String messageType;
    private final String messageId;

    /**
     *
     * @param observer Message observer used to publish marshaled messages.
     * @param converter Converter used to translate reading and struct values to message payloads.
     * @param messageType OpenFMB message type.
     * @param messageId ID of an OpenFMB message instance.
     */
    public SimpleMessageDeviceAdapter(MessageObserver observer, MessageConverter converter, String messageType, String messageId) {
        this.observer = observer;
        this.converter = converter;
        this.messageType = messageType;
        this.messageId = messageId;
    }

    /**
     * Convert to message and push to message observer.
     *
     * @param readingValues Map of reading IDs to value updates.
     * @param keyValues Map of key IDs to value updates.
     */
    public void update(Map<ReadingId, MeasValue> readingValues, Map<String, MeasValue> keyValues) {
        byte[] resultPayload = null;
        try {
            resultPayload = converter.convert(readingValues, keyValues);
        } catch (Exception ex) {
            logger.warn("Error converting to message payload: " + ex.getMessage());
        }
        if (resultPayload != null) {
            observer.publish(resultPayload, messageType, messageId);
        }
    }
}

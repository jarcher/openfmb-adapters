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
package com.greenenergycorp.openfmb.mapping.data.xml;

import com.greenenergycorp.openfmb.mapping.adapter.PayloadObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements a payload observer to convert a byte array into a JAXB object and forward to a handler.
 */
public class PayloadUnmarshaller implements PayloadObserver {
    private final static Logger logger = LoggerFactory.getLogger(PayloadUnmarshaller.class);

    final OpenFmbXmlMarshaller marshaller;
    final MessageObjectObserver observer;

    /**
     * @param marshaller JAXB marshaller for OpenFMB XML.
     * @param observer Message object observer.
     */
    public PayloadUnmarshaller(OpenFmbXmlMarshaller marshaller, MessageObjectObserver observer) {
        this.marshaller = marshaller;
        this.observer = observer;
    }

    /**
     * Handle payload, unmarshal to JAXB object, and forward.
     *
     * @param payload Byte array message.
     */
    public void handle(byte[] payload) {
        try {
            final Object result = marshaller.unmarshal(payload);
            if (result != null) {
                observer.handle(result);
            }
        } catch (Throwable ex) {
            logger.warn("Error unmarshalling: " + ex.getMessage());
        }
    }
}

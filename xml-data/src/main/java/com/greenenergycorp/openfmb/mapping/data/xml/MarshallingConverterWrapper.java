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

import com.greenenergycorp.openfmb.mapping.adapter.MessageConverter;
import com.greenenergycorp.openfmb.mapping.model.MeasValue;
import com.greenenergycorp.openfmb.mapping.model.ReadingId;

import java.util.Map;

/**
 * Adapts a JaxbObjectConverter to a MessageConverter by performing the marshalling to a byte array.
 */
public class MarshallingConverterWrapper implements MessageConverter {
    private final JaxbObjectConverter jaxbObjectConverter;
    private final OpenFmbXmlMarshaller marshaller;

    /**
     * @param jaxbObjectConverter Converter that produces JAXB objects.
     * @param marshaller Marshaller to turn JAXB objects into byte arrays.
     */
    public MarshallingConverterWrapper(JaxbObjectConverter jaxbObjectConverter, OpenFmbXmlMarshaller marshaller) {
        this.jaxbObjectConverter = jaxbObjectConverter;
        this.marshaller = marshaller;
    }

    /**
     * Adapts a JaxbObjectConverter to a MessageConverter by performing the marshalling to a byte array.
     *
     * @param readingValues OpenFMB readings
     * @param keyValues OpenFMB struct field values
     * @return Byte array payload
     * @throws Exception
     */
    public byte[] convert(Map<ReadingId, MeasValue> readingValues, Map<String, MeasValue> keyValues) throws Exception {
        final Object result = jaxbObjectConverter.convert(readingValues, keyValues);
        if (result != null) {
            return marshaller.marshal(result);
        } else {
            return null;
        }
    }
}

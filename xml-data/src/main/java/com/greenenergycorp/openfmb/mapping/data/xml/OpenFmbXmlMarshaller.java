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

import com.greenenergycorp.openfmb.xml.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Marshals/unmarshals OpenFMB JAXB message objects.
 */
public class OpenFmbXmlMarshaller {

    private final JAXBContext context;

    public OpenFmbXmlMarshaller() throws JAXBException {
        context = JAXBContext.newInstance(ObjectFactory.class);
    }

    /**
     *  Marshal OpenFMB JAXB message object.
     *
     * @param object JAXB object.
     * @return Byte array payload.
     * @throws JAXBException
     */
    public byte[] marshal(Object object) throws JAXBException {
        final Marshaller marshaller = context.createMarshaller();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(object, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * Unmarhal OpenFMB JAXB message object.
     *
     * @param data Message byte array.
     * @return JAXB object.
     * @throws JAXBException
     */
    public Object unmarshal(byte[] data) throws JAXBException {
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        return unmarshaller.unmarshal(inputStream);
    }
}

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

/**
 * Handler for byte payload messages distinguished by their OpenFMB identification.
 */
public interface MessageObserver {

    /**
     * Handles payload messages distinguished by their OpenFMB identification.
     *
     * @param payload Byte array payload of a message.
     * @param messageType OpenFMB message type.
     * @param messageId ID of an OpenFMB message instance.
     */
    void publish(final byte[] payload, final String messageType, final String messageId);
}

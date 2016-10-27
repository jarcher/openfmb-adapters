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
 * Identifier that combines the OpenFMB message type with the logical device ID of a particular message instance.
 */
public class MessageIdentifier {
    private final String messageType;
    private final String messageId;

    /**
     *
     * @param messageType OpenFMB message type
     * @param messageId Logical device ID of message instance
     */
    public MessageIdentifier(String messageType, String messageId) {
        this.messageType = messageType;
        this.messageId = messageId;
    }

    /**
     * @return OpenFMB message type
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @return Logical device ID of message instance
     */
    public String getMessageId() {
        return messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageIdentifier that = (MessageIdentifier) o;

        if (messageType != null ? !messageType.equals(that.messageType) : that.messageType != null) return false;
        return !(messageId != null ? !messageId.equals(that.messageId) : that.messageId != null);

    }

    @Override
    public int hashCode() {
        int result = messageType != null ? messageType.hashCode() : 0;
        result = 31 * result + (messageId != null ? messageId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageIdentifier{" +
                "messageType='" + messageType + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}

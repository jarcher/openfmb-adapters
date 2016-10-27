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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data structure that maps topic subscription to objects associated with those topics.
 *
 * @param <T> Object type.
 */
public class MqttTopicTrie <T> {

    private final Map<String, Node<T>> nodes = new HashMap<String, Node<T>>();
    private final List<T> hashNodes = new ArrayList<T>();

    /**
     * Lookup objects for subscriptions matching arriving MQTT topic.
     *
     * @param messageTopic MQTT topic of received message.
     * @return List of objects for subscriptions mapping this topic.
     */
    public List<T> lookup(final String messageTopic) {
        final List<T> results = new ArrayList<T>();

        results.addAll(hashNodes);

        final String[] parts = messageTopic.split("/");

        if (parts.length > 0) {
            final String trimmed = parts[0].trim();
            final Node<T> partMatch = nodes.get(trimmed);
            if (partMatch != null) {
                nodeLookup(results, partMatch, parts, 1);
            }
        }

        return results;
    }

    private static <T> void nodeLookup(final List<T> results, final Node<T> node, final String[] parts, final int nextIndex) {

        if (nextIndex >= parts.length ) {
            results.addAll(node.exactMatches);
        } else {
            results.addAll(node.hashMatches);

            final String nextPart = parts[nextIndex].trim();
            final Node<T> subNode = node.nodes.get(nextPart);
            if (subNode != null) {
                nodeLookup(results, subNode, parts, nextIndex + 1);
            }
        }
    }

    /**
     * Associate an object with an MQTT topic subscription.
     *
     * @param topicSubscription MQTT topic subscription.
     * @param obj Object to be associated.
     */
    public void put(final String topicSubscription, final T obj) {

        final String[] parts = topicSubscription.split("/");

        if (parts.length > 0) {
            final String trimmed = parts[0].trim();
            if (trimmed.equals("#")) {
                hashNodes.add(obj);
            } else {
                final Node<T> existing = nodes.get(trimmed);
                if (existing != null) {
                    put(parts, 1, existing, obj);
                } else {
                    final Node<T> emptyNode = new Node<T>();
                    nodes.put(trimmed, emptyNode);
                    put(parts, 1, emptyNode, obj);
                }
            }
        }
    }

    private static <T> void put(final String[] parts, final int nextIndex, Node<T> node, T obj) {
        if (nextIndex >= parts.length) {
            node.exactMatches.add(obj);
        } else {
            final String nextPart = parts[nextIndex].trim();
            if (nextPart.equals("#")) {
                node.hashMatches.add(obj);
            } else {
                final Node<T> existingSubNode = node.nodes.get(nextPart);
                if (existingSubNode != null) {
                    put(parts, nextIndex + 1, existingSubNode, obj);
                } else {
                    final Node<T> emptyNode = new Node<T>();
                    node.nodes.put(nextPart, emptyNode);
                    put(parts, nextIndex + 1, emptyNode, obj);
                }
            }
        }
    }


    static class Node<T> {
        List<T> exactMatches = new ArrayList<T>();
        List<T> hashMatches = new ArrayList<T>();
        Map<String, Node<T>> nodes = new HashMap<String, Node<T>>();
    }
}

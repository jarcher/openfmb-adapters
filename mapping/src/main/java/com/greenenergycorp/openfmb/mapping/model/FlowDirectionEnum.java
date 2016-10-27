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
package com.greenenergycorp.openfmb.mapping.model;

/**
 * OpenFMB flow direction
 */
public enum FlowDirectionEnum {

    FORWARD("forward"),
    LAGGING("lagging"),
    LEADING("leading"),
    NET("net"),
    NO_DIRECTION("noDirection"),
    Q_1_MINUS_Q_4("q1minusQ4"),
    Q_1_PLUS_Q_2("q1plusQ2"),
    Q_1_PLUS_Q_3("q1plusQ3"),
    Q_1_PLUS_Q_4("q1plusQ4"),
    Q_2_MINUS_Q_3("q2minusQ3"),
    Q_2_PLUS_Q_3("q2plusQ3"),
    Q_2_PLUS_Q_4("q2plusQ4"),
    Q_3_MINUS_Q_2("q3minusQ2"),
    Q_3_PLUS_Q_4("q3plusQ4"),
    QUADRANT_1("quadrant1"),
    QUADRANT_2("quadrant2"),
    QUADRANT_3("quadrant3"),
    QUADRANT_4("quadrant4"),
    REVERSE("reverse"),
    TOTAL("total"),
    TOTAL_BY_PHASE("totalByPhase");


    private final String value;

    FlowDirectionEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FlowDirectionEnum fromValue(String v) {
        for (FlowDirectionEnum c: FlowDirectionEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

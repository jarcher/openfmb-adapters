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
 * OpenFMB phase code
 */
public enum PhaseCodeEnum {
    A("A"),
    AB("AB"),
    ABC("ABC"),
    ABCN("ABCN"),
    ABN("ABN"),
    AC("AC"),
    ACN("ACN"),
    AN("AN"),
    B("B"),
    BC("BC"),
    BCN("BCN"),
    BN("BN"),
    C("C"),
    CN("CN"),
    N("N"),
    NO_PHASE("noPhase"),
    S_1("s1"),
    S_12("s12"),
    S_12_N("s12N"),
    S_1_N("s1N"),
    S_2("s2"),
    S_2_N("s2N");

    private final String value;

    PhaseCodeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PhaseCodeEnum fromValue(String v) {
        for (PhaseCodeEnum c: PhaseCodeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

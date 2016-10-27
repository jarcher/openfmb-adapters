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
 * OpenFMB unit
 */
public enum UnitSymbolEnum {
    AMP("Amp"),
    DEG("deg"),
    DEG_C("degC"),
    DEG_F("degF"),
    FARAD("Farad"),
    GRAM("gram"),
    HOUR("hour"),
    HENRY("Henry"),
    HZ("Hz"),
    JOULE("Joule"),
    METER("meter"),
    M_2("m2"),
    M_3("m3"),
    MIN("min"),
    MPH("mph"),
    NEWTON("Newton"),
    NO_UNIT("noUnit"),
    OHM("ohm"),
    PA("Pa"),
    RAD("rad"),
    SIEMENS("Siemens"),
    SEC("sec"),
    V("V"),
    VA("VA"),
    V_AH("VAh"),
    V_AR("VAr"),
    V_ARH("VArh"),
    W("W"),
    WH("Wh"),
    W_PER_VA("wPerVA"),
    W_PER_M_2("WPerM2");

    private final String value;

    UnitSymbolEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnitSymbolEnum fromValue(String v) {
        for (UnitSymbolEnum c: UnitSymbolEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

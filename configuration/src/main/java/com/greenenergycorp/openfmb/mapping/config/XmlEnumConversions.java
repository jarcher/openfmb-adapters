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
package com.greenenergycorp.openfmb.mapping.config;

import com.greenenergycorp.openfmb.mapping.config.xml.FlowDirection;
import com.greenenergycorp.openfmb.mapping.config.xml.PhaseCode;
import com.greenenergycorp.openfmb.mapping.config.xml.UnitMultiplier;
import com.greenenergycorp.openfmb.mapping.config.xml.UnitSymbol;
import com.greenenergycorp.openfmb.mapping.model.FlowDirectionEnum;
import com.greenenergycorp.openfmb.mapping.model.PhaseCodeEnum;
import com.greenenergycorp.openfmb.mapping.model.UnitMultiplierEnum;
import com.greenenergycorp.openfmb.mapping.model.UnitSymbolEnum;

public class XmlEnumConversions {

    /**
     * Converts XML units to OpenFMB Java units.
     * @param xml XML type.
     * @return OpenFMB Java type.
     */
    public static UnitSymbolEnum convertFromXml(UnitSymbol xml) {

        if (xml == UnitSymbol.AMP) {
            return UnitSymbolEnum.AMP;
        } else if (xml == UnitSymbol.DEG) {
            return UnitSymbolEnum.DEG;
        } else if (xml == UnitSymbol.DEG_C) {
            return UnitSymbolEnum.DEG_C;
        } else if (xml == UnitSymbol.FARAD) {
            return UnitSymbolEnum.FARAD;
        } else if (xml == UnitSymbol.GRAM) {
            return UnitSymbolEnum.GRAM;
        } else if (xml == UnitSymbol.HOUR) {
            return UnitSymbolEnum.HOUR;
        } else if (xml == UnitSymbol.HENRY) {
            return UnitSymbolEnum.HENRY;
        } else if (xml == UnitSymbol.HZ) {
            return UnitSymbolEnum.HZ;
        } else if (xml == UnitSymbol.JOULE) {
            return UnitSymbolEnum.JOULE;
        } else if (xml == UnitSymbol.METER) {
            return UnitSymbolEnum.METER;
        } else if (xml == UnitSymbol.M_2) {
            return UnitSymbolEnum.M_2;
        } else if (xml == UnitSymbol.M_3) {
            return UnitSymbolEnum.M_3;
        } else if (xml == UnitSymbol.MIN) {
            return UnitSymbolEnum.MIN;
        } else if (xml == UnitSymbol.NEWTON) {
            return UnitSymbolEnum.NEWTON;
        } else if (xml == UnitSymbol.NO_UNIT) {
            return UnitSymbolEnum.NO_UNIT;
        } else if (xml == UnitSymbol.OHM) {
            return UnitSymbolEnum.OHM;
        } else if (xml == UnitSymbol.PA) {
            return UnitSymbolEnum.PA;
        } else if (xml == UnitSymbol.RAD) {
            return UnitSymbolEnum.RAD;
        } else if (xml == UnitSymbol.SIEMENS) {
            return UnitSymbolEnum.SIEMENS;
        } else if (xml == UnitSymbol.V) {
            return UnitSymbolEnum.V;
        } else if (xml == UnitSymbol.VA) {
            return UnitSymbolEnum.VA;
        } else if (xml == UnitSymbol.V_AH) {
            return UnitSymbolEnum.V_AH;
        } else if (xml == UnitSymbol.V_AR) {
            return UnitSymbolEnum.V_AR;
        } else if (xml == UnitSymbol.V_ARH) {
            return UnitSymbolEnum.V_ARH;
        } else if (xml == UnitSymbol.W) {
            return UnitSymbolEnum.W;
        } else if (xml == UnitSymbol.WH) {
            return UnitSymbolEnum.WH;
        } else if (xml == UnitSymbol.W_PER_VA) {
            return UnitSymbolEnum.W_PER_VA;
        } else {
            return null;
        }
    }

    /**
     * Converts XML unit multiplier to OpenFMB Java unit multiplier.
     * @param xml XML type.
     * @return OpenFMB Java type.
     */
    public static UnitMultiplierEnum convertFromXml(UnitMultiplier xml) {

        if (xml == UnitMultiplier.CENTI) {
            return UnitMultiplierEnum.CENTI;
        } else if (xml == UnitMultiplier.DECI) {
            return UnitMultiplierEnum.DECI;
        } else if (xml == UnitMultiplier.GIGA) {
            return UnitMultiplierEnum.GIGA;
        } else if (xml == UnitMultiplier.KILO) {
            return UnitMultiplierEnum.KILO;
        } else if (xml == UnitMultiplier.MEGA) {
            return UnitMultiplierEnum.MEGA;
        } else if (xml == UnitMultiplier.MILLI) {
            return UnitMultiplierEnum.MILLI;
        } else if (xml == UnitMultiplier.MICRO) {
            return UnitMultiplierEnum.MICRO;
        } else if (xml == UnitMultiplier.NANO) {
            return UnitMultiplierEnum.NANO;
        } else if (xml == UnitMultiplier.NO_MULTIPLIER) {
            return UnitMultiplierEnum.NO_MULTIPLIER;
        } else if (xml == UnitMultiplier.PICO) {
            return UnitMultiplierEnum.PICO;
        } else if (xml == UnitMultiplier.TERA) {
            return UnitMultiplierEnum.TERA;
        } else {
            return null;
        }
    }

    /**
     * Converts XML flow direction to OpenFMB Java flow direction.
     * @param xml XML type.
     * @return OpenFMB Java type.
     */
    public static FlowDirectionEnum convertFromXml(FlowDirection xml) {

        if (xml == FlowDirection.FORWARD) {
            return FlowDirectionEnum.FORWARD;
        } else if (xml == FlowDirection.LAGGING) {
            return FlowDirectionEnum.LAGGING;
        } else if (xml == FlowDirection.LEADING) {
            return FlowDirectionEnum.LEADING;
        } else if (xml == FlowDirection.NET) {
            return FlowDirectionEnum.NET;
        } else if (xml == FlowDirection.NO_DIRECTION) {
            return FlowDirectionEnum.NO_DIRECTION;
        } else if (xml == FlowDirection.Q_1_MINUS_Q_4) {
            return FlowDirectionEnum.Q_1_MINUS_Q_4;
        } else if (xml == FlowDirection.Q_1_PLUS_Q_2) {
            return FlowDirectionEnum.Q_1_PLUS_Q_2;
        } else if (xml == FlowDirection.Q_1_PLUS_Q_3) {
            return FlowDirectionEnum.Q_1_PLUS_Q_3;
        } else if (xml == FlowDirection.Q_1_PLUS_Q_4) {
            return FlowDirectionEnum.Q_1_PLUS_Q_4;
        } else if (xml == FlowDirection.Q_2_MINUS_Q_3) {
            return FlowDirectionEnum.Q_2_MINUS_Q_3;
        } else if (xml == FlowDirection.Q_2_PLUS_Q_3) {
            return FlowDirectionEnum.Q_2_PLUS_Q_3;
        } else if (xml == FlowDirection.Q_2_PLUS_Q_4) {
            return FlowDirectionEnum.Q_2_PLUS_Q_4;
        } else if (xml == FlowDirection.Q_3_MINUS_Q_2) {
            return FlowDirectionEnum.Q_3_MINUS_Q_2;
        } else if (xml == FlowDirection.Q_3_PLUS_Q_4) {
            return FlowDirectionEnum.Q_3_PLUS_Q_4;
        } else if (xml == FlowDirection.QUADRANT_1) {
            return FlowDirectionEnum.QUADRANT_1;
        } else if (xml == FlowDirection.QUADRANT_2) {
            return FlowDirectionEnum.QUADRANT_2;
        } else if (xml == FlowDirection.QUADRANT_3) {
            return FlowDirectionEnum.QUADRANT_3;
        } else if (xml == FlowDirection.QUADRANT_4) {
            return FlowDirectionEnum.QUADRANT_4;
        } else if (xml == FlowDirection.REVERSE) {
            return FlowDirectionEnum.REVERSE;
        } else if (xml == FlowDirection.TOTAL) {
            return FlowDirectionEnum.TOTAL;
        } else if (xml == FlowDirection.TOTAL_BY_PHASE) {
            return FlowDirectionEnum.TOTAL_BY_PHASE;
        } else {
            return null;
        }
    }

    /**
     * Converts XML phase to OpenFMB Java phase.
     * @param xml XML type.
     * @return OpenFMB Java type.
     */
    public static PhaseCodeEnum convertFromXml(PhaseCode xml) {

        if (xml == PhaseCode.A) {
            return PhaseCodeEnum.A;
        } else if (xml == PhaseCode.AB) {
            return PhaseCodeEnum.AB;
        } else if (xml == PhaseCode.ABC) {
            return PhaseCodeEnum.ABC;
        } else if (xml == PhaseCode.ABCN) {
            return PhaseCodeEnum.ABCN;
        } else if (xml == PhaseCode.ABN) {
            return PhaseCodeEnum.ABN;
        } else if (xml == PhaseCode.AC) {
            return PhaseCodeEnum.AC;
        } else if (xml == PhaseCode.ACN) {
            return PhaseCodeEnum.ACN;
        } else if (xml == PhaseCode.AN) {
            return PhaseCodeEnum.AN;
        } else if (xml == PhaseCode.B) {
            return PhaseCodeEnum.B;
        } else if (xml == PhaseCode.BC) {
            return PhaseCodeEnum.BC;
        } else if (xml == PhaseCode.BCN) {
            return PhaseCodeEnum.BCN;
        } else if (xml == PhaseCode.BN) {
            return PhaseCodeEnum.BN;
        } else if (xml == PhaseCode.C) {
            return PhaseCodeEnum.C;
        } else if (xml == PhaseCode.CN) {
            return PhaseCodeEnum.CN;
        } else if (xml == PhaseCode.N) {
            return PhaseCodeEnum.N;
        } else if (xml == PhaseCode.S_1) {
            return PhaseCodeEnum.S_1;
        } else if (xml == PhaseCode.S_12) {
            return PhaseCodeEnum.S_12;
        } else if (xml == PhaseCode.S_12_N) {
            return PhaseCodeEnum.S_12_N;
        } else if (xml == PhaseCode.S_1_N) {
            return PhaseCodeEnum.S_1_N;
        } else if (xml == PhaseCode.S_2) {
            return PhaseCodeEnum.S_2;
        } else if (xml == PhaseCode.S_2_N) {
            return PhaseCodeEnum.S_2_N;
        } else {
            return null;
        }
    }
}

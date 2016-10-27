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

import com.greenenergycorp.openfmb.mapping.model.*;
import com.greenenergycorp.openfmb.xml.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CommonMapping {

    /**
     * Converts Java system time to JAXB XSD time.
     *
     * @param time Java system time.
     * @return JAXB XSD time.
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar xmlTimeFor(long time) throws DatatypeConfigurationException {
        final GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }

    /**
     * Build OpenFMB XML reading.
     *
     * @param id Identification for the reading.
     * @param value Value of the reading.
     * @param now Time of the reading.
     * @return JAXB XML Reading struct.
     * @throws Exception
     */
    public static Reading buildReading(final ReadingId id, final MeasValue value, final long now) throws Exception {
        final Double readingValue = value.asDouble();
        if (readingValue != null) {

            final DateTimeInterval timeInterval = new DateTimeInterval();
            timeInterval.setStart(xmlTimeFor(now));
            timeInterval.setEnd(xmlTimeFor(now));

            final ReadingType readingType = new ReadingType();

            readingType.setUnit(convertToXml(id.getUnit()));
            readingType.setMultiplier(convertToXml(id.getMultiplier()));
            readingType.setFlowDirection(convertToXml(id.getFlowDirection()));
            readingType.setPhases(convertToXml(id.getPhases()));
            readingType.setName(id.getName());

            final Reading reading = new Reading();
            reading.setQualityFlag(new byte[] {0, 0});
            reading.setSource("");
            reading.setTimePeriod(timeInterval);
            reading.setReadingType(readingType);

            reading.setValue(readingValue.floatValue());
            return reading;
        } else {
            return null;
        }
    }

    /**
     * Convert unit Java enum to JAXB enum.
     *
     * @param e Java enum value.
     * @return JAXB enum value.
     */
    public static UnitSymbolKind convertToXml(UnitSymbolEnum e) {

        if (e == UnitSymbolEnum.AMP) {
            return UnitSymbolKind.AMP;
        } else if (e == UnitSymbolEnum.DEG) {
            return UnitSymbolKind.DEG;
        } else if (e == UnitSymbolEnum.DEG_C) {
            return UnitSymbolKind.DEG_C;
        } else if (e == UnitSymbolEnum.FARAD) {
            return UnitSymbolKind.FARAD;
        } else if (e == UnitSymbolEnum.GRAM) {
            return UnitSymbolKind.GRAM;
        } else if (e == UnitSymbolEnum.HOUR) {
            return UnitSymbolKind.HOUR;
        } else if (e == UnitSymbolEnum.HENRY) {
            return UnitSymbolKind.HENRY;
        } else if (e == UnitSymbolEnum.HZ) {
            return UnitSymbolKind.HZ;
        } else if (e == UnitSymbolEnum.JOULE) {
            return UnitSymbolKind.JOULE;
        } else if (e == UnitSymbolEnum.METER) {
            return UnitSymbolKind.METER;
        } else if (e == UnitSymbolEnum.M_2) {
            return UnitSymbolKind.M_2;
        } else if (e == UnitSymbolEnum.M_3) {
            return UnitSymbolKind.M_3;
        } else if (e == UnitSymbolEnum.MIN) {
            return UnitSymbolKind.MIN;
        } else if (e == UnitSymbolEnum.NEWTON) {
            return UnitSymbolKind.NEWTON;
        } else if (e == UnitSymbolEnum.NO_UNIT) {
            return UnitSymbolKind.NO_UNIT;
        } else if (e == UnitSymbolEnum.OHM) {
            return UnitSymbolKind.OHM;
        } else if (e == UnitSymbolEnum.PA) {
            return UnitSymbolKind.PA;
        } else if (e == UnitSymbolEnum.RAD) {
            return UnitSymbolKind.RAD;
        } else if (e == UnitSymbolEnum.SIEMENS) {
            return UnitSymbolKind.SIEMENS;
        } else if (e == UnitSymbolEnum.V) {
            return UnitSymbolKind.V;
        } else if (e == UnitSymbolEnum.VA) {
            return UnitSymbolKind.VA;
        } else if (e == UnitSymbolEnum.V_AH) {
            return UnitSymbolKind.V_AH;
        } else if (e == UnitSymbolEnum.V_AR) {
            return UnitSymbolKind.V_AR;
        } else if (e == UnitSymbolEnum.V_ARH) {
            return UnitSymbolKind.V_ARH;
        } else if (e == UnitSymbolEnum.W) {
            return UnitSymbolKind.W;
        } else if (e == UnitSymbolEnum.WH) {
            return UnitSymbolKind.WH;
        } else if (e == UnitSymbolEnum.W_PER_VA) {
            return UnitSymbolKind.W_PER_VA;
        } else {
            return null;
        }
    }

    /**
     * Convert unit multiplier Java enum to JAXB enum.
     *
     * @param e Java enum value.
     * @return JAXB enum value.
     */
    public static UnitMultiplierKind convertToXml(UnitMultiplierEnum e) {

        if (e == UnitMultiplierEnum.CENTI) {
            return UnitMultiplierKind.CENTI;
        } else if (e == UnitMultiplierEnum.DECI) {
            return UnitMultiplierKind.DECI;
        } else if (e == UnitMultiplierEnum.GIGA) {
            return UnitMultiplierKind.GIGA;
        } else if (e == UnitMultiplierEnum.KILO) {
            return UnitMultiplierKind.KILO;
        } else if (e == UnitMultiplierEnum.MEGA) {
            return UnitMultiplierKind.MEGA;
        } else if (e == UnitMultiplierEnum.MILLI) {
            return UnitMultiplierKind.MILLI;
        } else if (e == UnitMultiplierEnum.MICRO) {
            return UnitMultiplierKind.MICRO;
        } else if (e == UnitMultiplierEnum.NANO) {
            return UnitMultiplierKind.NANO;
        } else if (e == UnitMultiplierEnum.NO_MULTIPLIER) {
            return UnitMultiplierKind.NO_MULTIPLIER;
        } else if (e == UnitMultiplierEnum.PICO) {
            return UnitMultiplierKind.PICO;
        } else if (e == UnitMultiplierEnum.TERA) {
            return UnitMultiplierKind.TERA;
        } else {
            return null;
        }
    }

    /**
     * Convert flow direction Java enum to JAXB enum.
     *
     * @param e Java enum value.
     * @return JAXB enum value.
     */
    public static FlowDirectionKind convertToXml(FlowDirectionEnum e) {

        if (e == FlowDirectionEnum.FORWARD) {
            return FlowDirectionKind.FORWARD;
        } else if (e == FlowDirectionEnum.LAGGING) {
            return FlowDirectionKind.LAGGING;
        } else if (e == FlowDirectionEnum.LEADING) {
            return FlowDirectionKind.LEADING;
        } else if (e == FlowDirectionEnum.NET) {
            return FlowDirectionKind.NET;
        } else if (e == FlowDirectionEnum.NO_DIRECTION) {
            return FlowDirectionKind.NO_DIRECTION;
        } else if (e == FlowDirectionEnum.Q_1_MINUS_Q_4) {
            return FlowDirectionKind.Q_1_MINUS_Q_4;
        } else if (e == FlowDirectionEnum.Q_1_PLUS_Q_2) {
            return FlowDirectionKind.Q_1_PLUS_Q_2;
        } else if (e == FlowDirectionEnum.Q_1_PLUS_Q_3) {
            return FlowDirectionKind.Q_1_PLUS_Q_3;
        } else if (e == FlowDirectionEnum.Q_1_PLUS_Q_4) {
            return FlowDirectionKind.Q_1_PLUS_Q_4;
        } else if (e == FlowDirectionEnum.Q_2_MINUS_Q_3) {
            return FlowDirectionKind.Q_2_MINUS_Q_3;
        } else if (e == FlowDirectionEnum.Q_2_PLUS_Q_3) {
            return FlowDirectionKind.Q_2_PLUS_Q_3;
        } else if (e == FlowDirectionEnum.Q_2_PLUS_Q_4) {
            return FlowDirectionKind.Q_2_PLUS_Q_4;
        } else if (e == FlowDirectionEnum.Q_3_MINUS_Q_2) {
            return FlowDirectionKind.Q_3_MINUS_Q_2;
        } else if (e == FlowDirectionEnum.Q_3_PLUS_Q_4) {
            return FlowDirectionKind.Q_3_PLUS_Q_4;
        } else if (e == FlowDirectionEnum.QUADRANT_1) {
            return FlowDirectionKind.QUADRANT_1;
        } else if (e == FlowDirectionEnum.QUADRANT_2) {
            return FlowDirectionKind.QUADRANT_2;
        } else if (e == FlowDirectionEnum.QUADRANT_3) {
            return FlowDirectionKind.QUADRANT_3;
        } else if (e == FlowDirectionEnum.QUADRANT_4) {
            return FlowDirectionKind.QUADRANT_4;
        } else if (e == FlowDirectionEnum.REVERSE) {
            return FlowDirectionKind.REVERSE;
        } else if (e == FlowDirectionEnum.TOTAL) {
            return FlowDirectionKind.TOTAL;
        } else if (e == FlowDirectionEnum.TOTAL_BY_PHASE) {
            return FlowDirectionKind.TOTAL_BY_PHASE;
        } else {
            return null;
        }
    }

    /**
     * Convert phase code Java enum to JAXB enum.
     *
     * @param e Java enum value.
     * @return JAXB enum value.
     */
    public static PhaseCodeKind convertToXml(PhaseCodeEnum e) {

        if (e == PhaseCodeEnum.A) {
            return PhaseCodeKind.A;
        } else if (e == PhaseCodeEnum.AB) {
            return PhaseCodeKind.AB;
        } else if (e == PhaseCodeEnum.ABC) {
            return PhaseCodeKind.ABC;
        } else if (e == PhaseCodeEnum.ABCN) {
            return PhaseCodeKind.ABCN;
        } else if (e == PhaseCodeEnum.ABN) {
            return PhaseCodeKind.ABN;
        } else if (e == PhaseCodeEnum.AC) {
            return PhaseCodeKind.AC;
        } else if (e == PhaseCodeEnum.ACN) {
            return PhaseCodeKind.ACN;
        } else if (e == PhaseCodeEnum.AN) {
            return PhaseCodeKind.AN;
        } else if (e == PhaseCodeEnum.B) {
            return PhaseCodeKind.B;
        } else if (e == PhaseCodeEnum.BC) {
            return PhaseCodeKind.BC;
        } else if (e == PhaseCodeEnum.BCN) {
            return PhaseCodeKind.BCN;
        } else if (e == PhaseCodeEnum.BN) {
            return PhaseCodeKind.BN;
        } else if (e == PhaseCodeEnum.C) {
            return PhaseCodeKind.C;
        } else if (e == PhaseCodeEnum.CN) {
            return PhaseCodeKind.CN;
        } else if (e == PhaseCodeEnum.N) {
            return PhaseCodeKind.N;
        } else if (e == PhaseCodeEnum.S_1) {
            return PhaseCodeKind.S_1;
        } else if (e == PhaseCodeEnum.S_12) {
            return PhaseCodeKind.S_12;
        } else if (e == PhaseCodeEnum.S_12_N) {
            return PhaseCodeKind.S_12_N;
        } else if (e == PhaseCodeEnum.S_1_N) {
            return PhaseCodeKind.S_1_N;
        } else if (e == PhaseCodeEnum.S_2) {
            return PhaseCodeKind.S_2;
        } else if (e == PhaseCodeEnum.S_2_N) {
            return PhaseCodeKind.S_2_N;
        } else {
            return null;
        }
    }

    /**
     * Convert unit JAXB enum to Java enum.
     *
     * @param e JAXB enum value.
     * @return Java enum value.
     */
    public static UnitSymbolEnum convertFromXml(UnitSymbolKind e) {

        if (e == UnitSymbolKind.AMP) {
            return UnitSymbolEnum.AMP;
        } else if (e == UnitSymbolKind.DEG) {
            return UnitSymbolEnum.DEG;
        } else if (e == UnitSymbolKind.DEG_C) {
            return UnitSymbolEnum.DEG_C;
        } else if (e == UnitSymbolKind.FARAD) {
            return UnitSymbolEnum.FARAD;
        } else if (e == UnitSymbolKind.GRAM) {
            return UnitSymbolEnum.GRAM;
        } else if (e == UnitSymbolKind.HOUR) {
            return UnitSymbolEnum.HOUR;
        } else if (e == UnitSymbolKind.HENRY) {
            return UnitSymbolEnum.HENRY;
        } else if (e == UnitSymbolKind.HZ) {
            return UnitSymbolEnum.HZ;
        } else if (e == UnitSymbolKind.JOULE) {
            return UnitSymbolEnum.JOULE;
        } else if (e == UnitSymbolKind.METER) {
            return UnitSymbolEnum.METER;
        } else if (e == UnitSymbolKind.M_2) {
            return UnitSymbolEnum.M_2;
        } else if (e == UnitSymbolKind.M_3) {
            return UnitSymbolEnum.M_3;
        } else if (e == UnitSymbolKind.MIN) {
            return UnitSymbolEnum.MIN;
        } else if (e == UnitSymbolKind.NEWTON) {
            return UnitSymbolEnum.NEWTON;
        } else if (e == UnitSymbolKind.NO_UNIT) {
            return UnitSymbolEnum.NO_UNIT;
        } else if (e == UnitSymbolKind.OHM) {
            return UnitSymbolEnum.OHM;
        } else if (e == UnitSymbolKind.PA) {
            return UnitSymbolEnum.PA;
        } else if (e == UnitSymbolKind.RAD) {
            return UnitSymbolEnum.RAD;
        } else if (e == UnitSymbolKind.SIEMENS) {
            return UnitSymbolEnum.SIEMENS;
        } else if (e == UnitSymbolKind.V) {
            return UnitSymbolEnum.V;
        } else if (e == UnitSymbolKind.VA) {
            return UnitSymbolEnum.VA;
        } else if (e == UnitSymbolKind.V_AH) {
            return UnitSymbolEnum.V_AH;
        } else if (e == UnitSymbolKind.V_AR) {
            return UnitSymbolEnum.V_AR;
        } else if (e == UnitSymbolKind.V_ARH) {
            return UnitSymbolEnum.V_ARH;
        } else if (e == UnitSymbolKind.W) {
            return UnitSymbolEnum.W;
        } else if (e == UnitSymbolKind.WH) {
            return UnitSymbolEnum.WH;
        } else if (e == UnitSymbolKind.W_PER_VA) {
            return UnitSymbolEnum.W_PER_VA;
        } else {
            return null;
        }
    }

    /**
     * Convert unit JAXB enum to Java enum.
     *
     * @param e JAXB enum value.
     * @return Java enum value.
     */
    public static UnitMultiplierEnum convertFromXml(UnitMultiplierKind e) {

        if (e == UnitMultiplierKind.CENTI) {
            return UnitMultiplierEnum.CENTI;
        } else if (e == UnitMultiplierKind.DECI) {
            return UnitMultiplierEnum.DECI;
        } else if (e == UnitMultiplierKind.GIGA) {
            return UnitMultiplierEnum.GIGA;
        } else if (e == UnitMultiplierKind.KILO) {
            return UnitMultiplierEnum.KILO;
        } else if (e == UnitMultiplierKind.MEGA) {
            return UnitMultiplierEnum.MEGA;
        } else if (e == UnitMultiplierKind.MILLI) {
            return UnitMultiplierEnum.MILLI;
        } else if (e == UnitMultiplierKind.MICRO) {
            return UnitMultiplierEnum.MICRO;
        } else if (e == UnitMultiplierKind.NANO) {
            return UnitMultiplierEnum.NANO;
        } else if (e == UnitMultiplierKind.NO_MULTIPLIER) {
            return UnitMultiplierEnum.NO_MULTIPLIER;
        } else if (e == UnitMultiplierKind.PICO) {
            return UnitMultiplierEnum.PICO;
        } else if (e == UnitMultiplierKind.TERA) {
            return UnitMultiplierEnum.TERA;
        } else {
            return null;
        }
    }
}

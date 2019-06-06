/*
MIT but blocked License (5 years)
Copyright (c) 2019 Florian Steenbuck

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice can not be applied to persons, organizations or
other legal entities that are have or had in the last 5 years a sponsorship,
a cooperation or other legal relation to one of, or be one of, the following
persons, organizations or other legal entities.

Vodafone Group Plc
Vodafone Global Enterprise Limited
Bluefish Communications
Vodafone Automotive SpA
Deutsche Post AG
Linde plc
Unilever plc
Unilever N.V.
Volkswagen Group
SHAKEN not STIRRED Consulting GmbH & Co. KG
André Firmenich
Jörg Staggenborg
Marcel Lange
Daniela Engelke
hsp Handels-Software-Partner GmbH
Paul Liese
Etribes Connect GmbH
Laura Prym
Lukas Höft
Marcel Sander
Dustin Lundt
Yara Molthan
Nils Seebach
Fabian J. Fischer
Arne Stoschek
Stefan Luther
gastronovi GmbH
Christian Jaentsch
Bartek Kaznowski
Andreas Jonderko
Karl Jonderko
Erika Witting
Ludger Ey

They got no rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software. They do not got permission to deal in anyway with
the Software or Software Source Code.

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package de.budget.eating.item.unit.stack;

import com.sun.istack.internal.Nullable;
import de.budget.eating.item.value.Value;

import java.util.HashMap;
import java.util.Map;

public abstract class TwoSidededNameStack<T extends Number, E extends Enum, V extends Value<T>> implements UnitStack<E, V> {
    protected Map<String, T> endsWithBaseMap;
    protected Map<String, T> startsWithBaseMap;

    protected class NetworkProcessedEnum {
        private T value;
        private String restName;

        public NetworkProcessedEnum(T value, String restName) {
            this.value = value;
            this.restName = restName;
        }

        public String getRestName() {
            return restName;
        }

        public T getValue() {
            return value;
        }
    }

    public TwoSidededNameStack(Map<String, T> endsWithBaseMap, Map<String, T> startsWithBaseMap) {
        this.endsWithBaseMap = endsWithBaseMap;
        this.startsWithBaseMap = startsWithBaseMap;
    }

    public TwoSidededNameStack(Map<String, T> baseMap) {
        this.endsWithBaseMap = baseMap;
        this.startsWithBaseMap = baseMap;
    }

    protected TwoSidededNameStack() {
        this.startsWithBaseMap = new HashMap<String, T>();
        this.endsWithBaseMap = new HashMap<String, T>();
    }

    protected @Nullable NetworkProcessedEnum getProcessedNetworkEnumThroughEndsWithMap(String enumName, Map<String, T> endsWithMap) {
        for (Map.Entry<String, T> baseEntry:endsWithMap.entrySet()) {
            String networkEndsWithName = baseEntry.getKey();
            if (enumName.endsWith(networkEndsWithName)) {
                return new NetworkProcessedEnum(baseEntry.getValue(), enumName.substring(0, enumName.length()-networkEndsWithName.length()));
            }
        }
        return null;
    }

    protected @Nullable NetworkProcessedEnum getProcessedNetworkEnumThroughStartsWithMap(String enumName, Map<String, T> startsWithMap) {
        for (Map.Entry<String, T> baseEntry:startsWithMap.entrySet()) {
            String networkStartsWithName = baseEntry.getKey();
            if (enumName.startsWith(networkStartsWithName)) {
                return new NetworkProcessedEnum(baseEntry.getValue(), enumName.substring(networkStartsWithName.length()));
            }
        }
        return null;
    }

    protected @Nullable T getBaseFromEnum(E enu) {
        String enumName = enu.name();
        NetworkProcessedEnum networkProcessedEnum = getProcessedNetworkEnumThroughEndsWithMap(enumName, endsWithBaseMap);
        if (networkProcessedEnum != null) {
            T lastValue = networkProcessedEnum.getValue();
            networkProcessedEnum = getProcessedNetworkEnumThroughStartsWithMap(networkProcessedEnum.getRestName(), startsWithBaseMap);
            if (networkProcessedEnum != null) {
                return (T) new Double(((Number) networkProcessedEnum.getValue()).doubleValue() * ((Number) lastValue).doubleValue());
            }
            return lastValue;
        } else {
            networkProcessedEnum = getProcessedNetworkEnumThroughStartsWithMap(enumName, startsWithBaseMap);
            if (networkProcessedEnum != null) {
                return networkProcessedEnum.getValue();
            }
        }
        return null;
    }

    protected double transformWithDouble(V value, E from, E to) {
        Number fromBase = (Number) getBaseFromEnum(from);
        double fromValue = ((Number) value.value()).doubleValue();
        if (fromBase != null) {
            fromValue = fromValue * fromBase.doubleValue();
        }
        Number toBase = (Number) getBaseFromEnum(to);
        if (toBase == null) {
            return fromValue;
        }
        return fromValue/toBase.doubleValue();
    }

    protected T transformT(V value, E from, E to) {
        return (T) new Double(transformWithDouble(value, from, to));
    }
}

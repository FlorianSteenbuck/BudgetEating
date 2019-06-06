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

import de.budget.eating.item.value.Value;

public class GramStack extends MegaStack<GramStack.GramEnum, Value<Double>> {
    private static final GramStack defaultInstance = new GramStack();

    public GramStack() {
        super();
        endsWithBaseMap.put("t", new Double(1000000));
    }

    @Override
    public Value<Double> transform(Value<Double> value, GramEnum from, GramEnum to) {
        return new Value<Double>(transformT(value, from, to));
    }

    @Override
    public GramEnum fromName(String shortName) {
        return GramEnum.valueOf(shortName);
    }

    @Override
    public String display(Value<Double> value) {
        return value.value().toString()+" "+symbol();
    }

    @Override
    public String displayHtml(Value<Double> value) {
        return display(value);
    }

    @Override
    public String fullName() {
        return "Gram";
    }

    @Override
    public String shortName() {
        return "g";
    }

    @Override
    public String symbol() {
        return "g";
    }

    public static GramStack getDefaultInstance() {
        return defaultInstance;
    }

    public enum GramEnum {
        Gt, Mt, kt, t, kg, g, mg, μg, ng, pg;
    }
}
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

import de.budget.eating.item.unit.UnitCurrency;
import de.budget.eating.item.value.RangeValue;
import de.budget.eating.item.value.Value;
import de.budget.eating.item.value.ValuePair;

public class EggSizeStack implements UnitStack<EggSizeStack.EggSizeEnum, Value<Integer>> {
    private static final EggSizeStack gramInstance = new EggSizeStack();
    private static final EggSizeStack diameterInstance = new EggSizeStack();

    protected EggSizeStrat strat;

    public EggSizeStack() {
        this(EggSizeStrat.GRAM);
    }

    public EggSizeStack(EggSizeStrat strat) {
        this.strat = strat;
    }

    @Override
    public Value<Integer> transform(Value<Integer> value, EggSizeEnum from, EggSizeEnum to) {
        int count = value.value().intValue();

        double size = from.getGram().value().value();
        double toSize = from.getGram().value().value();
        if (strat == EggSizeStrat.DIAMETER) {
            size = from.getDiameter().value().value();
            toSize = from.getDiameter().value().value();
        }

        double totalSize = count*size;
        double neededCount = totalSize/toSize;

        return new Value<Integer>(new Double(Math.floor(neededCount)).intValue());
    }

    @Override
    public EggSizeEnum fromName(String shortName) {
        return null;
    }

    @Override
    public String display(Value<Integer> value) {
        return null;
    }

    @Override
    public String displayHtml(Value<Integer> value) {
        return null;
    }

    @Override
    public String fullName() {
        return "Chicken Egg Size";
    }

    @Override
    public String shortName() {
        return "CES";
    }

    @Override
    public String symbol() {
        return shortName();
    }

    public static EggSizeStack getDiameterInstance() {
        return diameterInstance;
    }

    public static EggSizeStack getGramInstance() {
        return gramInstance;
    }

    public enum EggSizeEnum {
        S(
                new ValuePair<Value<Double>, UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>>(
                        new RangeValue<Double>(
                                43.0, 53.0,
                                RangeValue.ValueStrat.LOW
                        ),
                        new UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>(
                                GramStack.GramEnum.g,
                                GramStack.getDefaultInstance()
                        )
                ),
                new ValuePair<Value<Double>, UnitCurrency<Value<Double>, DiameterStack.MetaEnum, DiameterStack>>(
                        new RangeValue<Double>(
                                36.0, 41.0,
                                RangeValue.ValueStrat.LOW
                        ),
                        new UnitCurrency<Value<Double>, DiameterStack.MetaEnum, DiameterStack>(
                                MeterStack.MetaEnum.cm,
                                DiameterStack.getDefaultInstance()
                        )
                )
        ),
        M(
                new ValuePair<Value<Double>, UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>>(
                        new RangeValue<Double>(
                                53.0, 63.0,
                                RangeValue.ValueStrat.MID
                        ),
                        new UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>(
                                GramStack.GramEnum.g,
                                GramStack.getDefaultInstance()
                        )
                ),
                new ValuePair<Value<Double>, UnitCurrency<Value<Double>, DiameterStack.MetaEnum, DiameterStack>>(
                        new RangeValue<Double>(
                                41.0, 43.0,
                                RangeValue.ValueStrat.MID
                        ),
                        new UnitCurrency<Value<Double>, DiameterStack.MetaEnum, DiameterStack>(
                                MeterStack.MetaEnum.cm,
                                DiameterStack.getDefaultInstance()
                        )

                )
        ),
        L(
                new ValuePair<Value<Double>, UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>>(
                        new RangeValue<Double>(
                                63.0, 73.0,
                                RangeValue.ValueStrat.MID
                        ),
                        new UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>(
                                GramStack.GramEnum.g,
                                GramStack.getDefaultInstance()
                        )
                ),
                new ValuePair<Value<Double>, UnitCurrency<Value<Double>, DiameterStack.MetaEnum, DiameterStack>>(
                        new RangeValue<Double>(
                                43.0, 46.0,
                                RangeValue.ValueStrat.MID
                        ),
                        new UnitCurrency<Value<Double>, DiameterStack.MetaEnum, DiameterStack>(
                                MeterStack.MetaEnum.cm,
                                DiameterStack.getDefaultInstance()
                        )

                )
        ),
        XL(
                new ValuePair<Value<Double>, UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>>(
                        new RangeValue<Double>(
                                73.0, 90.0,
                                RangeValue.ValueStrat.HIGH
                        ),
                        new UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>(
                                GramStack.GramEnum.g,
                                GramStack.getDefaultInstance()
                        )
                ),
                new ValuePair<Value<Double>, UnitCurrency<Value<Double>, DiameterStack.MetaEnum, DiameterStack>>(
                        new RangeValue<Double>(
                                46.0, 49.0,
                                RangeValue.ValueStrat.HIGH
                        ),
                        new UnitCurrency<Value<Double>, DiameterStack.MetaEnum, DiameterStack>(
                                MeterStack.MetaEnum.cm,
                                DiameterStack.getDefaultInstance()
                        )

                )
        );
        private ValuePair<Value<Double>, UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>> gram;
        private ValuePair<Value<Double>, UnitCurrency<Value<Double>, MeterStack.MetaEnum, DiameterStack>> diameter;

        EggSizeEnum(ValuePair<Value<Double>, UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>> gram, ValuePair<Value<Double>, UnitCurrency<Value<Double>, MeterStack.MetaEnum, DiameterStack>> diameter) {
            this.gram = gram;
            this.diameter = diameter;
        }

        public ValuePair<Value<Double>, UnitCurrency<Value<Double>, MeterStack.MetaEnum, DiameterStack>> getDiameter() {
            return diameter;
        }

        public ValuePair<Value<Double>, UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>> getGram() {
            return gram;
        }
    }

    public enum EggSizeStrat {
        GRAM, DIAMETER
    }
}

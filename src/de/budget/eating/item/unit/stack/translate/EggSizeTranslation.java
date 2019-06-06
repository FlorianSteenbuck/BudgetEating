package de.budget.eating.item.unit.stack.translate;

import de.budget.eating.item.unit.UnitCurrency;
import de.budget.eating.item.unit.stack.*;
import de.budget.eating.item.value.Value;
import de.budget.eating.item.value.ValuePair;

public class EggSizeTranslation
    implements UnitStackTranslation<
        EggSizeStack, UnitStack,
        ValuePair<
                Value<Integer>,
                UnitCurrency<
                        Value<Integer>,
                        EggSizeStack.EggSizeEnum,
                        EggSizeStack
                >
        >,
        ValuePair<
                Value<Double>,
                UnitCurrency<
                        Value<Double>,
                        Enum,
                        UnitStack<Enum, Value<Double>>
                >
        >> {

    @Override
    public ValuePair translateFrom(ValuePair<Value<Integer>, UnitCurrency<Value<Integer>, EggSizeStack.EggSizeEnum, EggSizeStack>> value, EggSizeStack from, UnitStack to) {
        boolean diameter = to instanceof DiameterStack;

        double unitValue = value.value().value().intValue();
        if (diameter) {
            unitValue = unitValue*value.currency().getUnit().getDiameter().value().value().doubleValue();
        } else {
            unitValue = unitValue*value.currency().getUnit().getGram().value().value().doubleValue();
        }

        if (diameter) {
            return new ValuePair<Value<Double>, UnitCurrency<Value<Double>, MeterStack.MetaEnum, DiameterStack>>(
                    new Value<Double>(unitValue),
                    new UnitCurrency<Value<Double>, MeterStack.MetaEnum, DiameterStack>(
                            MeterStack.MetaEnum.cm,
                            DiameterStack.getDefaultInstance()
                    )

            );
        } else {
            return new ValuePair<Value<Double>, UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>>(
                    new Value<Double>(unitValue),
                    new UnitCurrency<Value<Double>, GramStack.GramEnum, GramStack>(
                            GramStack.GramEnum.g,
                            GramStack.getDefaultInstance()
                    )

            );
        }
    }

    @Override
    public ValuePair<Value<Integer>, UnitCurrency<Value<Integer>, EggSizeStack.EggSizeEnum, EggSizeStack>> translateTo(ValuePair<Value<Double>, UnitCurrency<Value<Double>, Enum, UnitStack<Enum, Value<Double>>>> value, UnitStack to, EggSizeStack from) {
        boolean diameter = to instanceof DiameterStack;

        double unitValue = value.value().value();
        double smallest = -1.0;
        EggSizeStack.EggSizeEnum smallestEggSize = null;
        for (EggSizeStack.EggSizeEnum eggSize:EggSizeStack.EggSizeEnum.values()) {
            double maySmaller = 0.0;
            if (diameter) {
                maySmaller = unitValue / eggSize.getDiameter().value().value();
            } else {
                maySmaller = unitValue / eggSize.getGram().value().value();
            }

            if (smallest == -1.0 && (maySmaller < smallest && maySmaller > 0)) {
                smallest = maySmaller;
                smallestEggSize = eggSize;
            }
        }
        return new ValuePair<Value<Integer>, UnitCurrency<Value<Integer>, EggSizeStack.EggSizeEnum, EggSizeStack>>(
                new Value<Integer>(new Double(Math.floor(smallest)).intValue()),
                new UnitCurrency<Value<Integer>, EggSizeStack.EggSizeEnum, EggSizeStack>(
                        smallestEggSize,
                        EggSizeStack.getDiameterInstance()
                )
        );
    }

    @Override
    public Class<UnitStack>[] toSupported() {
        return new Class[]{DiameterStack.class, GramStack.class};
    }

    @Override
    public Class<EggSizeStack>[] fromSupported() {
        return new Class[]{EggSizeStack.class};
    }
}

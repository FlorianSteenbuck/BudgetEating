package de.budget.eating.item.unit.stack.translate;

import de.budget.eating.item.unit.stack.UnitStack;
import de.budget.eating.item.value.ValuePair;

import java.util.Collection;

/*
 * UnitStackTranslation - translate a unit from a unit stack to another unit stack
 */
public interface UnitStackTranslation<F extends UnitStack, T extends UnitStack, FV extends ValuePair, TV extends ValuePair> {
    TV translateFrom(FV value, F from, T to);
    FV translateTo(TV value, T to, F from);

    Class<T>[] toSupported();
    Class<F>[] fromSupported();
}

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
package de.budget.eating.item.unit;

import de.budget.eating.item.unit.stack.UnitStack;
import de.budget.eating.item.value.display.Currency;
import de.budget.eating.item.value.Value;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnitCurrency<V extends Value, U extends Enum, S extends UnitStack<U, V>> implements Currency<V> {
    protected U unit;
    protected S stack;

    public UnitCurrency(U unit, S stack) {
        this.unit = unit;
        this.stack = stack;
    }

    @Override
    public String display(V value) {
        return stack.display(value);
    }

    @Override
    public String displayHtml(V value) {
        return stack.displayHtml(value);
    }

    protected String executeUnitFunction(String funcName) {
        try {
            Method method = unit.getClass().getMethod(funcName);
            return (String) method.invoke(unit);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        } catch (ClassCastException e) {}
        // TODO better error handling
        return "";
    }

    @Override
    public String fullName() {
        return stack.fullName()+" in "+executeUnitFunction("fullName");
    }

    @Override
    public String shortName() {
        return stack.shortName()+executeUnitFunction("shortName");
    }

    @Override
    public String symbol() {
        return stack.symbol()+executeUnitFunction("symbol");
    }

    public S getStack() {
        return stack;
    }

    public U getUnit() {
        return unit;
    }
}

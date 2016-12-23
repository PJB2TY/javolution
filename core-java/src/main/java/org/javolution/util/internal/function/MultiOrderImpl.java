/*
 * Javolution - Java(TM) Solution for Real-Time and Embedded Systems
 * Copyright (C) 2012 - Javolution (http://javolution.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.javolution.util.internal.function;

import org.javolution.lang.MathLib;
import org.javolution.util.function.Order;

/**
 * The multi hash-order implementation (all objects are considered different).
 * Enum-based singleton, ref. Effective Java Reloaded (Joshua Bloch). 
 */
public enum MultiOrderImpl implements Order<Object> {
    INSTANCE;

    @Override
    public boolean areEqual(Object left, Object right) {
        return false; // Considers all objects are different.
    }

    /** Order based on hash value (never returns 0).*/
    @Override
    public int compare(Object left, Object right) {
        int hashLeft = left.hashCode();
        int hashRight = right.hashCode();
        return (hashLeft == hashRight) ? 0 : MathLib.unsignedLessThan(hashLeft, hashRight) ? -1 : 1;
    }

    @Override
    public int indexOf(Object object) { // Unsigned 32-bits
        return object.hashCode();
    }

    @Override
    public Order<Object> subOrder(Object obj) {
        return null; // No sub-order.
    }

}

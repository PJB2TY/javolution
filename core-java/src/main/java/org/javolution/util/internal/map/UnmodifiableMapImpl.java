/*
 * Javolution - Java(TM) Solution for Real-Time and Embedded Systems
 * Copyright (C) 2012 - Javolution (http://javolution.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.javolution.util.internal.map;

import java.util.Iterator;

import org.javolution.util.FastMap;
import org.javolution.util.function.Equality;
import org.javolution.util.function.Order;

/**
 * An unmodifiable view over a map.
 */
public final class UnmodifiableMapImpl<K, V> extends FastMap<K, V> {

    private static final long serialVersionUID = 0x700L; // Version.
    private static final String ERROR_MSG = "Unmodifiable View.";
    private final FastMap<K, V> inner;

    public UnmodifiableMapImpl(FastMap<K, V> inner) {
        this.inner = inner;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public FastMap<K, V> clone() {
        return new UnmodifiableMapImpl<K, V>(inner.clone());
    }

    @Override
    public Order<? super K> keyOrder() { // Immutable.
        return inner.keyOrder();
    }

    @Override
    public Iterator<Entry<K, V>> descendingIterator() {
        return new ReadOnlyEntryIterator<K,V>(inner.descendingIterator());
    }

    @Override
    public Iterator<Entry<K, V>> descendingIterator(K fromKey) {
        return new ReadOnlyEntryIterator<K,V>(inner.descendingIterator(fromKey));
    }

    @Override
    public V get(Object key) { // Optimization.
        return inner.get(key);
    }

    @Override
    public Entry<K, V> getEntry(K key) {
        return inner.getEntry(key);
    }

    @Override
    public boolean isEmpty() {
        return inner.isEmpty();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new ReadOnlyEntryIterator<K,V>(inner.iterator());
    }

    @Override
    public Iterator<Entry<K, V>> iterator(K fromKey) {
        return new ReadOnlyEntryIterator<K,V>(inner.iterator());
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public Entry<K,V> putEntry(Entry<? extends K, ? extends V> entry) {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public Entry<K, V> removeEntry(K key) {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public int size() {
        return inner.size();
    }

    @Override
    public UnmodifiableMapImpl<K, V> unmodifiable() {
        return this;
    }

    @Override
    public Equality<? super V> valuesEquality() { // Immutable.
        return inner.valuesEquality();
    }

    /** Read-only entry iterator (also a read-only view over the current entry) */
    private static class ReadOnlyEntryIterator<K,V> implements Iterator<Entry<K,V>> {
        private final Iterator<Entry<K,V>> inner;
        private ReadOnlyEntryIterator(Iterator<Entry<K,V>> inner) {
            this.inner = inner;
        }
        @Override
        public boolean hasNext() {
            return inner.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            return inner.next(); 
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unmodifiable map"); 
        }
    }
}

package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {

    private final Object[] collectionArray;
    private int remainLen;

    public BackwardIterator(Collection<T> collection) {
        this.collectionArray = collection.toArray();
        remainLen = collectionArray.length;
    }

    @Override
    public boolean hasNext() {
        return remainLen > 0;
    }

    @Override
    public T next() throws NoSuchElementException {
        if (hasNext()) {
            return (T) collectionArray[--remainLen];
        } else {
            throw new NoSuchElementException();
        }
    }
}

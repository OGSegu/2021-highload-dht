package ru.mail.polis.lsm.artem_drozdov;

import ru.mail.polis.lsm.Record;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TombstoneFilterIterator implements Iterator<Record> {

    private final LsmDAO.PeekingIterator delegate;

    public TombstoneFilterIterator(LsmDAO.PeekingIterator delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean hasNext() {
        for (; ; ) {
            Record peek = delegate.peek();
            if (peek == null) {
                return false;
            }
            if (!peek.isTombstone()) {
                return true;
            }

            delegate.next();
        }
    }

    @Override
    public Record next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No elements");
        }
        return delegate.next();
    }

}

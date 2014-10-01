package com.lerith.akka.chapter2.message;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2014/10/1.
 */
public class ImmutableMessage implements Serializable {
    private static final long serialVersionUID = -6025472823650741137L;
    private final int sequenceNumber;
    private final List<String> values;

    public ImmutableMessage(final int sequenceNumber, final List<String> values) {
        this.sequenceNumber = sequenceNumber;
        this.values = values;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<String> getValues() {
        return values;
    }
}

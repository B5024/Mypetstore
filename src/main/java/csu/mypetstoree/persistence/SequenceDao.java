package csu.mypetstoree.persistence;

import csu.mypetstoree.domain.Sequence;

public interface SequenceDao {

    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);

}

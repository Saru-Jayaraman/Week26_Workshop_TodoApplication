package se.lexicon.sequencers;

public class PersonIdSequencer {
    public static int currentId = 0;

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        PersonIdSequencer.currentId = currentId;
    }

    public static int nextId() {
        currentId++;
        setCurrentId(currentId);
        return currentId;
    }
}

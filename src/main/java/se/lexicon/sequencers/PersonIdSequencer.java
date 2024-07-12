package se.lexicon.sequencers;

public class PersonIdSequencer {
    private static int currentId;

    //Singleton Object
    private static PersonIdSequencer instance;

    //Private constructor - to make the class not to get instantiated from outside
    private PersonIdSequencer() {
        currentId = 0;
    }

    //When there is no object, create an object; Else return the instance of existing one.
    public static PersonIdSequencer getInstance() {
        if (instance == null)
            instance = new PersonIdSequencer();
        return instance;
    }

    public static void setCurrentId(int currentId) {
        PersonIdSequencer.currentId = currentId;
    }

    public int nextId() {
        currentId++;
        setCurrentId(currentId);
        return currentId;
    }
}
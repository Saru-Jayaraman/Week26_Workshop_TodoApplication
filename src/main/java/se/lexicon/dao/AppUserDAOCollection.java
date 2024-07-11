package se.lexicon.dao;

import se.lexicon.model.AppUser;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AppUserDAOCollection implements AppUserDAO {

    private final Set<AppUser> appUserSet;

    //Singleton Object
    private static AppUserDAOCollection instance;

    //Private constructor - to make the class not to get instantiated from outside
    private AppUserDAOCollection() {
        appUserSet = new HashSet<>();
    }

    public static AppUserDAOCollection getInstance() {
        if(instance == null)
            instance = new AppUserDAOCollection();
        return instance;
    }

    @Override
    public AppUser persist(AppUser appUser) {
        appUserSet.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String userName) {
        validateInput(userName);
        for (AppUser appUser : appUserSet) {
            if (appUser.getUsername().equalsIgnoreCase(userName)) {
                return appUser;
            }
        }
        return null;
    }

    @Override
    public Set<AppUser> findAll() {
        return new HashSet<>(appUserSet);
    }

    @Override
    public void remove(String userName) {
        validateInput(userName);
        Iterator<AppUser> iterator = appUserSet.iterator();
        AppUser removeAppUser;
        while (iterator.hasNext()) {
            removeAppUser = iterator.next();
            if (removeAppUser.getUsername().equalsIgnoreCase(userName)) {
                iterator.remove();
                break;
            }
        }
    }

    private void validateInput(String userName) {
        if(userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty...");
        }
    }
}

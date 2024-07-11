package se.lexicon.dao;

import se.lexicon.model.AppUser;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AppUserDAOCollection implements AppUserDAO {

    Set<AppUser> appUserSet = new HashSet<>();

    @Override
    public AppUser persist(AppUser appUser) {
        appUserSet.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String userName) {
        validateInput(userName);
        for (AppUser appUser : appUserSet) {
            if (appUser.getUsername().equals(userName)) {
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
            if (removeAppUser.getUsername().equals(userName)) {
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

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
        return null;
    }

    @Override
    public AppUser findByUsername(String userName) {
        for (AppUser appUser : appUserSet) {
            if (appUser.getUsername().equals(userName)) {
                return appUser;
            }
        }
        return null;
    }

    @Override
    public Set<AppUser> findAll() {
        HashSet<AppUser> newAppUserSet = new HashSet<>(appUserSet);
        return newAppUserSet;
    }

    @Override
    public void remove(String userName) {
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
}

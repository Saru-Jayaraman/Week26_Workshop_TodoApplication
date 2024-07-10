package se.lexicon.dao;

import se.lexicon.model.AppUser;
import java.util.Set;

public interface AppUserDAO {
    AppUser persist(AppUser appUser);
    AppUser findByUsername(String userName);
    Set<AppUser> findAll();
    void remove(String userName);
}
package se.lexicon.model;

import java.util.Objects;

public class AppUser {
    private String username;
    private String password;
    private AppRole role;

    public AppUser(String username, String password, AppRole role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public String getUsername() {
        return username;
    }

    public AppRole getRole() {
        return role;
    }

    public void setUsername(String username) {
        validateInput(username, "Username");
        this.username = username;
    }

    public void setPassword(String password) {
        validateInput(password, "Password");
        this.password = password;
    }

    public void setRole(AppRole role) {
        this.role = Objects.requireNonNull(role, "Role cannot be null...");
    }

    private void validateInput(String paramName, String paramFullName) {
        if(paramName == null || paramName.trim().isEmpty()) {
            throw new IllegalArgumentException(paramFullName + " is either empty or null...");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    @Override
    public String toString() {
        return "AppUser{" + "username='" + getUsername() + '\'' + ", role=" + getRole() + '}';
    }
}
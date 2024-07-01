package se.lexicon.model;

import java.util.Random;

public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email) {
        Random random = new Random();
        this.id = random.nextInt(100);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        validateInput(firstName, "First Name");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateInput(lastName, "Last Name");
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        validateInput(email, "Email");
        this.email = email;
    }

    private void validateInput(String paramName, String paramFullName) {
        if(paramName == null || paramName.trim().isEmpty()) {
            throw new IllegalArgumentException(paramFullName + " is either empty or null...");
        }
    }

    public String getSummary() {
        return "Person ID: " + getId() + " Name: " + getFirstName() + " " + getLastName() + " Email: " + getEmail();
    }
}
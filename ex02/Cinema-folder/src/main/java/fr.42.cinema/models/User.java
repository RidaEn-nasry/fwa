package fr.fortytwo.cinema.models;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;

    public User() {

    }

    public User(String firstName, String lastName, String phoneNumber, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
    }

    public User(Long id, String firstName, String lastName, String phoneNumber, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName=" + lastName +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber +
                ", password=" + password +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User otherUser = (User) o;
        return otherUser.getFirstName() == this.getFirstName() && otherUser.getLastName() == this.getLastName() &&
                otherUser.getPhoneNumber() == this.getPhoneNumber()
                && otherUser.getPassword() == this.getPassword() + this.getEmail();
    }

    @Override
    public int hashCode() {
        return this.getFirstName().hashCode() + this.getLastName().hashCode() + this.phoneNumber.hashCode()
                + this.getPhoneNumber().hashCode() + this.email.hashCode();
    }

}

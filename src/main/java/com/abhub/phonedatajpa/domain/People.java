package com.abhub.phonedatajpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * People class
 *
 * @author 100736974
 *
 */
@Entity
@Table(name = "PEOPLE", schema = "public")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    id;

    @Column(length = 50, unique = true, nullable = false)
    private String  login;

    @Column(length = 100)
    private String  password;

    @Column(name = "first_name", length = 50)
    private String  firstName;

    @Column(name = "last_name", length = 50)
    private String  lastName;

    @Column(length = 100, unique = true)
    private String  email;

    @Column(nullable = false)
    private boolean activated = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

}
package com.example.seniorapp.Models;


public class CaregiversObject {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;

    public CaregiversObject() {}

    public CaregiversObject(String name, String surname, String login, String password) {
        this.setName(name);
        this.setSurname(surname);
        this.setLogin(login);
        this.setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}

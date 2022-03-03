package com.example.sqlitedatabasedemo;

public class ModalData {

    String id;
    String name;
    String surname;
    String cource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }

    public ModalData(String id, String name, String surname, String cource) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cource = cource;
    }
}

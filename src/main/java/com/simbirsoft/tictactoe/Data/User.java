package com.simbirsoft.tictactoe.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Класс Пользователь(Игрок) и его параметры и методы
 */
public class User {
    private int id;
    private String name;
    @JsonIgnore //чтобы не выводился хэш пароля в JSON ответах на REST запросы; хоть какаято "безопасность" :(
    private int passwordhash;

    @Deprecated //пока что обхожусь без этого метода
    public void create(int id,String name, int passwordhash) {
        this.id=id;
        this.name = name;
        this.passwordhash =passwordhash;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPasswordhash() {
        return passwordhash;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswordhash(int passwordhash) {
        this.passwordhash = passwordhash;
    }
}

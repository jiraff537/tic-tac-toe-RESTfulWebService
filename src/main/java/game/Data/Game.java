package game.Data;

import game.SaveLoad.SaveLoadDataAPI;

import java.util.Arrays;

/**
 * Created by jiraff537 on 5/19/17.
 * Класс Игра и ее параметры и атрибуты
 */
public class Game {
    private int id;
    private int player1id;
    private int player2id;
    private int[] field = {0, 0, 0,    //0 - ход не сделан(пустая клетка)
            0, 0, 0,    //1 - Х крестик
            0, 0, 0};   //2 - О нолик

//    int[] field = {1, 2, 3,    //0 - ход не сделан(пустая клетка)
//            4, 5, 6,    //1 - Х крестик
//            7, 8, 9};   //2 - О нолик

    // проверка зарегистрирован ли такой пользователь с такими id
    public boolean userExist(int player1id, SaveLoadDataAPI<User> users) {
        try {
            users.get(player1id);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    //проверка не пытается ли пользователь играть сам с собой
    public boolean usersAreDifferent(int player1id, int player2id) {
        return player1id != player2id;
    }

    public String getFieldAsString() {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < field.length; i++) {
//            s.append(field[i]);
//        }
//        return s.toString();
        return Arrays.toString(field); //избавился от цикла со StringBuilder'ом
    }

    public int getId() {
        return id;
    }

    public int getPlayer1id() {
        return player1id;
    }

    public int getPlayer2id() {
        return player2id;
    }

    public int[] getField() {
        return field;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayer1id(int player1id) {
        this.player1id = player1id;
    }

    public void setPlayer2id(int player2id) {
        this.player2id = player2id;
    }

    public void setField(int[] field) {
        this.field = field;
    }
}//class

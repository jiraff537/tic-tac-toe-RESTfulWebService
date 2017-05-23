package game.Data;

import game.SaveLoad.SaveLoadDataAPI;
import game.utility.StringUtils;

import java.util.Arrays;

/**
 * Created by jiraff537 on 5/19/17.
 * Класс Игра и ее параметры и атрибуты
 */
public class Game {
    private int player1id;
    private int player2id;
    private int[] pole = {0, 0, 0,    //0 - ход не сделан(пустая клетка)
            0, 0, 0,    //1 - Х крестик
            0, 0, 0};   //2 - О нолик

//    int[] pole = {1, 2, 3,    //0 - ход не сделан(пустая клетка)
//            4, 5, 6,    //1 - Х крестик
//            7, 8, 9};   //2 - О нолик

    public String create(String player1id, String player2id, SaveLoadDataAPI<User> users) {
        try { //проверка на то что мы получили цифру в payerid
            this.player1id = new Integer(player1id);
            this.player2id = new Integer(player2id);
        } catch (NumberFormatException e) {
            return "ERROR in field 'player1id' or 'player2id', both of it must be integer! " +
                    e.getMessage(); // + "<br><br>" + StringUtils.StackTraceAsString(e);//stackTrace это наверное перебор
        }
        try {// проверка зарегистрированны ли такие пользователи с такими id
            users.load(this.player1id);
            users.load(this.player2id);
        } catch (IndexOutOfBoundsException e) {
            return "ERROR there is no such user registered! You input 'userid1=" + player1id + "' and 'user2id=" + player2id + "' "
                    + e.getLocalizedMessage() + "<br><br>" + StringUtils.StackTraceAsString(e);//stackTrace это наверное перебор;
        }
        if (this.player1id == this.player2id)
            return "ERROR player1id and player2id must be not equal"; //если id первого и второго игрока равны.
        return "OK";
    }

    public String getPoleAsString() {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < pole.length; i++) {
//            s.append(pole[i]);
//        }
//        return s.toString();
        return Arrays.toString(pole); //избавился от цикла со StringBuilder'ом
    }

    public int getPlayer1id() {
        return player1id;
    }

    public int getPlayer2id() {
        return player2id;
    }






}//class

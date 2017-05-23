package game.Data;

import game.SaveLoad.SaveLoadDataAPI;
import game.utility.StringUtils;

import java.util.Arrays;

/**
 * Created by jiraff537 on 5/19/17.
 * Класс хранящий в себе игру
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
        try{
            this.player1id = new Integer(player1id);
            this.player2id = new Integer(player2id);
        } catch (NumberFormatException e){
            return "ERROR in field 'player1id' or 'player1id', both of it must be integer! " +
                    e.getMessage() + "<br><br>" ; // + StringUtils.StackTraceAsString(e);//stackTrace это наверное перебор
        }
        return "OK";
    }

//    public Game(int player1id, int player2id) {
//
//        this.player1id = player1id;
//        this.player2id = player2id;
//    }

    public String getPoleAsString() {
        return Arrays.toString(pole); //избавился от цикла со StringBuilder'ом
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < pole.length; i++) {
//            s.append(pole[i]);
//        }
//        return s.toString();
    }

    public int getPlayer1id() {
        return player1id;
    }

    public void setPlayer1id(int player1id) {
        this.player1id = player1id;
    }

    public int getPlayer2id() {
        return player2id;
    }

    public void setPlayer2id(int player2id) {
        this.player2id = player2id;
    }

    public int[] getPole() {
        return pole;
    }

    public void setPole(int[] pole) {
        this.pole = pole;
    }


}//class

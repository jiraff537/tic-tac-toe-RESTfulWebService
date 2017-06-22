package com.simbirsoft.tictactoe.Data;

import com.simbirsoft.tictactoe.SaveLoad.SaveLoadDataAPI;

import java.util.Arrays;

/**
 * Класс Игра и ее параметры и методы
 */
public class Game {
    private int id;
    private int user1id;
    private int user2id;
    private int[] field = {0, 0, 0,    //0 - ход не сделан(пустая клетка)
            0, 0, 0,    //1 - Х крестик
            0, 0, 0};   //2 - О нолик

    //    int[] field = {1, 2, 3,    //0 - ход не сделан(пустая клетка)
//                   4, 5, 6,    //1 - Х крестик
//                   7, 8, 9};   //2 - О нолик
    //private int[] turns = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //все ходы игры игры
    //private int turnNumber=0; //номер текущего хода

    //@JsonIgnore
    private boolean CREATED = false; //игра создана
    //private boolean INPROCESS = false; //сделано более 1го хода
    private boolean GAMEOVER = false; //игра завершена (некуда ходить/победил один из гроков)
    //private int firstTurnUserid = -1; //id игрока совершившего первый ход (-1 ходов ещё не было)
    //первый ход делает
    private int userIdWhoPlaysXandMakeTunrFirst=-1;//id пользователя играющего крестиком и ходящим первым (-1 если ходов в игре еще не седлано)

    // проверка зарегистрирован ли такой пользователь с такими id
    public boolean userExist(int player1id, SaveLoadDataAPI<User> users) {
        try {
            users.get(player1id);
        } catch (IndexOutOfBoundsException e) {
            return false; //значит пользователя с таким id не существует!
        }
        return true;
    }

    //проверка не пытается ли пользователь играть сам с собой
    public boolean usersAreDifferent(int user1id, int user2id) {
        return user1id != user2id;
    }

    @Deprecated //пока что обхожусь без этого метода
    public String FieldAsString() {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < field.length; i++) {
//            s.append(field[i]);
//        }
//        return s.toString();
        return Arrays.toString(field); //избавился от цикла со StringBuilder'ом
    }

    public int turnFromStart(){  //сколько ходов сделано c начала игры и вычисление номера текущего хода
        // (если с начала игры сделано три хода значит сейчас четвертыйход)
        int turnsWasMade=0;
        for (int aField : field) {
            if (aField != 0) turnsWasMade = turnsWasMade + 1;
        }
        //System.out.println(turnsWasMade);
        return turnsWasMade+1; //+1 т.к. текущий ход больше предидущего на 1
    }

    public int getId() {
        return id;
    }

    public int getUser1id() {
        return user1id;
    }

    public int getUser2id() {
        return user2id;
    }

    public int[] getField() {
        return field;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser1id(int user1id) {
        this.user1id = user1id;
    }

    public void setUser2id(int user2id) {
        this.user2id = user2id;
    }

    public void setField(int[] field) {
        this.field = field;
    }

    public boolean isCREATED() {
        return CREATED;
    }

    public void setCREATED(boolean CREATED) {
        this.CREATED = CREATED;
    }

    public boolean isGAMEOVER() {
        return GAMEOVER;
    }

    public void setGAMEOVER(boolean GAMEOVER) {
        this.GAMEOVER = GAMEOVER;
    }

    public int getUserIdWhoPlaysXandMakeTunrFirst() {
        return userIdWhoPlaysXandMakeTunrFirst;
    }

    public void setUserIdWhoPlaysXandMakeTunrFirst(int userIdWhoPlaysXandMakeTunrFirst) {
        this.userIdWhoPlaysXandMakeTunrFirst = userIdWhoPlaysXandMakeTunrFirst;
    }
}//class

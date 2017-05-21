package game.Data;

/**
 * Created by jiraff537 on 5/19/17.
 */
public class Game {
    int player1id;
    int player2id;
    int[] pole = {0, 0, 0,    //0 - ход не сделан(пустая клетка)
            0, 0, 0,    //1 - Х крестик
            0, 0, 0};   //2 - О нолик

//    int[] pole = {1, 2, 3,    //0 - ход не сделан(пустая клетка)
//            4, 5, 6,    //1 - Х крестик
//            7, 8, 9};   //2 - О нолик


    public Game(int player1id, int player2id) {
        this.player1id = player1id;
        this.player2id = player2id;
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

    public String getPoleAsString() {

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < pole.length; i++) {
            s.append(pole[i]);
        }
        return s.toString();
    }


    public void setPole(int[] pole) {
        this.pole = pole;
    }
}//class

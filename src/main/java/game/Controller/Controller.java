package game.Controller;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import game.Data.Game;
import game.SaveLoad.SaveLoadData;
import game.SaveLoad.SaveLoadDataAPI;
import game.Data.User;
import org.springframework.web.bind.annotation.*;


//@RestController ==@Controller + @ResponseBody
@RestController
@RequestMapping("/tic-tac-toe")
public class Controller {
    private final AtomicInteger userCounter = new AtomicInteger();  //уникальный id-пользователя
    private final AtomicInteger gameCounter = new AtomicInteger();  //уникальный id-игры


    private SaveLoadDataAPI<User> users = new SaveLoadData<>();
    private SaveLoadDataAPI<Game> games = new SaveLoadData<>();

    //регистрация пользователя http://localhost:8080/tic-tac-toe/adduser?name=Alexei&code=11
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public @ResponseBody
    User addUser(@RequestParam(value = "name") String name,
                 @RequestParam(value = "passwordhash") int passwordhash) {
        User user = new User();
        user.setId(userCounter.get());
        user.setName(name);
        user.setPasswordhash(passwordhash);
        users.add(user, userCounter.getAndIncrement());
        return user;
    }

    //создание новой игры http://localhost:8080/tic-tac-toe/creategame?player1id=1&player2id=2
    @RequestMapping(value = "/creategame", method = RequestMethod.POST)
    public @ResponseBody
    String createGame(@RequestParam(value = "user1id") int user1id,
                      @RequestParam(value = "user2id") int user2id) {
        Game game = new Game();
        if (!game.userExist(user1id, users)) return "{\"error\":user1 not registered}";
        if (!game.userExist(user2id, users)) return "{\"error\":user2 not registered}";
        if (!game.usersAreDifferent(user1id, user2id)) return "{\"error\":user cannot play with himself}";
        game.setId(gameCounter.get());
        game.setUser1id(user1id);
        game.setUser2id(user2id);
        game.setCRAETED(true);
        int gameid = gameCounter.get();
        games.add(game, gameCounter.getAndIncrement());
        return "{\"gameid\":" + gameid + "}";
    }

    //получить текущее состояние игрового поля http://localhost:8080/tic-tac-toe/gamestate?gameid=1
    @RequestMapping(value = "/gamestate", method = RequestMethod.GET)
    public @ResponseBody
    int[] getGameState(@RequestParam(value = "gameid") int gameId) {

        return games.get(gameId).getField(); //TODO QQQ: wrap with Arrays.toString()
    }

    //совершение хода http://localhost:8080/tic-tac-toe/creategame?player1id=1&player2id=2
    @RequestMapping(value = "/maketurn", method = RequestMethod.POST)
    public @ResponseBody
    String makeTurn(@RequestParam(value = "gameid") int gameid,
                    @RequestParam(value = "userid") int userid,
                    @RequestParam(value = "turn") int turn) {
        //проверка входных значений на ошибочность
        if (gameid >= games.size() || gameid < 0 ) return "{\"error\":there is no such game}";
        Game game;
        game=games.get(gameid);
        if (!game.userExist(userid, users)) return "{\"error\":user not registered}";
        if (turn < 0 || turn >8) return "{\"error\":turn mut be from 0 to 8}";
        if (game.getUser1id()!= userid && game.getUser2id() != userid) return "{\"error\": user with this userid not play in this game}";
        if (game.isGAMEOVER()) return "{\"error\": this game is over}";
        //изменяем
        if (game.isCRAETED() && game.getUserIdWhoPlaysXandMakeTunrFirst()== -1) { //еслиигра создана и ходов ещё не было то это иргок делающий первых ход....
            game.setUserIdWhoPlaysXandMakeTunrFirst(userid);
        } else { //иначе проверить: а ваш ли сейчас ход сударь(%userid%)?
            System.out.println("проверка ваш ли ход???");
            if (game.getUserIdWhoPlaysXandMakeTunrFirst()==userid && (game.turnsFromStart()%2==0)) return "{\"error\": X it is not you turn now}";
            if (game.getUserIdWhoPlaysXandMakeTunrFirst()!=userid && (game.turnsFromStart()%2==1)) return "{\"error\": O it is not you turn now}";
        }
        //game.turnNumber++
        int currentMark;//ходит крестик(1) или нолик(2)
        if (userid==game.getUserIdWhoPlaysXandMakeTunrFirst())  currentMark=1; else currentMark=2;
        int[] currentSate = game.getField(); //текущее состояние поля
        if(currentSate[turn]!=0) return "{\"error\":you can't make this turn it's already taken}"; //клетка уже занята
        currentSate[turn]=currentMark; //делаю ход.
        if (game.turnsFromStart()==9) game.setGAMEOVER(true);//если уже сделано 9 ходов меняю статус игры
        return Arrays.toString(game.getField());





    }

    //================DEBUG=============================
    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    //Дебаг, тестирование чего-либо. http://localhost:8080/tic-tac-toe/debug
    public String debug(@RequestParam(value = "debug", defaultValue = "1") int debug) {
        String allUsers = null;
        StringBuilder sbUsers = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            sbUsers.append("<br>|users.id=" + i + " |user.name=" + users.get(i).getName());
            allUsers = sbUsers.toString();
        }

        String allGames = null;
        StringBuilder sbGames = new StringBuilder();
        for (int i = 0; i < games.size(); i++) {
            sbGames.append("<br>|games.id=" + i +
                    " |pl1=" + games.get(i).getUser1id() +
                    " |pl2=" + games.get(i).getUser2id() +
                    " |PoleAsString=" + games.get(i).getField());
            allGames = sbGames.toString();
        }

        return "<H3>debug USERS:</H3> " + users.toString() + allUsers + "<br>" +
                "<br>" +
                "<H3>debug GAMES:</H3> " + games.toString() + allGames + "<br>" +
                "<br>" +
                " _______________\n" + "<br>" +
                "< EnD oF DeBuG! >\n" + "<br>" +
                " ---------------------\n" + "<br>" +
                "";
    }

//    @RequestMapping(value="/greeting",method = RequestMethod.GET)  //@RequestMapping()
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "age", defaultValue = "537") int age) {
//
//        return new Greeting(counter.incrementAndGet(), String.format(template, name, age));
//    }
}//class//

//
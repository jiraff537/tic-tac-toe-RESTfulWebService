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
    private final AtomicInteger userCounter = new AtomicInteger();  //для уникальности id-шника
    private final AtomicInteger gameCounter = new AtomicInteger();  //для уникальности id-шника


    private SaveLoadDataAPI<User> users = new SaveLoadData<>();
    private SaveLoadDataAPI<Game> games = new SaveLoadData<>();
    //SaveLoadDataAPI<Turn> turns = new SaveLoadData(); //TODO impmement this!

    //регистрация пользователя http://localhost:8080/adduser?name=Alexei&code=11
    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public @ResponseBody
    User addUser(@RequestParam(value = "name") String name,
                 @RequestParam(value = "code") int code) {
        User user = new User();
        user.setId(userCounter.get());
        user.setName(name);
        user.setCode(code);
        users.add(user, userCounter.getAndIncrement());
        //String createStatus = user.checkPhayerExist(name, code);//статус при создании пользователя ОК или описание ошибки
        //System.out.println("userCounter.get()=" + userCounter.get()+" createStatus="+ createStatus); //log2console
        return user;
//                createStatus.equals("OK") ?
//                "userId=" + users.add(user, userCounter.getAndIncrement()):
//                createStatus;
    }

    //создание новой игры http://localhost:8080/creategame?player1id=1&player2id=2
    @RequestMapping(value = "/creategame", method = RequestMethod.GET)
    public @ResponseBody
    String createGame(@RequestParam(value = "player1id") int player1id,
                        @RequestParam(value = "player2id") int player2id) {
        Game game = new Game();
        if (!game.userExist(player1id, users)) return "{\"error\":User1 not registered}";
        if (!game.userExist(player2id, users)) return "{\"error\":user2 not registered}";
        if (!game.usersAreDifferent(player1id, player2id)) return "{\"error\":user cannot play with himself}";
        game.setId(gameCounter.get());
        game.setPlayer1id(player1id);
        game.setPlayer2id(player2id);
        int gameid=gameCounter.get();
        games.add(game, gameCounter.getAndIncrement());
        return "{\"gameid\":"+ gameid +"}";
    }

    //получить текущее состояние игрового поля http://localhost:8080//gamestate?gameid=1
    @RequestMapping(value = "/gamestate", method = RequestMethod.GET)
    public String getGameState(@RequestParam(value = "gameid") int gameId) {
        //TODO implement this in JSON

        return "gamestate=" + games.get(gameId).getField(); //TODO QQQ: wrap with Arrays.toString()
    }


    //================DEBUG=============================
    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    //Дебаг, тестирование чего-либо. http://localhost:8080/debug
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
                    " |pl1=" + games.get(i).getPlayer1id() +
                    " |pl2=" + games.get(i).getPlayer2id() +
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
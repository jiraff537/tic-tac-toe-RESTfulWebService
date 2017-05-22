package game.Controller;

import java.util.concurrent.atomic.AtomicInteger;

import game.Data.Game;
import game.SaveLoad.SaveLoadData;
import game.SaveLoad.SaveLoadDataAPI;
import game.Data.User;
import org.springframework.web.bind.annotation.*;

@RestController //=@Controller + @ResponseBody
public class Controller {

    //private static final String template = "Hello, %s %d!";

    //кажеться так правильнее:
    private final AtomicInteger userCounter = new AtomicInteger();  //добиваемся уникальности id-шника
    private final AtomicInteger gameCounter = new AtomicInteger();  //добиваемся уникальности id-шника


    SaveLoadDataAPI<User> users = new SaveLoadData<User>();
    SaveLoadDataAPI<Game> games = new SaveLoadData<Game>();
    //SaveLoadDataAPI<Turn> turns = new SaveLoadData(); //TODO impmement this!


    //регистрация пользователя http://localhost:8080/adduser?name=Alexei&code=11
    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addPlayer(@RequestParam(value = "name") String name,
                            @RequestParam(value = "code") String code) {
        User user = new User();
        String createStatus; //статус при создании пользователя ОК или описание ошибки
        createStatus = user.create(name, code);
        System.out.println("userCounter.get()=" + userCounter.get()); //log2console
        return createStatus.equals("OK") ?
                "userId=" + users.save(user, userCounter.getAndIncrement()):
                createStatus
                ; //уникальный id в сохраненном потоке где бы он не был в БД или простом ArrayList'е
    }

    //создание новой игры http://localhost:8080/creategame?player1id=1&player2id=2
    @RequestMapping(value = "/creategame", method = RequestMethod.GET)
    public String createGame(@RequestParam(value = "player1id") int player1id,
                             @RequestParam(value = "player2id") int player2id) {
        Game game = new Game(player1id, player2id);
        System.out.println("---------------------gameCounter.get()=" + gameCounter.get()); //log2console
        return "gameId=" + games.save(game, gameCounter.getAndIncrement()); //уникальный id в сохраненном потоке где бы он не был в БД или простом ArrayList'е
    }

    //получить текущее состояние игрового поля http://localhost:8080//gamestate?gameid=1
    @RequestMapping(value = "/gamestate", method = RequestMethod.GET)
    public String getGameState(@RequestParam(value = "gameid") int gameId) {

        return "gamestate=" + games.load(gameId).getPoleAsString();
    }


    //================DEBUG=============================
    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    //Дебаг, тестирование чего-либо. http://localhost:8080/debug
    public String debug(@RequestParam(value = "debug", defaultValue = "1") int debug) {
        String allUsers = null;
        StringBuilder sbUsers = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            sbUsers.append("<br>|users.id=" + i + " |user.name=" + users.load(i).getName());
            allUsers = sbUsers.toString();
        }

        String allGames = null;
        StringBuilder sbGames = new StringBuilder();
        for (int i = 0; i < games.size(); i++) {
            sbGames.append("<br>|games.id=" + i +
                    " |pl1=" + games.load(i).getPlayer1id() +
                    " |pl2=" + games.load(i).getPlayer2id() +
                    " |PoleAsString=" + games.load(i).getPoleAsString());
            allGames = sbGames.toString();
        }

        return "<H3>debug USERS:</H3> " + users.toString() + allUsers + "<br>" +
                "<br>" +
                "<H3>debug GAMES:</H3> " + games.toString() + allGames + "<br>" +
                "<br>" +


                " __________________\n" + "<br>" +
                "< конец-делу венец! >\n" + "<br>" +
                " --------------------------\n" + "<br>" +
                "";

    }


//    @RequestMapping(value="/greeting",method = RequestMethod.GET)  //@RequestMapping()
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "age", defaultValue = "537") int age) {
//
//        return new Greeting(counter.incrementAndGet(), String.format(template, name, age));
//    }
}//class//

//
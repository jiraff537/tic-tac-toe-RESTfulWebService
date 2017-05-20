package game.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import game.Data.Game;
import game.SaveLoad.SaveLoadData;
import game.SaveLoad.SaveLoadDataAPI;
import game.Data.User;
import org.springframework.web.bind.annotation.*;

@RestController //=@Controller + @ResponseBody
public class Controller {

    private static final String template = "Hello, %s %d!";

    // однажды это приложение может стать многопоточным...
    private final AtomicInteger userCounter = new AtomicInteger();  //добиваемся уникальности id-шника при паралельных запросах
    private final AtomicInteger gameCounter = new AtomicInteger();  //добиваемся уникальности id-шника при паралельных запросах


    SaveLoadDataAPI<User> users = new SaveLoadData();
    SaveLoadDataAPI<User> games = new SaveLoadData(); //TODO impmement this!
    SaveLoadDataAPI<User> turns = new SaveLoadData(); //TODO impmement this!

    //регистрация пользователя http://localhost:8080/adduser?name=Alexei&code=11
    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addplayer(@RequestParam(value = "name") String name,
                            @RequestParam(value = "code") int code) {
        User user = new User(name, code);
        return "userId=" + users.save(user, userCounter.incrementAndGet()); //уникальный id в сохраненном потоке где бы он не был в БД или простом ArrayList'е
    }

//    //создание новой игры http://localhost:8080/creategame?player1id=1&player2id=2
//    @RequestMapping(value = "/creategame", method = RequestMethod.GET)
//    public String addplayer(@RequestParam(value = "player1id") int player1id,
//                            @RequestParam(value = "player2id") int player2id) {
//        Game game = new Game(player1id, player2id);
//        return "gameId=" + games.save(game, gameCounter.incrementAndGet()); //уникальный id в сохраненном потоке где бы он не был в БД или простом ArrayList'е
//    }


    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    //Дебаг, тестирование чего-либо. http://localhost:8080/debug
    public String debug(@RequestParam(value = "debug", defaultValue = "1") int debug) {
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < users.size(); i++) {
            stringBuilder.append(" " + users.load(i).getName());
            result = stringBuilder.toString();
        }

        return users.toString() + result;
    }


//    @RequestMapping(value="/greeting",method = RequestMethod.GET)  //@RequestMapping()
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "age", defaultValue = "537") int age) {
//
//        return new Greeting(counter.incrementAndGet(), String.format(template, name, age));
//    }
}//class//

//
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
    private final AtomicLong counter = new AtomicLong();
    private final AtomicInteger userCounter = new AtomicInteger();


    List<Game> games = new ArrayList<>();

    SaveLoadDataAPI users = new SaveLoadData();


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    //регистрация пользователя http://localhost:8080/user?name=Alexei&code=12
    public String addplayer(@RequestParam(value = "name") String name,
                            @RequestParam(value = "code") int code) {
        User user = new User(userCounter.incrementAndGet(), name, code);
        return "userId=" + users.saveUser(user); //возвращю id пользователя в users
    }

    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    //Дебаг, тестирование чего-либо. http://localhost:8080/debug
    public String debug(@RequestParam(value = "debug", defaultValue = "1") int debug) {
        String result = null;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < users.userSize(); i++) {
            stringBuilder.append(" " + users.loadUser(i).getName());
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
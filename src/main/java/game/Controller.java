package game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController //=@Controller + @ResponseBody
public class Controller {

    private static final String template = "Hello, %s %d!";
    private final AtomicLong counter = new AtomicLong();

    List<User> users = new ArrayList<>();

    @RequestMapping(value="/user",method = RequestMethod.GET)  //@RequestMapping()
    public String addplayer(@RequestParam(value = "name") String name,
                            @RequestParam(value = "code") int code) {
        User user = new User(name,code);
        users.add(user);
        return "userId="+user.id.toString();
    }

//    @RequestMapping(value="/greeting",method = RequestMethod.GET)  //@RequestMapping()
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "age", defaultValue = "537") int age) {
//
//        return new Greeting(counter.incrementAndGet(), String.format(template, name, age));
//    }
}//class//

//http://localhost:8080/greeting?name=Alexei&code=12
package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController //=@Controller + @ResponseBody
public class GreetingController {

    private static final String template = "Hello, %s %d!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/greeting",method = RequestMethod.GET)  //@RequestMapping()
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "age", defaultValue = "537") int age) {

        return new Greeting(counter.incrementAndGet(), String.format(template, name, age));
    }
}
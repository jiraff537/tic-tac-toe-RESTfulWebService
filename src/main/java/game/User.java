package game;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jiraff537 on 5/19/17.
 */
public class User {
    AtomicLong id = new AtomicLong();
    String name;
    int code;

    public User(String name,int code) {
        id.incrementAndGet();
        this.name = name;
        this.code = code;


    }
}

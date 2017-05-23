package game.Data;

import game.utility.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jiraff537 on 5/19/17.
 * Класс Пользователь(Игрок) и его параметры и атрибуты
 */
public class User {
    private String name;
    private int code;

    public String create(String name, String code) {
        this.name = name;
        try {
            this.code = new Integer(code);
        } catch (NumberFormatException e) {
            return "ERROR " + e.getMessage() +
                    " field 'code' must be integer!" +
                    "<br><br>" + StringUtils.StackTraceAsString(e);//stackTrace это наверное перебор?
        }
        return "OK";
    }

    public String getName() {
        return name;
    }




}

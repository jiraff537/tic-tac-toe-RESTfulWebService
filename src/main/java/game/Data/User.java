package game.Data;

import game.utility.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jiraff537 on 5/19/17.
 * Класс Пользователь(Игрок) и его параметры и атрибуты
 */
public class User {
    private int id;
    private String name;
    private int code;

    public void create(int id,String name, int code) {
        this.id=id;
        this.name = name;
        this.code=code;
//        try {
//            this.code = new Integer(code);
//        } catch (NumberFormatException e) {
//            return "ERROR " + e.getMessage() +
//                    " field 'code' must be integer!" +
//                    "<br><br>" + StringUtils.StackTraceAsString(e);//stackTrace это наверное перебор?
//        }
//        return "OK";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

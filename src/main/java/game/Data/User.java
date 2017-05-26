package game.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by jiraff537 on 5/19/17.
 * Класс Пользователь(Игрок) и его параметры и атрибуты
 */
public class User {
    private int id;
    private String name;
    @JsonIgnore
    private int passwordhash;

    public void create(int id,String name, int passwordhash) {
        this.id=id;
        this.name = name;
        this.passwordhash =passwordhash;
//        try {
//            this.passwordhash = new Integer(passwordhash);
//        } catch (NumberFormatException e) {
//            return "ERROR " + e.getMessage() +
//                    " field 'passwordhash' must be integer!" +
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

    public int getPasswordhash() {
        return passwordhash;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswordhash(int passwordhash) {
        this.passwordhash = passwordhash;
    }
}

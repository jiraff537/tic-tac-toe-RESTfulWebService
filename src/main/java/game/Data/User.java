package game.Data;

import game.utility.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jiraff537 on 5/19/17.
 */
public class User {
    String name;
    int code;

    public String create(String name, String code) {
        this.name = name;
        try {
            this.code = new Integer(code);
        } catch (NumberFormatException e) {
            return "Error in field 'code="+code+"', it must be integer! " +e.getMessage()+"<br><br>"+ StringUtils.StackTraceAsString(e);
        }
        return "OK";
    }
//    public User(/*int id,*/ String name, int code) {
//        //this.id = id;
//        this.name = name;
//        this.code = code;
//    }

//    public String toString (){
//
//        return "___";
//    }


//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}

package game.utility;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * This is Created by jiraff537 on 5/22/17.
 * Вспомогательный класс для всяких мелких поручений чтобы не дублировать код.
 */
//@Deprecated
public class StringUtils {

    public static String StackTraceAsString(Exception e){ //convert a stack trace to a string
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }
}

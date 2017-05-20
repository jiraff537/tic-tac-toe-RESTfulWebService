package game.Data;

@Deprecated

public class Greeting { //Класс из туториала https://spring.io/guides/gs/rest-service/

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}//class
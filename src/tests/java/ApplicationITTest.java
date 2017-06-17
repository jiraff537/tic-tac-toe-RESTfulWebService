import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;


/**
 * Интеграционный тест java REST работает при локально запущенном приложении
 */

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.simbirsoft.tictactoe.Application.class)

public class ApplicationITTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void EmulateGameOfTwoPlayers() throws Exception {
        Response resp;
        //POST //регистрация 2х пользователей Alexei Elena
        resp = post("/tic-tac-toe/adduser?name=Alexei&passwordhash=11");
        assertEquals(resp.asString(), "{\"id\":0,\"name\":\"Alexei\"}");
        resp = post("/tic-tac-toe/adduser?name=Elena&passwordhash=22");
        assertEquals(resp.asString(), "{\"id\":1,\"name\":\"Elena\"}");
        //POST //создание игры между пользователями id=0 и  id=1
        resp = post("/tic-tac-toe/creategame?user1id=0&user2id=1");
        assertEquals(resp.asString(), "{\"gameid\":0}");
        //GET //получаем состояние игры
        resp = get("/tic-tac-toe/getstatus?gameid=0");
        assertEquals(resp.asString(), "{\"win\":0}");

        //POST //делаем ходы по очереди X , O (крестики победят)
        resp = post("/tic-tac-toe/maketurn?gameid=0&userid=0&turn=4"); //X
        assertEquals(resp.asString(), "[0, 0, 0, 0, 1, 0, 0, 0, 0]");
        resp = post("/tic-tac-toe/maketurn?gameid=0&userid=1&turn=8"); //O
        assertEquals(resp.asString(), "[0, 0, 0, 0, 1, 0, 0, 0, 2]");

        resp = post("/tic-tac-toe/maketurn?gameid=0&userid=0&turn=0"); //X
        assertEquals(resp.asString(), "[1, 0, 0, 0, 1, 0, 0, 0, 2]");
        resp = post("/tic-tac-toe/maketurn?gameid=0&userid=1&turn=7"); //O
        assertEquals(resp.asString(), "[1, 0, 0, 0, 1, 0, 0, 2, 2]");

        resp = post("/tic-tac-toe/maketurn?gameid=0&userid=0&turn=6"); //X
        assertEquals(resp.asString(), "[1, 0, 0, 0, 1, 0, 1, 2, 2]");
        resp = post("/tic-tac-toe/maketurn?gameid=0&userid=1&turn=2"); //O
        assertEquals(resp.asString(), "[1, 0, 2, 0, 1, 0, 1, 2, 2]");

        resp = post("/tic-tac-toe/maketurn?gameid=0&userid=0&turn=3"); //X
        assertEquals(resp.asString(), "[1, 0, 2, 1, 1, 0, 1, 2, 2]");

        //GET //получаем состояние игры победил пользователь  id=0 (крестики)
        resp = get("/tic-tac-toe/getstatus?gameid=0");
        assertEquals(resp.asString(), "{\"win\":1}");

        //GET //получаем состояние поля
        resp = get("/tic-tac-toe/fieldstate?gameid=0");
        assertEquals(resp.asString(), "[1,0,2,1,1,0,1,2,2]");

    }

    @Ignore
    @Test
    public void ApplicationIT() {
        expect().statusCode(200)
                .when().post("/tic-tac-toe/adduser?name=Alexei&passwordhash=11")
                .then().contentType(ContentType.JSON) //.JSON
                .extract().response();
        get("/tic-tac-toe/getstatus?gameid=0").then().body(equalTo("{\"win\":0}"));
    }

    @Ignore
    @Test
    public void shouldSaveABook() {
        //       /tic-tac-toe/adduser?name=Alexei&passwordhash=11
        //expect().given().body("?name=Alexei&passwordhash=11").post("/tic-tac-toe/adduser");
        //expect().given().post("/tic-tac-toe/adduser?name=Alexei&passwordhash=11");
        expect().contentType(ContentType.JSON).given().post("/tic-tac-toe/adduser?name=Alexei&passwordhash=11");
        //expect().response().given().post("/tic-tac-toe/adduser?name=Alexei&passwordhash=11");


        //.statusCode(400).when().post("/tic-tac-toe/adduser");
        //  .given().contentType("application/json;charset=UTF-8").body("?name=Alexei&passwordhash=11").post();
    }


    @Ignore
    @Test
    public void getRequestFindCapital() throws Exception {

        // выполняем запрос get для доступа ко всем параметрам ответа
        Response resp = get("http://restcountries123.eu/rest/v1/name/ukraine");

        JSONArray jsonResponse = new JSONArray(resp.asString());

        // получение параметра capital (столицы Норвегии)
        String capital = jsonResponse.getJSONObject(0).getString("capital");

        // проверка, что столицей является Осло
        assertEquals(capital, "Kiev");
    }


}//class
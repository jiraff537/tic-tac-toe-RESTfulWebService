package com.simbirsoft.tictactoe;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static org.junit.Assert.assertEquals;


/**
 * Интеграционный тест REST-бекэнда
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void EmulateGameOfTwoPlayers() throws Exception {
        final String baseURL;
        baseURL = "http://localhost:" + port + "/tic-tac-toe/";

        Response resp;

        //POST //регистрация 2х пользователей Alexei Elena
        resp = post(baseURL+"adduser?name=Alexei&passwordhash=11");
        assertEquals(resp.asString(), "{\"id\":0,\"name\":\"Alexei\"}");
        resp = post(baseURL+"adduser?name=Elena&passwordhash=22");
        assertEquals(resp.asString(), "{\"id\":1,\"name\":\"Elena\"}");

        //POST //создание игры между пользователями id=0 и  id=1
        resp = post(baseURL+"creategame?user1id=0&user2id=1");
        assertEquals(resp.asString(), "{\"gameid\":0}");

        //GET //получаем состояние игры
        resp = get(baseURL+"getstatus?gameid=0");
        assertEquals(resp.asString(), "{\"win\":0}");

        //POST //делаем ходы по очереди X , O (крестики победят)
        resp = post(baseURL+"maketurn?gameid=0&userid=0&turn=4"); //X
        assertEquals(resp.asString(), "[0, 0, 0, 0, 1, 0, 0, 0, 0]");
        resp = post(baseURL+"maketurn?gameid=0&userid=1&turn=8"); //O
        assertEquals(resp.asString(), "[0, 0, 0, 0, 1, 0, 0, 0, 2]");

        resp = post(baseURL+"maketurn?gameid=0&userid=0&turn=0"); //X
        assertEquals(resp.asString(), "[1, 0, 0, 0, 1, 0, 0, 0, 2]");
        resp = post(baseURL+"maketurn?gameid=0&userid=1&turn=7"); //O
        assertEquals(resp.asString(), "[1, 0, 0, 0, 1, 0, 0, 2, 2]");

        resp = post(baseURL+"maketurn?gameid=0&userid=0&turn=6"); //X
        assertEquals(resp.asString(), "[1, 0, 0, 0, 1, 0, 1, 2, 2]");
        resp = post(baseURL+"maketurn?gameid=0&userid=1&turn=2"); //O
        assertEquals(resp.asString(), "[1, 0, 2, 0, 1, 0, 1, 2, 2]");

        resp = post(baseURL+"maketurn?gameid=0&userid=0&turn=3"); //X
        assertEquals(resp.asString(), "[1, 0, 2, 1, 1, 0, 1, 2, 2]");

        //GET //получаем состояние игры победил пользователь  id=0 (крестики)
        resp = get(baseURL+"getstatus?gameid=0");
        assertEquals(resp.asString(), "{\"win\":1}");

        //GET //получаем состояние поля
        resp = get(baseURL+"fieldstate?gameid=0");
        assertEquals(resp.asString(), "[1,0,2,1,1,0,1,2,2]");
    }
}//class
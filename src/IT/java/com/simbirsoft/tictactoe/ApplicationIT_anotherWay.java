package com.simbirsoft.tictactoe;

import static io.restassured.RestAssured.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/*
* Интеграционный тест
* */

@Deprecated
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIT_anotherWay {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void EmulateGameOfTwoPlayers() throws Exception {
        final String baseURL;
        baseURL = "http://localhost:" + port + "/tic-tac-toe/";
        System.out.println("baseURL="+baseURL);

        //POST //регистрация 2х пользователей Alexei Elena
        assertThat(this.restTemplate.postForObject(baseURL+"adduser/?name=Alexei&passwordhash=11","",
                String.class)).isEqualTo("{\"id\":0,\"name\":\"Alexei\"}");
        assertThat(this.restTemplate.postForObject(baseURL+"adduser/?name=Elena&passwordhash=22","",
                String.class)).isEqualTo("{\"id\":1,\"name\":\"Elena\"}");

        //POST //создание игры между пользователями id=0 и  id=1
        assertThat(this.restTemplate.postForObject(baseURL+"creategame?user1id=0&user2id=1","",
                String.class)).isEqualTo("{\"gameid\":0}");

        //GET //получаем состояние игры
        assertThat(this.restTemplate.getForObject(baseURL+"getstatus?gameid=0",
                String.class)).isEqualTo("{\"win\":0}");

        //POST //делаем ходы по очереди X , O (крестики победят)
        Response resp;
        resp = post(baseURL+"maketurn?gameid=0&userid=0&turn=4"); //X
        assertEquals(resp.asString(), "[0, 0, 0, 0, 1, 0, 0, 0, 0]");




    }
}
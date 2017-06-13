package com.simbirsoft.tictactoe.Controller;

import com.simbirsoft.tictactoe.Data.Game;
import com.simbirsoft.tictactoe.Data.User;
import com.simbirsoft.tictactoe.SaveLoad.SaveLoadData;
import com.simbirsoft.tictactoe.SaveLoad.SaveLoadDataAPI;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by jiraff537 on 6/11/17.
 */
public class ControllerTest {

    private Controller controller;
    private Game game;
    private User user;
    private SaveLoadDataAPI<User> users;
    private SaveLoadDataAPI<Game> games;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("--> Before ControllerTest.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("<-- After ControllerTest.class");
    }

    @Before
    public void setUp() throws Exception {
        controller = new Controller();
        game = new Game();
        user = new User();
        users = new SaveLoadData<>();
        games = new SaveLoadData<>();
    }

    @After
    public void tearDown() throws Exception {
        controller = null;
        game = null;
        user = null;
        users = null;
        games = null;
    }

    @Test
    public void addUser() throws Exception {
        user = controller.addUser("John Doe", 537);
        //---проверка возварщаемого значения при добавлении пользователя.
        assertEquals(user.getId(), 0);
        assertEquals(user.getName(), "John Doe");
        assertEquals(user.getPasswordhash(), 537);
        //проверка что id-шник инкрементируется при добавлении следующего пользователя
        user = null;
        user = controller.addUser("Joann Doe", 538);
        assertTrue(user.getId() == 1);
    }

    @Test
    public void createGame() throws Exception {
        //---проверка возварщаемого значения при создании игры между пользователями
        controller.addUser("John Doe", 537);
        controller.addUser("Joann Doe", 538);
        String res = controller.createGame(0, 1);
        assertEquals("{\"gameid\":0}", res);
    }

    @Test
    public void getFieldState() throws Exception {
        //---проверка возварщаемого значения при запросе состояния поля
        controller.addUser("John Doe", 537);
        controller.addUser("Joann Doe", 538);
        controller.createGame(0, 1);
        //проверка совпадает ли пустое поле
        int[] fieldBlank = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] resBlank = controller.getFieldState(0);
        assertEquals(Arrays.toString(fieldBlank), Arrays.toString(resBlank));
        //проверка совпадает ли поле после совершения хотябы одного хода
        controller.makeTurn(0, 0, 4); //совершаем ход
        int[] fieldAfterTurn = {0, 0, 0, 0, 1, 0, 0, 0, 0};
        int[] resNotBlank = controller.getFieldState(0);
        assertEquals(Arrays.toString(fieldAfterTurn), Arrays.toString(resNotBlank));
    }

    @Test
    public void makeTurn() throws Exception {
        //---проверка возварщаемого значения при совершении хода
        controller.addUser("John Doe", 537);
        controller.addUser("Joann Doe", 538);
        controller.createGame(0, 1);
        //проверяем до хода
        int[] fieldBeforeTurn = {0, 0, 0, 0, 0, 0, 0, 0, 0}; //ожидаемое состояние поля
        assertEquals(Arrays.toString(fieldBeforeTurn), Arrays.toString(controller.getFieldState(0)));
        //делаем ход
        String resFieldAfterTurn = controller.makeTurn(0,0,4);
        int[] fieldAfterTurn = {0, 0, 0, 0, 1, 0, 0, 0, 0}; //ожидаемое состояние поля
        //проверяем после хода
        assertEquals(Arrays.toString(fieldAfterTurn), resFieldAfterTurn);
    }


    @Test
    public void getstatusWinX() throws Exception {
        //---проверка возварщаемого значения при получении статуса игры (победа(X|O)|ничья|можно дальше играть)
        controller.addUser("John Doe", 537);
        controller.addUser("Joann Doe", 538);
        controller.createGame(0, 1);
        //проверка победы крестиков
        controller.makeTurn(0,0,4);
        controller.makeTurn(0,1,5);
        controller.makeTurn(0,0,1);
        controller.makeTurn(0,1,2);
        controller.makeTurn(0,0,7);
        //System.out.println("X"+Arrays.toString(controller.getFieldState(0))); // {0, 1, 2, 0, 1, 2, 0, 1, 0}; //ожидаемое состояние поля
        assertEquals("{\"win\":1}",controller.getstatus(0));
        //проверка победы ноликов
        controller.createGame(0, 1);
        controller.makeTurn(1,0,4);
        controller.makeTurn(1,1,5);
        controller.makeTurn(1,0,3);
        controller.makeTurn(1,1,8);
        controller.makeTurn(1,0,0);
        controller.makeTurn(1,1,2);
        //System.out.println("O"=Arrays.toString(controller.getFieldState(1))); // {1, 0, 2, 1, 1, 2, 0, 0, 2}; //ожидаемое состояние поля
        assertEquals("{\"win\":2}",controller.getstatus(1));
        //проверка ничьи
        controller.createGame(0, 1);
        controller.makeTurn(2,0,4);
        controller.makeTurn(2,1,5);
        controller.makeTurn(2,0,6);
        controller.makeTurn(2,1,7);
        controller.makeTurn(2,0,8);
        controller.makeTurn(2,1,0);
        controller.makeTurn(2,0,1);
        controller.makeTurn(2,1,2);
        controller.makeTurn(2,0,3);
        //System.out.println(Arrays.toString(controller.getFieldState(2))); //{2, 1, 2, 1, 1, 2, 1, 2, 1}; //ожидаемое состояние поля
        assertEquals("{\"win\":draw tie}",controller.getstatus(2));
        //проверка того что игра может быть продолжена
        controller.createGame(0, 1);
        controller.makeTurn(3,0,4);
        controller.makeTurn(3,1,5);
        controller.makeTurn(3,0,6);
        controller.makeTurn(3,1,7);
        controller.makeTurn(3,0,8);
        controller.makeTurn(3,1,0);
        controller.makeTurn(3,0,1);
        controller.makeTurn(3,1,2);
        //System.out.println(Arrays.toString(controller.getFieldState(3))); //{2, 1, 2, 0, 1, 2, 1, 2, 1}; //ожидаемое состояние поля
        assertEquals("{\"win\":0}",controller.getstatus(3));
    }

    @Ignore("There is no need to test debug method in Controller.class")
    @Test
    public void debug() throws Exception {
    }

}//class










































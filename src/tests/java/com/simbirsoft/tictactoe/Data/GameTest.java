package com.simbirsoft.tictactoe.Data;

import com.simbirsoft.tictactoe.SaveLoad.SaveLoadData;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by jiraff537 on 6/10/17.
 * unit test for Game.class
 */
public class GameTest {

    private Game game;
    private User user;
    private SaveLoadData<User> users;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("--> Before GameTest.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("<-- After GameTest.class");
    }

    @Before
    public void setUp() throws Exception {
        game = new Game();
        user = new User();
        users = new SaveLoadData<>();
    }

    @After
    public void tearDown() throws Exception {
        game = null;
        user = null;
        users = null;
    }

    @Test
    public void userExist() throws Exception {

        user.setId(0);
        user.setName("Name");
        user.setPasswordhash(537);
        users.add(user,0);
        //System.out.println("userExist");
        //assertTrue(false);
        assertTrue(game.userExist(0,users));
        assertFalse(game.userExist(1,users));
    }

    @Test
    public void usersAreDifferent() throws Exception {
        assertTrue(game.usersAreDifferent(0,1));
        assertFalse(game.usersAreDifferent(0,0));
    }

    @Test
    public void fieldAsString() throws Exception {

        int[] field = {0, 0, 0,
                0, 0, 0,
                0, 0, 0};
        game.setField(field);
        assertEquals("[0, 0, 0, 0, 0, 0, 0, 0, 0]",game.FieldAsString());
    }

    @Test
    public void turnFromStart() throws Exception { //какой сейчас ход если сделано три? четвертый!
        int[] field = {2, 0, 0,
                0, 1, 0,
                0, 0, 1};
        game.setField(field);
        assertTrue(4==game.turnFromStart());
    }

    //Геттеры и сеттеры не тестирую

    @Ignore("Getter/Setter no need to test it")
    @Test
    public void getId() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void getUser1id() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void getUser2id() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void getField() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setId() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setUser1id() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setUser2id() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setField() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void isCRAETED() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setCRAETED() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void isGAMEOVER() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setGAMEOVER() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void getUserIdWhoPlaysXandMakeTunrFirst() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setUserIdWhoPlaysXandMakeTunrFirst() throws Exception {
    }

}
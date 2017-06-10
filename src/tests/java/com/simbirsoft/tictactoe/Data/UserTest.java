package com.simbirsoft.tictactoe.Data;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by jiraff537 on 6/10/17.
 */
public class UserTest {
    private User user;
    @BeforeClass
    public static void beforeClass() {
        System.out.println("--> Before UserTest.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("<-- After UserTest.class");
    }
    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void create() throws Exception {
        user.setId(0);
        user.setName("John Doe");
        user.setPasswordhash(537);
        User user1 = new User();
        user1.create(0,"John Doe",537);
        assertTrue(user.getId()==user1.getId());
        assertTrue(user.getName()==user1.getName());
        assertTrue(user.getPasswordhash()==user1.getPasswordhash());
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void getId() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void getName() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void getPasswordhash() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setId() throws Exception {
    }
    @Ignore("Getter/Setter no need to test it")
    @Test
    public void setName() throws Exception {
    }

    @Test@Ignore("Getter/Setter no need to test it")
    public void setPasswordhash() throws Exception {
    }

}
package com.simbirsoft.tictactoe.Controller;

import com.simbirsoft.tictactoe.Application;
import org.junit.runner.RunWith;
//import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Controller.class)
@WebIntegrationTest
public class ControllerIT {

public void ControllersampleIT(){
    System.out.println("16-54");
}
}//ITest

//Клестики нолики Классический вариант: https://ru.wikipedia.org/wiki/%D0%9A%D1%80%D0%B5%D1%81%D1%82%D0%B8%D0%BA%D0%B8-%D0%BD%D0%BE%D0%BB%D0%B8%D0%BA%D0%B8
// -Игроки по очереди ставят на свободные клетки поля 3х3 знаки (один всегда крестики, другой всегда нолики).
// -Первый, выстроивший в ряд 3 своих фигуры по вертикали, горизонтали или диагонали, выигрывает.
// -Первый ход делает игрок, ставящий крестики.

package com.simbirsoft.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}

//run local:
//mvn spring-boot:run

// build the JAR file ./mvn clean package
//java -jar target/gs-rest-service-0.1.0.jar

// если вдруг Failed to start end point associated with ProtocolHandler [http-nio-8080] то:
//  lsof -i :<portNumber> | grep LISTEN
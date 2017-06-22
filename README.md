# tic-tac-toe-RESTfulWebService
tic-tac-toe-RESTfulWebService

гитхаб:
git clone https://github.com/jiraff537/tic-tac-toe-RESTfulWebService.git

Запустить приложение можно следующим образом:

локально:
```
mvn spring-boot:run
```

или создать JAR файл:
```
./mvn clean package
```
запустить уже его:
```
java -jar target/tictactoe-0.1.0.jar
```
Unit-тесты тут:
```
../tic-tac-toe-RESTfulWebService/src/tests/java/com/simbirsoft/tictactoe
```

Интеграционный тест имитриующий игру двух игроков тут:
```
../tic-tac-toe-RESTfulWebService/src/IT/java/com/simbirsoft/tictactoe/ApplicationIT.java
```

если вдруг ошибка "Failed to start end point associated with ProtocolHandler [http-nio-8080]"
значит что-то у вас уже бежит на порту 8080 узнать что именно можно так:
```
lsof -i :8080 | grep LISTEN
```
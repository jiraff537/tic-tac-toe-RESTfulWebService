#!/bin/bash
clear
STR="Hello World!"
echo $STR
echo

#user1="Alexei" #переменные #echo user1= $user1

#POST addusers ------
echo curl -d "name=Alexei&passwordhash=11" http://localhost:8080/tic-tac-toe/adduser && echo -n "---"
curl -d "name=Alexei&passwordhash=11" http://localhost:8080/tic-tac-toe/adduser   && echo

echo curl -d "name=Elena&passwordhash=22" http://localhost:8080/tic-tac-toe/adduser && echo -n "---"
curl -d "name=Elena&passwordhash=22" http://localhost:8080/tic-tac-toe/adduser && echo
  
#POST creategame ------ id 0 w/ id 1
echo curl -d "user1id=0&user2id=1" http://localhost:8080/tic-tac-toe/creategame && echo -n "---"
curl -d "user1id=0&user2id=1" http://localhost:8080/tic-tac-toe/creategame && echo

#GET get state ------ gameid=0
echo curl http://localhost:8080/tic-tac-toe/gamestate?gameid=0 && echo -n "---"
curl http://localhost:8080/tic-tac-toe/gamestate?gameid=0 && echo

##

#POST maketurns ------ gameid=0 userid->turn = 0->4 1->2 0->3 1->5
echo curl -d "gameid=0&userid=0&turn=4" http://localhost:8080/tic-tac-toe/maketurn && echo -n "--->"
curl -d "gameid=0&userid=0&turn=4" http://localhost:8080/tic-tac-toe/maketurn && echo
curl -d "gameid=0&userid=1&turn=2" http://localhost:8080/tic-tac-toe/maketurn && echo
curl -d "gameid=0&userid=0&turn=3" http://localhost:8080/tic-tac-toe/maketurn && echo
curl -d "gameid=0&userid=1&turn=5" http://localhost:8080/tic-tac-toe/maketurn && echo
curl -d "gameid=0&userid=0&turn=1" http://localhost:8080/tic-tac-toe/maketurn && echo




#debug GET ------
echo
echo curl 'http://localhost:8080/tic-tac-toe/debug' -n "__"
curl 'http://localhost:8080/tic-tac-toe/debug'
echo
#cowsay ------
cowsay -d конец-делу венец!
#cowsay -bdgpstwy text message
#random ------
DIV=$((8+1))
R=$(($RANDOM%$DIV))
echo Today RANDOM = $R

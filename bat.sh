#!/bin/bash
STR="Hello World!"
echo $STR
echo

#user1="Alexei" #переменные #echo user1= $user1

#add users POST ------
echo curl -d "name=Alexei&passwordhash=11" http://localhost:8080/tic-tac-toe/adduser
echo -n "__"
curl -d "name=Alexei&passwordhash=11" http://localhost:8080/tic-tac-toe/adduser
echo  

echo curl -d "name=Elena&passwordhash=22" http://localhost:8080/tic-tac-toe/adduser
echo -n "__"
curl -d "name=Elena&passwordhash=22" http://localhost:8080/tic-tac-toe/adduser
echo  
#creategame ------ id 0 w/ id 1
echo curl -d "player1id=0&player2id=1" http://localhost:8080/tic-tac-toe/creategame
echo -n "__"
curl -d "player1id=0&player2id=1" http://localhost:8080/tic-tac-toe/creategame
echo
#debug GET ------
echo curl 'http://localhost:8080/tic-tac-toe/debug'
echo -n "__"
curl 'http://localhost:8080/tic-tac-toe/debug'
echo
#cowsay ------
cowsay -d конец-делу венец!
#cowsay -bdgpstwy text message
#random ------
DIV=$((8+1))
R=$(($RANDOM%$DIV))
echo Today RANDOM = $R

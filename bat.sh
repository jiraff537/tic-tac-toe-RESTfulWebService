#!/bin/bash
STR="Hello World!"
echo $STR
echo
#name users
user1="Alexei"
echo user1= $user1
echo 
user2="Elena"
echo user2= $user2
echo 

#add users w/name above
echo curl 'http://localhost:8080/adduser?name='$user1'&code=11'
echo -n "--"
curl 'http://localhost:8080/adduser?name='$user1'&code=11'
echo  

echo curl 'http://localhost:8080/adduser?name='$user2'&code=11'
echo -n "--"
curl 'http://localhost:8080/adduser?name='$user2'&code=11'
echo  
#creategame ------ id 0 w/ id 1
echo curl 'http://localhost:8080/creategame?player1id=0&player2id=1'
echo -n "--"
curl 'http://localhost:8080/creategame?player1id=0&player2id=1'
echo
#debug ------
echo curl 'http://localhost:8080/debug'
echo -n "--"
curl 'http://localhost:8080/debug'
echo
#cowsay ------
cowsay -d конец-делу венец!
#cowsay -bdgpstwy text message
#random ------
DIV=$((8+1))
R=$(($RANDOM%$DIV))
echo Today RANDOM = $R

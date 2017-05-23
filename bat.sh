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
curl 'http://localhost:8080/adduser?name='$user1'&code=11'
echo  

echo curl 'http://localhost:8080/adduser?name='$user2'&code=11'
curl 'http://localhost:8080/adduser?name='$user2'&code=11'
echo  

echo curl 'http://localhost:8080/creategame?player1id=1&player2id=2'
curl 'http://localhost:8080/creategame?player1id=1&player2id=2'
echo

echo curl 'http://localhost:8080/debug'
curl 'http://localhost:8080/debug'
echo
cowsay -d конец-делу венец!
#cowsay -bdgpstwy text message

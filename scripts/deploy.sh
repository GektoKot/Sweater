#!/usr/bin/env bash

#mvn -f pom.xml clean package -U

#echo 'Copy files...'

#scp target/sweater-1.0-SNAPSHOT.jar ad@192.168.0.89:/home/ad/

#echo 'Restart server...'

#ssh -tt -i ~/.ssh/id_rsa ad@192.168.0.89 << EOF
#
##pgrep java | xargs kscill -9
#nohup java -jar sweater-1.0-SNAPSHOT.jar > log.txt &
#
#EOF

#echo 'Bye'

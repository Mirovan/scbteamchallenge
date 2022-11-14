nohup java -jar /opt/bigint/web-0.0.1-SNAPSHOT.jar --spring.config.location=file:///opt/bigint/application-prom.properties > my.log 2>&1 &
echo $! > save_pid.txt
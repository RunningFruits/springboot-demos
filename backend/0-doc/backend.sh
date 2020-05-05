#!/bin/sh
export BOOT_WEB=boot-web.jar.jar

export BOOT_WEB_port=8082

case "$1" in

start)
        ## 启动BOOT_WEB
        echo "--------BOOT_WEB 开始启动--------------"
        nohup java -Xms512m -Xmx512m -jar $BOOT_WEB >/dev/null 2>&1 &
        BOOT_WEB_pid=`lsof -i:$BOOT_WEB_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$BOOT_WEB_pid" ]
            do
              BOOT_WEB_pid=`lsof -i:$BOOT_WEB_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "BOOT_WEB pid is $BOOT_WEB_pid"
        echo "--------BOOT_WEB 启动成功--------------"

        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $BOOT_WEB | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===BOOT_WEB process not exists or stop success"
        else
            kill -9 $P_ID
            echo "BOOT_WEB killed success"
        fi

        echo "===stopAll success==="
        ;;

restart)
        $0 stop
        sleep 2
        $0 start
        echo "===restartAll success==="
        ;;
esac
exit 0

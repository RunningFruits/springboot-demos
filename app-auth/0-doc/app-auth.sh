#!/bin/sh
export APP_AUTH=app-auth-0.0.1-SNAPSHOT.jar


export APP_AUTH_port=9080


case "$1" in

start)
        ## 启动APP_AUTH
        echo "--------APP_AUTH 开始启动--------------"
        java -Xms512m -Xmx512m -jar $APP_AUTH &
        APP_AUTH_pid=`lsof -i:$APP_AUTH_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$APP_AUTH_pid" ]
            do
              APP_AUTH_pid=`lsof -i:$APP_AUTH_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "APP_AUTH pid is $APP_AUTH_pid"
        echo "--------APP_AUTH 启动成功--------------"

        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $APP_AUTH | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===APP_AUTH process not exists or stop success"
        else
            kill -9 $P_ID
            echo "APP_AUTH killed success"
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

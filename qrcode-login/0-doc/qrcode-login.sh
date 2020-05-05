#!/bin/sh
export QRCODE_LOGIN=qrcode-login-0.0.1-SNAPSHOT.jar

export QRCODE_LOGIN_port=8092

case "$1" in

start)
        ## 启动qrcode-login
        echo "--------qrcode-login 开始启动--------------"
        java -Xms512m -Xmx512m -jar $QRCODE_LOGIN &
        QRCODE_LOGIN_pid=`lsof -i:$QRCODE_LOGIN_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$QRCODE_LOGIN_pid" ]
            do
              QRCODE_LOGIN_pid=`lsof -i:$QRCODE_LOGIN_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "QRCODE_LOGIN pid is $QRCODE_LOGIN_pid"
        echo "--------qrcode-login 启动成功--------------"

        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $QRCODE_LOGIN | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===QRCODE_LOGIN process not exists or stop success"
        else
            kill -9 $P_ID
            echo "QRCODE_LOGIN killed success"
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

#!/bin/sh
export FILEUPLOAD=fileupload-0.0.1-SNAPSHOT.jar

export FILEUPLOAD_port=8080

case "$1" in

start)
        ## 启动fileupload
        echo "--------fileupload 开始启动--------------"
        java -Xms512m -Xmx512m -jar $FILEUPLOAD &
        FILEUPLOAD_pid=`lsof -i:$FILEUPLOAD_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$FILEUPLOAD_pid" ]
            do
              FILEUPLOAD_pid=`lsof -i:$FILEUPLOAD_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "FILEUPLOAD pid is $FILEUPLOAD_pid"
        echo "--------fileupload 启动成功--------------"

        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $FILEUPLOAD | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===FILEUPLOAD process not exists or stop success"
        else
            kill -9 $P_ID
            echo "FILEUPLOAD killed success"
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

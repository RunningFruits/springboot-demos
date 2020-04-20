#!/bin/sh
export EUREKA=eureka-0.0.1-SNAPSHOT.jar
export GZGL=gzgl-server-0.0.1-SNAPSHOT.jar
export COLLECTER=ww-collecter-1.0.0-SNAPSHOT.jar
export WWZS=ROOT.war
export ZDMB=zdmb-server-0.0.1-SNAPSHOT.jar
export XTSZ=xtsz-server-0.0.1-SNAPSHOT.jar
export QBSJ=qbsj-server-0.0.1-SNAPSHOT.jar
export FILE=file-server-0.0.1-SNAPSHOT.jar
export PTAQ=ptaq-server-0.0.1-SNAPSHOT.jar
export QYXZ=qyxz-server-0.0.1-SNAPSHOT.jar
export FXPG=fxpg-server-0.0.1-SNAPSHOT.jar
export OPEN=open-server-0.0.1-SNAPSHOT.jar

export EUREKA_port=8081
export GZGL_port=8082
export COLLECTER_port=8083
export WWZS_port=8084
export ZDMB_port=8085
export XTSZ_port=8086
export QBSJ_port=8087
export FILE_port=8088
export PTAQ_port=8089
export QYXZ_port=8090
export FXPG_port=8091
export OPEN_port=8092

case "$1" in

start)
        ## 启动eureka
        echo "--------eureka 开始启动--------------"
        nohup java -jar $EUREKA >/dev/null 2>&1 &
        EUREKA_pid=`lsof -i:$EUREKA_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$EUREKA_pid" ]
            do
              EUREKA_pid=`lsof -i:$EUREKA_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "EUREKA pid is $EUREKA_pid"
        echo "--------eureka 启动成功--------------"

        ## 启动GZGL
        echo "--------开始启动GZGL---------------"
        nohup java -jar $GZGL >/dev/null 2>&1 &
        GZGL_pid=`lsof -i:$GZGL_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$GZGL_pid" ]
            do
              GZGL_pid=`lsof -i:$GZGL_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "GZGL pid is $GZGL_pid"
        echo "---------GZGL 启动成功-----------"

		## 启动COLLECTER
        echo "--------开始启动COLLECTER---------------"
        nohup java -jar $COLLECTER >/dev/null 2>&1 &
        COLLECTER_pid=`lsof -i:$COLLECTER_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$COLLECTER_pid" ]
            do
              COLLECTER_pid=`lsof -i:$COLLECTER_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "COLLECTER pid is $COLLECTER_pid"
        echo "---------COLLECTER 启动成功-----------"

		## 启动ZDMB
        echo "--------开始启动ZDMB---------------"
        nohup java -jar $ZDMB >/dev/null 2>&1 &
        ZDMB_pid=`lsof -i:$ZDMB_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$ZDMB_pid" ]
            do
              ZDMB_pid=`lsof -i:$ZDMB_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "ZDMB pid is $ZDMB_pid"
        echo "---------ZDMB 启动成功-----------"

		## 启动XTSZ
        echo "--------开始启动XTSZ---------------"
        nohup java -jar $XTSZ >/dev/null 2>&1 &
        XTSZ_pid=`lsof -i:$XTSZ_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$XTSZ_pid" ]
            do
              XTSZ_pid=`lsof -i:$XTSZ_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "XTSZ pid is $XTSZ_pid"
        echo "---------XTSZ 启动成功-----------"

		## 启动QBSJ
        echo "--------开始启动QBSJ---------------"
        nohup java -jar $QBSJ >/dev/null 2>&1 &
        QBSJ_pid=`lsof -i:$QBSJ_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$QBSJ_pid" ]
            do
              QBSJ_pid=`lsof -i:$QBSJ_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "QBSJ pid is $QBSJ_pid"
        echo "---------QBSJ 启动成功-----------"

		## 启动FILE
        echo "--------开始启动FILE---------------"
        nohup java -jar $FILE >/dev/null 2>&1 &
        FILE_pid=`lsof -i:$FILE_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$FILE_pid" ]
            do
              FILE_pid=`lsof -i:$FILE_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "FILE pid is $FILE_pid"
        echo "---------FILE 启动成功-----------"

		## 启动PTAQ
        echo "--------开始启动PTAQ---------------"
        nohup java -jar $PTAQ >/dev/null 2>&1 &
        PTAQ_pid=`lsof -i:$PTAQ_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$PTAQ_pid" ]
            do
              PTAQ_pid=`lsof -i:$PTAQ_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "PTAQ pid is $PTAQ_pid"
        echo "---------PTAQ 启动成功-----------"

		## 启动QYXZ
        echo "--------开始启动QYXZ---------------"
        nohup java -jar $QYXZ >/dev/null 2>&1 &
        QYXZ_pid=`lsof -i:$QYXZ_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$QYXZ_pid" ]
            do
              QYXZ_pid=`lsof -i:$QYXZ_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "QYXZ pid is $QYXZ_pid"
        echo "---------QYXZ 启动成功-----------"

		## 启动FXPG
        echo "--------开始启动FXPG---------------"
        nohup java -jar $FXPG >/dev/null 2>&1 &
        FXPG_pid=`lsof -i:$FXPG_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$FXPG_pid" ]
            do
              FXPG_pid=`lsof -i:$FXPG_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "FXPG pid is $FXPG_pid"
        echo "---------FXPG 启动成功-----------"

		## 启动OPEN
        echo "--------开始启动OPEN---------------"
        nohup java -jar $OPEN >/dev/null 2>&1 &
        OPEN_pid=`lsof -i:$OPEN_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$OPEN_pid" ]
            do
              OPEN_pid=`lsof -i:$OPEN_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "OPEN pid is $OPEN_pid"
        echo "---------OPEN 启动成功-----------"

        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $EUREKA | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===EUREKA process not exists or stop success"
        else
            kill -9 $P_ID
            echo "EUREKA killed success"
        fi
		P_ID=`ps -ef | grep -w $GZGL | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===GZGL process not exists or stop success"
        else
            kill -9 $P_ID
            echo "GZGL killed success"
        fi
		P_ID=`ps -ef | grep -w $COLLECTER | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===COLLECTER process not exists or stop success"
        else
            kill -9 $P_ID
            echo "COLLECTER killed success"
        fi
		P_ID=`ps -ef | grep -w $WWZS | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===WWZS process not exists or stop success"
        else
            kill -9 $P_ID
            echo "WWZS killed success"
        fi
		P_ID=`ps -ef | grep -w $ZDMB | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===ZDMB process not exists or stop success"
        else
            kill -9 $P_ID
            echo "ZDMB killed success"
        fi
		P_ID=`ps -ef | grep -w $XTSZ | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===XTSZ process not exists or stop success"
        else
            kill -9 $P_ID
            echo "XTSZ killed success"
        fi
		P_ID=`ps -ef | grep -w $QBSJ | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===QBSJ process not exists or stop success"
        else
            kill -9 $P_ID
            echo "QBSJ killed success"
        fi
		P_ID=`ps -ef | grep -w $FILE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===FILE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "FILE killed success"
        fi
		P_ID=`ps -ef | grep -w $PTAQ | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===PTAQ process not exists or stop success"
        else
            kill -9 $P_ID
            echo "PTAQ killed success"
        fi
		P_ID=`ps -ef | grep -w $QYXZ | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===QYXZ process not exists or stop success"
        else
            kill -9 $P_ID
            echo "QYXZ killed success"
        fi
		P_ID=`ps -ef | grep -w $FXPG | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===FXPG process not exists or stop success"
        else
            kill -9 $P_ID
            echo "FXPG killed success"
        fi
		P_ID=`ps -ef | grep -w $OPEN | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===OPEN process not exists or stop success"
        else
            kill -9 $P_ID
            echo "OPEN killed success"
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

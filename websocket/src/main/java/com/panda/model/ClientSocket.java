package com.panda.model;

import com.panda.utils.socket.client.SocketClient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ScheduledExecutorService;


@Data
@AllArgsConstructor
public class ClientSocket {

    private SocketClient socketClient;

    private ScheduledExecutorService clientHeartExecutor;
}

package com.panda.controller.socket;

import com.alibaba.fastjson.JSONObject;
import com.panda.core.ResponseEntity;
import com.panda.core.ServiceException;
import com.panda.model.ServerParamVo;
import com.panda.utils.socket.dto.ServerSendDto;
import com.panda.utils.socket.enums.FunctionCodeEnum;
import com.panda.utils.socket.server.Connection;
import com.panda.utils.socket.server.SocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentMap;

@Api(value = "socket-server", description = "socket服务端")
@RestController
@RequestMapping("/socket-server")
public class SocketServerController {

	@Resource
	private SocketServer socketServer;

	@ApiOperation(value = "/get-users", notes = "获取连接用户", httpMethod = "GET")
	@GetMapping("/get-users")
	public ResponseEntity<JSONObject> getLoginUsers() {
		ConcurrentMap<String, Connection> userMaps = socketServer.getExistSocketMap();
		JSONObject result=new JSONObject();
		result.put("total",userMaps.keySet().size());
		result.put("dataList",userMaps.keySet());
		return ResponseEntity.success(result);
	}

	@ApiOperation(value = "/send-message", notes = "获取连接用户", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", dataType = "MessageParam", name = "param"),
	})
	@PostMapping("/send-message")
	public ResponseEntity<?> sendMessage(@RequestBody ServerParamVo paramVo) {

		if (StringUtils.isEmpty(paramVo.getUserId()) || StringUtils.isEmpty(paramVo.getMessage())) {
			throw new ServiceException("参数不全");
		}
		if (!socketServer.getExistSocketMap().containsKey(paramVo.getUserId())) {
			throw new ServiceException("并没有客户端连接");
		}
		Connection connection = socketServer.getExistSocketMap().get(paramVo.getUserId());
		ServerSendDto dto = new ServerSendDto();
		dto.setFunctionCode(FunctionCodeEnum.MESSAGE.getValue());
		dto.setStatusCode(20000);
		dto.setMessage(paramVo.getMessage());
		connection.println(JSONObject.toJSONString(dto));
		return ResponseEntity.success();
	}

}

package com.panda.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServerParamVo implements Serializable {

	private static final long serialVersionUID = 5267331270045085979L;

	private String userId;

	private String message;
}

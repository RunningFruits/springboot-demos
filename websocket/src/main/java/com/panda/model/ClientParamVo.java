package com.panda.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class ClientParamVo implements Serializable {

	private static final long serialVersionUID = 2822768619906469920L;

	private String userId;

	private String message;
}

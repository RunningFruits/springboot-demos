package com.code.demo.api.dataobject.ro;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@Data
public class UserRegisterRO {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min=6, message="密码长度不能少于6位")
    private String password;

    private String email;

    private String phone;
}

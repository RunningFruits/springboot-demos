package com.haiyu.manager.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Title: UserAnswerDTO
 */
@Data
public class UserAnswerDTO {

    private Long id;
    private String openid;
    private String parentName;
    private String parentPhone;
    private String childName;
    private String score;
    private Date createTime;

}

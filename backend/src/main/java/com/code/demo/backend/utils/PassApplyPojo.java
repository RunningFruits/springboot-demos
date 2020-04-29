package com.code.demo.backend.utils;

import lombok.Data;

import java.util.List;

@Data
public class PassApplyPojo {
    private String clientName;
    private String cardId;
    private String shanghaiAddress;
    private String phoneNum;
    private String buildingNum;
    private String streetCode;
    private String streetName;
    private String communityCode;
    private String communityName;
    private String villageName;
    private String clientFlag;
    private int isok;
    private List<String> nameList;
    private List<String> cardIdList;
    private List<String> phoneList;
    private List<String> clientFlagList;
}

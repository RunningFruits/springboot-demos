package cn.huanzi.qch.springbootsecurity.sys.vo;

import cn.huanzi.qch.springbootsecurity.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserAuthorityVo extends PageCondition implements Serializable {
    private String userAuthorityId;//用户权限表id

    private String userId;//用户id

    private String authorityId;//权限id

    private SysUserVo sysUser;//用户

    private SysAuthorityVo sysAuthority;//权限
}

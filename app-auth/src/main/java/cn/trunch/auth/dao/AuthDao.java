package cn.trunch.auth.dao;

import cn.trunch.auth.entity.Auth;
import org.apache.ibatis.annotations.*;

public interface AuthDao {
    @Insert("INSERT INTO auth (token, ip, address)" +
            "VALUES (#{authToken}, #{authIp}, #{authAddress})")
    Integer addAuthInfo(String authToken, String authIp, String authAddress);

    @Select("SELECT * FROM auth WHERE token = #{authToken}")
    @Results({
            @Result(property = "authToken", column = "token"),
            @Result(property = "authTime", column = "time"),
            @Result(property = "authIp", column = "ip"),
            @Result(property = "authAddress", column = "address"),
            @Result(property = "authState", column = "state"),
            @Result(property = "userId", column = "user_id")
    })
    Auth getAuthInfo(String authToken);

    @Update("UPDATE auth SET state = #{authState}, user_id = #{userId} WHERE token = #{authToken}")
    Integer setAuthState(String authToken, Integer authState, String userId);
}

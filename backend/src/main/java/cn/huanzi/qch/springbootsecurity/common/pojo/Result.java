package cn.huanzi.qch.springbootsecurity.common.pojo;

import lombok.Data;

import java.io.Serializable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 统一返回对象
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 版本
     */
    private static final long serialVersionUID = 1L;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public Result() {

    }

    //构造方法
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //如果返回数据直接成功
    public Result(T data) {
        this.code = 200;
        this.data = data;
        this.msg = "OK";
    }

    //失败调用
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //成功直接返回数据和状态
    public static <T> Result<T> ok(T data) {
        return new Result<T>(data);
    }

    //失败的时候调用
    public static <T> Result<T> error(int code, String msg) {
        return new Result<T>(code, msg);
    }

    //转为JSON
    public static <T> String toJSON(Result<T> result) {
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

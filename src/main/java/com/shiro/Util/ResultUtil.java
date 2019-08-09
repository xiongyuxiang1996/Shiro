package com.shiro.Util;

import com.shiro.Entity.Result;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/9
 * @Time 14:09
 * @Description
 */
public class ResultUtil {
    // 成功
    private final static Integer SUCCESS_CODE = 0;
    private final static String SUCCESS_MSG = "成功";
    // 失败
    private final static Integer ERROR_CODE = 1;
    private final static String ERROR_MSG = "失败";
    // 其它
    private final static Integer OTHER_CODE = 2;
    private final static String OTHER_MSG = "其他";

    /**
     * 成功
     * @param msg
     * @param object
     * @return
     */
    public static Result success(String msg, Object object){
        return new Result(SUCCESS_CODE, msg, object);
    }

    public static Result success(Object object){
        return success(SUCCESS_MSG, object);
    }

    public static Result success(String msg){
        return success(msg, null);
    }

    /**
     * 失败
     * @param msg
     * @param object
     * @return
     */
    public static Result error(String msg, Object object){
        return new Result(ERROR_CODE, msg, object);
    }

    public static Result error(Object object){
        return error(ERROR_MSG, object);
    }

    public static Result error(String msg){
        return error(msg, null);
    }

    /**
     * 其它错误
     * @param msg
     * @param object
     * @return
     */
    public static Result other(String msg, Object object){
        return new Result(OTHER_CODE, msg, object);
    }

    public static Result other(Object object){
        return other(OTHER_MSG, object);
    }

    public static Result other(String msg){
        return other(msg, null);
    }

}

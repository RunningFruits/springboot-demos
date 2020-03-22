package com.init.demo.aop;


import com.init.demo.annotation.OperationLog;
import com.init.demo.entity.system.SystemLog;
import com.init.demo.entity.system.SystemUser;
import com.init.demo.repository.system.SystemLogRepository;
import com.init.demo.utils.SessionUtil;
import com.init.demo.utils.WebUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 操作日志切面.
 */
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private SystemLogRepository systemLogRepository;

    @Autowired
    private SessionUtil sessionUtil;


    @Pointcut("@annotation(com.init.demo.annotation.OperationLog)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        // 执行时长
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SystemLog sysLog = new SystemLog();

        // 获取注解上的操作描述
        OperationLog operationLogAnnotation = method.getAnnotation(OperationLog.class);
        if (operationLogAnnotation != null) {
            sysLog.setOperation(operationLogAnnotation.value());
        }

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        // 请求的方法参数
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append("  ").append(paramNames[i]).append(": ").append(args[i]);
            }
            sysLog.setParams(params.toString());
        }

        sysLog.setIp(WebUtil.getIpAddr());

        // 获取当前登录用户名
        SystemUser user = sessionUtil.getCurrentUser();
        if (user != null) {
            sysLog.setUserId(user.getId());
            sysLog.setUserName(user.getUserName());
        }
        sysLog.setTime((int) time);
        systemLogRepository.save(sysLog);
    }
}

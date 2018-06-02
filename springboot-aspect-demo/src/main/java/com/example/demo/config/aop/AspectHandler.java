package com.example.demo.config.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Description:
 * "@component" （把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
 * @Auther: zhangjie
 * @Date: 2018/5/30 15:46
 */
@Component
@Aspect
public class AspectHandler {

    private static final Logger logger = LoggerFactory.getLogger(AspectHandler.class);

    private JSONObject jsonObject = new JSONObject();

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void executeService (){
    }

    /**
     * 前置通知，方法调用前被调用
     * @param joinPoint
     */
    @Before("executeService()")
    public void methodBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("===============请求内容===============");
        try {
            // 打印请求内容
            logger.info("请求地址:" + request.getRequestURL().toString());
            logger.info("请求方式:" + request.getMethod());
            logger.info("请求类方法:" + joinPoint.getSignature());
            logger.info("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));
        } catch (Exception e) {
            logger.error("###LogAspectServiceApi.class methodBefore() ### ERROR:", e);
        }
        logger.info("===============请求内容===============");
    }

    @AfterReturning(returning = "o", pointcut = "executeService()")
    public void methodAfterReturing(Object o) {
        logger.info("--------------返回内容----------------");
        try {
            logger.info("Response内容:" + jsonObject.toJSONString(o));
        } catch (Exception e) {
            logger.error("###AspectHandler.class methodAfterReturing() ### ERROR:", e);
        }
        logger.info("--------------返回内容----------------");
    }


}

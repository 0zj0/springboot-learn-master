package com.example.demo.aspect;

import com.example.demo.entity.MsgContext;
import com.example.demo.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjie
 * @date 2019/6/21 15:43
 */
@Component
@Aspect
@Slf4j
public class ControllerAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();
    /**
     * 定义切入点
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void pointcut(){
    }

    /**
     * 前置处理
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        log.info("切面前置处理....");
        startTime.set(System.currentTimeMillis());
        //获取客户端请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取请求参数
        log.info("URL:{}",request.getRequestURL().toString());
        log.info("HTTP_METHOD :{}",request.getMethod());
        log.info("IP :{}",request.getRemoteAddr());
        log.info("declaringTypeName :{}",joinPoint.getSignature().getDeclaringTypeName());
        log.info("methodName:{}",  joinPoint.getSignature().getName());
        log.info("request 参数 :{}",getRequestParam(request));
        log.info("body 参数 :{}",getBodyParam(request));
    }

    /**
     * 待返回值的后置处理
     */
    @AfterReturning(returning = "msgContext",pointcut = "pointcut()")
    public void doAfter(MsgContext msgContext){
        log.info("切面前置处理....");
        long endTime = System.currentTimeMillis();
        log.info("接口调用耗时：{}",endTime - startTime.get());
        log.info("返回结果：{}",msgContext.toString());
    }

    /**
     * 获取request 传递参数
     * @param request
     * @return
     */
    private static String getRequestParam(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            map.put(key, request.getParameter(key));
        }
        return JsonUtils.toJsonString(map);
    }

    /**
     * 获取 body 传递参数
     * @param request
     * @return
     */
    private static String getBodyParam(HttpServletRequest request) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
        String str = "";
        while ((str = bufferedReader.readLine()) != null){
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

}

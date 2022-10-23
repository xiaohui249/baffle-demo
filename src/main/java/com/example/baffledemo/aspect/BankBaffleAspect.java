package com.example.baffledemo.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.baffledemo.annotation.BankBaffle;
import com.example.baffledemo.service.BankService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description: 挡板拦截类
 * @date: 2022-10-15 17:15
 */
@Aspect
@Component
public class BankBaffleAspect implements EnvironmentAware {
    private static final Logger log = LoggerFactory.getLogger(BankBaffleAspect.class);

    // 挡板默认开启
    private static final String BAFFLE_ENABLE_DEFAULT = "true";

    private Environment environment;

    // 挡板对象Map，起缓存作用
    @Resource
    private Map<String, BankService> map;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 定义切入点
    */
    @Pointcut("@annotation(com.example.baffledemo.annotation.BankBaffle) ")
    public void entryPoint() {
        // 无需内容
    }

    @Around("entryPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature sig = joinPoint.getSignature();
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature msig = (MethodSignature) sig;

        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        // 获取注解
        BankBaffle bankBaffle = currentMethod.getAnnotation(BankBaffle.class);
        // 解析注解参数
        String enable = this.environment.resolvePlaceholders(bankBaffle.enable());
        String classname = this.environment.resolvePlaceholders(bankBaffle.className());
        String bankIds = this.environment.resolvePlaceholders(bankBaffle.bankIds());

        if(BAFFLE_ENABLE_DEFAULT.equalsIgnoreCase(enable)) {
            // 容器中是否加载注入指定的挡板实例
            BankService baffleObj = map.get(classname);
            if(null == baffleObj) {
                return joinPoint.proceed();
            }

            // 是否配置指定执行挡板程序的银行
            String[] bankIdArray = StringUtils.split(bankIds, ",");
            if(null == bankIdArray || bankIdArray.length == 0) {
                return joinPoint.proceed();
            }

            // 对指定的银行请求执行挡板程序，否则执行原程序
            JSONObject methodParamValue = (JSONObject) JSON.toJSON(joinPoint.getArgs()[0]);
            String bankId = methodParamValue.getJSONObject("head").getString("bankid");
            if(Arrays.asList(bankIdArray).contains(bankId)) {
                String methodName = msig.getMethod().getName();
                log.info("执行挡板程序：{}.{}", classname, msig.getMethod().getName());
                Method m = baffleObj.getClass().getMethod(methodName, msig.getParameterTypes());
                return m.invoke(baffleObj, joinPoint.getArgs());
            } else {
                return joinPoint.proceed();
            }
        } else {
            return joinPoint.proceed();
        }
    }
}

package com.example.baffledemo.aspect;

import com.example.baffledemo.annotation.Baffle;
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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description: 挡板拦截类
 * @date: 2022-10-15 17:15
 */
@Aspect
@Component
public class BaffleAspect implements EnvironmentAware {
    private static final Logger log = LoggerFactory.getLogger(BaffleAspect.class);

    // 挡板默认开启
    private static final String BAFFLE_ENABLE_DEFAULT = "true";

    private Environment environment;

    // 挡板对象Map，起缓存作用
    private Map<String, Object> baffleInstanceMap = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 定义切入点
    */
    @Pointcut("@annotation(com.example.baffledemo.annotation.Baffle) ")
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
        Baffle aBaffle = currentMethod.getAnnotation(Baffle.class);
        // 解析注解参数
        String enable = this.environment.resolvePlaceholders(aBaffle.enable());
        String classname = this.environment.resolvePlaceholders(aBaffle.className());

        if(BAFFLE_ENABLE_DEFAULT.equalsIgnoreCase(enable)) {
            String methodName = msig.getMethod().getName();
            log.info("执行挡板程序：{}.{}", classname, msig.getMethod().getName());

            Class cls = Class.forName(classname);

            Object baffleObj = baffleInstanceMap.get(classname);
            if(baffleObj == null) {
                log.info("未在缓存Map中匹配到挡板服务对象，通过反射生成挡板服务对象！");
                baffleObj = cls.getDeclaredConstructor().newInstance();
                baffleInstanceMap.put(classname, baffleObj);
            }

            Method m = cls.getMethod(methodName, msig.getParameterTypes());
            return m.invoke(baffleObj, joinPoint.getArgs());
        } else {
            return joinPoint.proceed();
        }
    }
}

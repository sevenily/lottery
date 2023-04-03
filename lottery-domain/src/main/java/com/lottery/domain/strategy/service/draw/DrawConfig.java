package com.lottery.domain.strategy.service.draw;

import com.lottery.domain.strategy.annotation.Strategy;
import com.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DrawConfig
 * @Description: 抽奖统一配置信息类
 * @Author: seven
 * @CreateTime: 2023-02-08 16:41
 * @Version: 1.0
 **/

public class DrawConfig {
    @Resource
    private List<IDrawAlgorithm> algorithmList;

    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmGroup = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        algorithmList.forEach(algorithm ->{
            //AnnotationUtils.findAnnotation方式获取指定类上的注解
            /**
             * 获取类上的注解
             * 1.1 原生java的方式获取类上的注解
             * PayCode aliPay = aliaPay.getClass().getAnnotation(PayCode.class);
             * 1.2 原生Java的方式获取属性上的注解
             * Field versionField = ReflectionUtils.findField(jingDongPay.getClass(), "version");
             * Version version = versionField.getAnnotation(Version.class);
             * 1.3 通过反射获取aliaPay对象上的pay方法的Method对象
             * Method payMethod = ReflectionUtils.findMethod(aliaPay.getClass(), "pay");
             *
             * 2.1 AnnotationUtils.findAnnotation获取类注解
             * PayCode aliPay = AnnotationUtils.findAnnotation(aliaPay.getClass(), PayCode.class);
             *
             * 2.2 AnnotationUtils.getValue获取注解上的指定属性值
             * // AnnotationUtils的方式获取指定类上的注解
             * PayCode aliPayAnnotation = AnnotationUtils.findAnnotation(aliaPay.getClass(), PayCode.class);
             * // 获取注解上指定的值
             * Object payCode = AnnotationUtils.getValue(aliPayAnnotation, "payCode");
             *
             * 2.3 // 获取方法上的注解
             * PayOrder payOrder = AnnotationUtils.findAnnotation(payMethod, PayOrder.class);
             *
             * 2.4  AnnotationUtils.getAnnotationAttributes获取注解上的所有属性值
             * // 获取注解上所有的属性值
             * PayCode aliPay = AnnotationUtils.findAnnotation(aliaPay.getClass(), PayCode.class);
             * Map<String, Object> annotationAttributes = AnnotationUtils.getAnnotationAttributes(aliPay);
             */

            //将带有Strategy注解的类 存放至drawAlgorithmGroup 中 code, algorithm
            Strategy strategy = AnnotationUtils.findAnnotation(algorithm.getClass(), Strategy.class);
            if (null != strategy){
                drawAlgorithmGroup.put(strategy.strategyMode().getCode(), algorithm);
            }
        });
    }

}

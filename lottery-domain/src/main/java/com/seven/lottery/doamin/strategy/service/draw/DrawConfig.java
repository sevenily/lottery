package com.seven.lottery.doamin.strategy.service.draw;

import com.seven.lottery.doamin.strategy.annotation.Strategy;
import com.seven.lottery.doamin.strategy.service.algorithm.IDrawAlgorithm;
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
            Strategy strategy = AnnotationUtils.findAnnotation(algorithm.getClass(), Strategy.class);
        });
    }

}

package com.seven.lottery.doamin.strategy.service.algorithm.impl;

import com.seven.lottery.common.Constants;
import com.seven.lottery.doamin.strategy.annotation.Strategy;
import com.seven.lottery.doamin.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: SingleRateRandomDrawAlgorithm
 * @Description: 单项随机概率抽奖，抽到一个已经排除掉的奖品则未中奖
 * @Author: seven
 * @CreateTime: 2023-02-08 15:36
 * @Version: 1.0
 **/
@Component("singleRateRandomDrawAlgorithm")
@Strategy(strategyMode = Constants.StrategyMode.SINGLE)
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        return null;
    }

}

package com.lottery.domain.strategy.service.algorithm.impl;

import com.lottery.domain.strategy.annotation.Strategy;
import com.lottery.common.Constants;
import com.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
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
        // 获取策略对应的元组
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        //随机索引
        int randomVal = this.generateSecureRandomIntCode(100);
        int idx = super.hashIdx(randomVal);

        //返回结果
        String awardId = rateTuple[idx];

        //如果妆奖ID命中排除奖品列表，则返回NULL
        if (excludeAwardIds.contains(awardId)){
            return null;
        }
        return awardId;
    }

}

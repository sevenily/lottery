package com.seven.lottery.doamin.strategy.service.algorithm.impl;

import com.seven.lottery.common.Constants;
import com.seven.lottery.doamin.strategy.annotation.Strategy;
import com.seven.lottery.doamin.strategy.model.vo.AwardRateVO;
import com.seven.lottery.doamin.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EntiretyRateRandomDrawAlgorithm
 * @Description: 必中奖抽奖策略，排除已经中奖的概率，重新计算中奖范围
 * @Author: seven
 * @CreateTime: 2023-03-17 14:08
 * @Version: 1.0
 **/

@Component("entiretyRateRandomDrawAlgorithm")
@Strategy(strategyMode = Constants.StrategyMode.ENTIRETY)
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        //排除掉不在抽奖范围的奖品ID集合
        List<AwardRateVO> differentAwardRateList = new ArrayList<>();
        List<AwardRateVO> awardRateIntervalList = awardRateInfoMap.get(strategyId);
        for (AwardRateVO awardRateInfo : awardRateIntervalList) {
            String awardId = awardRateInfo.getAwardId();
            if (excludeAwardIds.contains(awardId)) {
                continue;
            }

            differentAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        //前置判断：奖品列表为0， 返回null
        if (differentAwardRateList.size() == 0) {
            return null;
        }

        //前置判断：奖品列表为1， 直接返回
        if (differentAwardRateList.size() == 1) {
            return differentAwardRateList.get(0).getAwardId();
        }

        int randomVal = this.generateSecureRandomIntCode(100);

        //循环获取奖品
        String awardId = null;
        int cursorVal = 0;
        for (AwardRateVO awardRateInfo : differentAwardRateList) {
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if (randomVal <= (cursorVal + randomVal)) {
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        //返回中奖结果
        return awardId;

    }
}

package com.seven.lottery.doamin.strategy.service.draw;

import com.seven.lottery.doamin.strategy.model.aggregates.StrategyRich;
import com.seven.lottery.doamin.strategy.model.vo.AwardBriefVO;
import com.seven.lottery.doamin.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;

/**
 * @ClassName: DrawStrategySupport
 * @Description: 抽奖策略模式数据支撑，一些通用的数据服务
 * @Author: seven
 * @CreateTime: 2023-02-08 16:40
 * @Version: 1.0
 **/

public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     *  查询策略匹配信息
     * @param strategyId    策略ID
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId){
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详细信息
     * @param awardId   奖品ID
     * @return 中奖详情
     */
    protected AwardBriefVO queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }
}

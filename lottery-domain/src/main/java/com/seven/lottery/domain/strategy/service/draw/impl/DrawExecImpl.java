package com.seven.lottery.domain.strategy.service.draw.impl;

import com.alibaba.fastjson.JSON;
import com.seven.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.seven.lottery.domain.strategy.service.draw.AbstractDrawBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName: DrawExecImpl
 * @Description: 抽奖过程方法实现
 * @Author: seven
 * @CreateTime: 2023-03-17 13:47
 * @Version: 1.0
 **/

@Service("drawExec")
public class DrawExecImpl extends AbstractDrawBase {
    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);


    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        List<String> awardList = strategyRepository.queryNoStockStrategyAwardList(strategyId);

        logger.info("执行抽奖策略 strategyId: {}, 无库存排除奖品列表ID集合 awardList: {}", strategyId, JSON.toJSONString(awardList));
        return awardList;
    }

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds) {
        //执行抽奖
        String awardId = drawAlgorithm.randomDraw(strategyId, excludeAwardIds);

        //判断抽奖结果
        if (null == awardId){
            return null;
        }

        //成功-减库存，返回结果
        /**
         * 扣减库存，暂时采用数据库行级锁的方式扣减库存，后续优化为Redis分布式锁扣减库存
         * 注意：通常数据库直接锁行记录的方式并不能支撑大体量的开发，
         * 但此种方式需要了解，因为在分库分表的正常数据流量下的个人数据记录中，是可以使用行级锁的，
         * 因为他只影响到自己的记录，不会影响到其他人
         */
        boolean isSuccess = strategyRepository.deductStock(strategyId, awardId);
        //返回结果，库存扣减成功返回奖品ID， 否则返回null
        // 【在实际业务员场景中，如果中奖奖品库存为空，会返回兜底奖品，比如各类券】
        return isSuccess? awardId: null;

    }
}

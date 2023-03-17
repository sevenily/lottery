package com.seven.lottery.doamin.strategy.service.draw;

import com.seven.lottery.common.Constants;
import com.seven.lottery.doamin.strategy.model.aggregates.StrategyRich;
import com.seven.lottery.doamin.strategy.model.req.DrawReq;
import com.seven.lottery.doamin.strategy.model.res.DrawResult;
import com.seven.lottery.doamin.strategy.model.vo.*;
import com.seven.lottery.doamin.strategy.service.algorithm.IDrawAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AbstractDrawBase
 * @Description: 定义抽象抽奖过程，模板模式
 * @Author: seven
 * @CreateTime: 2023-02-08 16:39
 * @Version: 1.0
 **/

public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec {
    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        //1. 获取抽奖策略
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();
        //2. 校验抽奖策略是否已初始化到内存

        //3. 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
        //4. 执行抽奖算法
        //5. 包装中奖结果

        return null;
    }


    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等，
     * 这类数据是含有业务逻辑的，所以需要由具体的实现方决定
     *
     * @param strategyId 策略ID
     * @return 排除的奖品ID集合
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);


    /**
     * 执行抽奖算法
     *
     * @param strategyId      策略ID
     * @param drawAlgorithm   抽奖算法模型
     * @param excludeAwardIds 排除的抽奖ID集合
     * @return 中奖奖品ID
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds);

    /**
     * 校验抽奖策略是否已经初始化内存
     *
     * @param strategyId             抽奖策略ID
     * @param strategyMode           抽奖策略模式
     * @param strategyDetailBriefVOS 抽奖策略详情
     */
    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailBriefVOS) {
        IDrawAlgorithm drawAlgorithm = drawAlgorithmGroup.get(strategyMode);
        if (drawAlgorithm.isExist(strategyId)) {
            return;
        }

        //不存在，则解析并初始化中奖概率数据到散列表
        ArrayList<AwardRateVO> awardRateInfoList = new ArrayList<>(strategyDetailBriefVOS.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailBriefVOS) {
            awardRateInfoList.add(new AwardRateVO(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }
        drawAlgorithm.initRateTuple(strategyId, strategyMode, awardRateInfoList);
    }

    /**
     * 包装抽奖结果
     *
     * @param uId        用户id
     * @param strategyId 策略id
     * @param awardId    奖品id， null 情况：并发抽奖情况下，库存临界值1>0, 会有用户中奖结果为null
     * @param strategy
     * @return 中奖结果
     */
    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId, StrategyBriefVO strategy) {
        if (null == awardId) {
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略id：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        AwardBriefVO awardBriefVO = super.queryAwardInfoByAwardId(awardId);
        DrawAwardVO drawAwardInfo = new DrawAwardVO(uId, awardBriefVO.getAwardId(), awardBriefVO.getAwardType(), awardBriefVO.getAwardName(), awardBriefVO.getAwardContent());
        drawAwardInfo.setStrategyMode(strategy.getStrategyMode());
        drawAwardInfo.setGrantType(strategy.getGrantType());
        drawAwardInfo.setGrantDate(strategy.getGrantDate());

        logger.info("执行策略抽奖完成【已中奖】,用户：{} 策略ID：{} 奖品ID： {} 奖品名称：{}", uId, strategyId, awardId, awardBriefVO.getAwardName());

        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardInfo);
    }
}

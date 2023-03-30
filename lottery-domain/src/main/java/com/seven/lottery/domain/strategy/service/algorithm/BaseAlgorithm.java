package com.seven.lottery.domain.strategy.service.algorithm;

import com.seven.lottery.common.Constants;
import com.seven.lottery.domain.strategy.model.vo.AwardRateVO;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: BaseAlgorithm
 * @Description: 公用算法逻辑抽象类
 * @Author: seven
 * @CreateTime: 2023-02-08 14:57
 * @Version: 1.0
 **/

public abstract class BaseAlgorithm implements IDrawAlgorithm{
    /**
     * 斐波那契散列增量
     * 逻辑：黄金分割点：
     * （(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647）
     */
    private final int HASH_INCREMENT = 0x61c88647;

    /**
     * 数组初始化长度 128，保证数据填充时不发生碰撞的最小初始化值
     */
    private final int RATE_TUPLE_LENGTH = 128;

    /**
     * 存放概率奖品对应的散列结果（strategyId ——>rateTuple）
     */
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    /**
     * 奖品区间概率值，strategyId——>[awardId->begin、awardId->end]
     */
    protected Map<Long, List<AwardRateVO>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public synchronized void initRateTuple(Long strategyId, Integer strategyMode, List<AwardRateVO> awardRateList) {
        //前置判断
        if (isExist(strategyId)){
            return;
        }

        //保存奖品概率信息
        awardRateInfoMap.put(strategyId, awardRateList);

        //非单项概率，不必存入缓存，因为这部分概率算法需要实时处理中奖概率
        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)){
            return;
        }

        /*
         * hashmap.computeIfAbsent(K key, Function remappingFunction)
         * 方法有两个参数：第一个参数是hashMap的key，第二个参数是一个方法，
         * 叫做重新映射函数，用于重新计算值(就是说value值是这个方法重新计算后的结果)。
         *
         * 返回值：返回的就是value值
         *
         * 作用：使用这个方法时，如果说key不存在，那么直接把key添加到map中
         *
         */
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k ->
        new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for (AwardRateVO awardRateInfo : awardRateList) {
            //将概率转为0-100之间的值
            int rateVale = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();

            //循环填充概率范围值
            for (int i = cursorVal + 1; i < (rateVale + cursorVal); i++) {
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
            }
            cursorVal += rateVale;
        }

    }

    /**
     * 斐波那契（Fibonacci）散列法，计算哈希索引下标值
     * @param val   值
     * @return      索引
     */
    protected int hashIdx(int val){
        int hashCode = val * HASH_INCREMENT +HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH -1);
    }

    @Override
    public boolean isExist(Long strategyId) {
        return awardRateInfoMap.containsKey(strategyId);
    }


    /**
     * 生成百位随机抽奖码
     * @param bound 随机值
     * @return 1 ~ bound+1
     */
    public int generateSecureRandomIntCode(int bound){
        //secureRandom.nextInt(max - min) + min;
        return new SecureRandom().nextInt(bound) + 1;
    }
}

package com.seven.lottery.domain.award.service.goods;

import com.seven.lottery.domain.award.repository.IAwardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;


/**
 * @ClassName: DistributionBase
 * @Description: 配送货物基础公共类
 * @Author: seven
 * @CreateTime: 2023-03-17 16:29
 * @Version: 1.0
 **/

public class DistributionBase {
    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);


    @Resource
    private IAwardRepository awardRepository;

    protected void updateUserAwardState(String uId, String orderId, String awardId, Integer awardState, String awardStateInfo){
        //TODO 后期添加更新分库分表中， 用户个人的抽奖记录表中奖品状态
        logger.info("TODO 后期添加更新分库分表中， 用户个人的抽奖记录表中奖品状态uId: {}", uId);

    }


}

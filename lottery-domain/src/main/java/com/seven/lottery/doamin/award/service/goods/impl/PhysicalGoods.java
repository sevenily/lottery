package com.seven.lottery.doamin.award.service.goods.impl;

import com.seven.lottery.common.Constants;
import com.seven.lottery.doamin.award.model.req.GoodsReq;
import com.seven.lottery.doamin.award.model.res.DistributionRes;
import com.seven.lottery.doamin.award.service.goods.DistributionBase;
import com.seven.lottery.doamin.award.service.goods.IDistributionGoods;

/**
 * @ClassName: PhysicalGoods
 * @Description: 实物发货类商品
 * @Author: seven
 * @CreateTime: 2023-03-17 16:34
 * @Version: 1.0
 **/

public class PhysicalGoods extends DistributionBase implements IDistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        //模拟调用实物发奖
        logger.info("模拟调用实物发奖 uId:{} awardContent:{}", req.getuId(), req.getAwardContent());

        //更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(),
                Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(),
                Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.PhysicalGoods.getCode();
    }
}

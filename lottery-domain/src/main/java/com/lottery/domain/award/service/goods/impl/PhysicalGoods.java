package com.lottery.domain.award.service.goods.impl;

import com.lottery.domain.award.model.req.GoodsReq;
import com.lottery.domain.award.service.goods.DistributionBase;
import com.lottery.domain.award.service.goods.IDistributionGoods;
import com.lottery.common.Constants;
import com.lottery.domain.award.model.res.DistributionRes;

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

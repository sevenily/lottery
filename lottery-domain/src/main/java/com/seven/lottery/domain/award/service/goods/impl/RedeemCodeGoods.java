package com.seven.lottery.domain.award.service.goods.impl;

import com.seven.lottery.common.Constants;
import com.seven.lottery.domain.award.model.req.GoodsReq;
import com.seven.lottery.domain.award.model.res.DistributionRes;
import com.seven.lottery.domain.award.service.goods.DistributionBase;
import com.seven.lottery.domain.award.service.goods.IDistributionGoods;

/**
 * @ClassName: RedeemCodeGoods
 * @Description: 兑换类商品
 * @Author: seven
 * @CreateTime: 2023-03-17 16:34
 * @Version: 1.0
 **/

public class RedeemCodeGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        //模拟调用兑换码
        logger.info("模拟调用兑换码 uId:{} awardContent:{}", req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.RedeemCodeGoods.getCode();
    }
}

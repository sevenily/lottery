package com.seven.lottery.doamin.award.service.goods;

import com.seven.lottery.doamin.award.model.req.GoodsReq;
import com.seven.lottery.doamin.award.model.res.DistributionRes;

/**
 * @ClassName: IDistributionGoods
 * @Description: 抽奖，
 *   抽象出配送货物接口，把各类奖品模拟成货物、
 *   配送代表着发货，包括虚拟奖品和实物奖品
 * @Author: seven
 * @CreateTime: 2023-03-17 16:27
 * @Version: 1.0
 **/

public interface IDistributionGoods {

    /**
     * 奖品配送接口，奖品类型（1. 文字描述、2.兑换码、3.优惠券、4.实物奖品）
     * @param req 物品信息
     * @return  配送结果
     */
    DistributionRes doDistribution(GoodsReq req);

    Integer getDistributionGoodsName();
}

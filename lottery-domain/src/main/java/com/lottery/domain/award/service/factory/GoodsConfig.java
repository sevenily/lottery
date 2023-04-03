package com.lottery.domain.award.service.factory;

import com.lottery.common.Constants;
import com.lottery.domain.award.service.goods.IDistributionGoods;
import com.lottery.domain.award.service.goods.impl.CouponGoods;
import com.lottery.domain.award.service.goods.impl.DescGoods;
import com.lottery.domain.award.service.goods.impl.PhysicalGoods;
import com.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: GoodsConfig
 * @Description: 各类发奖奖品配置类
 * @Author: seven
 * @CreateTime: 2023-03-17 16:41
 * @Version: 1.0
 **/

public class GoodsConfig {

    //奖品发放策略组
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init(){
        goodsMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }

}

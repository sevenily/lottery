package com.seven.lottery.domain.award.service.factory;

import com.seven.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DistirbutionGoodsFactory
 * @Description: 配送商品简单工厂，提供获取配送服务
 * @Author: seven
 * @CreateTime: 2023-03-17 16:42
 * @Version: 1.0
 **/

@Service
public class DistributionGoodsFactory extends GoodsConfig{

    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }

}
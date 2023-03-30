package com.seven.lottery.domain.activity.service.deploy.impl;

import com.alibaba.fastjson.JSON;
import com.seven.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.seven.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.seven.lottery.domain.activity.model.req.ActivityConfigReq;
import com.seven.lottery.domain.activity.model.vo.ActivityVO;
import com.seven.lottery.domain.activity.model.vo.AwardVO;
import com.seven.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.seven.lottery.domain.activity.model.vo.StrategyVO;
import com.seven.lottery.domain.activity.repository.IActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: ActivityDeployImpl
 * @Description: 部署活动配置服务
 * @Author: seven
 * @CreateTime: 2023-03-21 10:17
 * @Version: 1.0
 **/

@Service
public class ActivityDeployImpl implements IActivityDeploy {

    private Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createActivity(ActivityConfigReq req) {
        logger.info("创建活动配置开始， activityId:{}", req.getActivityId());
        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();

        try {
            /**
             * 添加活动配置
             */
            ActivityVO activity = activityConfigRich.getActivity();
            activityRepository.addActivity(activity);

            /**
             * 添加奖品配置
             */
            List<AwardVO> awardList = activityConfigRich.getAwardList();
            activityRepository.addAward(awardList);

            /**
             * 添加策略配置
             */
            StrategyVO strategy = activityConfigRich.getStrategy();
            activityRepository.addStrategy(strategy);

            /**
             * 添加策略明细配置
             */
            List<StrategyDetailVO> strategyDetailList = activityConfigRich.getStrategy().getStrategyDetailList();
            activityRepository.addStrategyDetailList(strategyDetailList);

            logger.info("创建活动配置完成， activityId：{}", req.getActivityId());
        } catch (DuplicateKeyException e) {
            logger.error("创建活动配置失败， 唯一索引冲突 activity:{} reqJSON:{}", req.getActivityId(), JSON.toJSONString(req), e);
            throw e;
        }
    }

    @Override
    public void updateActivity(ActivityConfigReq req) {
        //TODO 非核心功能后续补充

    }
}

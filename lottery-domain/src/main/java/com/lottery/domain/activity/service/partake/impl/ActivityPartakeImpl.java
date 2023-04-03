package com.lottery.domain.activity.service.partake.impl;

import com.lottery.common.Constants;
import com.lottery.common.Result;
import com.lottery.domain.activity.model.req.PartakeReq;
import com.lottery.domain.activity.model.vo.ActivityBillVO;
import com.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.lottery.domain.activity.service.partake.BaseActivityPartake;
import com.lottery.domain.support.ids.IIdGenerator;
import org.lottery.db.strategy.IDBRouterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: ActivityPartakeImpl
 * @Description: 活动参与功能实现
 * @Author: seven
 * @CreateTime: 2023-03-31 10:43
 * @Version: 1.0
 **/

@Service
public class ActivityPartakeImpl extends BaseActivityPartake {
    private Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);
    
    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;
    
    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;
    
    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private IDBRouterStrategy dbRouter;
    
    @Override
    protected Result checkActivityBill(PartakeReq partake, ActivityBillVO bill) {
        //校验：活动状态
        if (!Constants.ActivityState.DOING.getCode().equals(bill.getState())){
            logger.info("活动当前状态可用 state:{}", bill.getState());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动当前状态可用");
        }

        //校验：活动日期
        if (bill.getBeginDateTime().after(partake.getPartakeDate()) || bill.getEndDateTime().before(partake.getPartakeDate())){
            logger.info("活动时间范围非可用 beginDateTime：{} endDateTime：{}", bill.getBeginDateTime(), bill.getEndDateTime());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动时间范围费可用");
        }

        //校验：活动库存
        if (bill.getStockSurplusCount() <= 0){
            logger.info("活动剩余课程非可用 stockSurplusCount:{}", bill.getStockSurplusCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动剩余库存非可用");
        }

        //校验：个人库存-个人活动剩余可领取次数
        if (bill.getUserTakeLeftCount() <= 0){
            logger.info("活动领取次数非可用 userTakeLeftCount:{}", bill.getUserTakeLeftCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "个人领取次数非可用");
        }

        return Result.buildSuccessResult();
    }

    /**
     * 扣减库存
     * @param req   参与活动请求
     * @return
     */
    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        int count = activityRepository.subtractActivityStock(req.getActivityId());
        if (0 == count){
            logger.info("扣减活动库存失败 activityId:{}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
        }

        return Result.buildSuccessResult();
    }

    @Override
    protected Result grabActivity(PartakeReq partake, ActivityBillVO bill) {
        try {
            dbRouter.doRouter(partake.getuId());
            return transactionTemplate.execute(status -> {
                try {
                    //扣减个人已参与次数
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId(), bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), partake.getuId(), partake.getPartakeDate());
                    if (0 == updateCount){
                        //设置事务回滚
                        status.setRollbackOnly();
                        logger.info("领取活动，扣减个人参与次数失败 activityId：{} uId:{}", partake.getActivityId(), partake.getuId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    //插入领取活动信息
                    long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
                    userTakeActivityRepository.takeActivity(bill.getActivityId(), bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), partake.getuId(), partake.getPartakeDate(), takeId);
                } catch (DuplicateKeyException e) {
                    //设置事务回滚
                    status.setRollbackOnly();
                    logger.info("领取活动，唯一索引冲突 activityId ：{} uId：{}", partake.getActivityId(), partake.getuId());
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }
}

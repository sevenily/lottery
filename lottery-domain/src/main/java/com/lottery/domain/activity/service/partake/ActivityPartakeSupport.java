package com.lottery.domain.activity.service.partake;

import com.lottery.domain.activity.model.req.PartakeReq;
import com.lottery.domain.activity.model.vo.ActivityBillVO;
import com.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @ClassName: ActivityPartakeSupport
 * @Description: 活动领取操作，一些通用的数据服务
 * @Author: seven
 * @CreateTime: 2023-03-31 09:07
 * @Version: 1.0
 **/

public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }

}

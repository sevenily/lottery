package com.lottery.interfaces;

import com.lottery.rpc.IActivityBooth;
import com.lottery.rpc.dto.ActivityDto;
import com.lottery.rpc.req.ActivityReq;
import com.lottery.rpc.res.ActivityRes;
import com.lottery.dao.IActivityDao;
import com.lottery.common.Constants;
import com.lottery.common.Result;
import com.lottery.po.Activity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: ActivityBooth
 * @Description: 活动服务
 * @Author: seven
 * @CreateTime: 2023-03-29 13:33
 * @Version: 1.0
 **/
//@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activityDto.getBeginDateTime());
        activityDto.setEndDateTime(activityDto.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(),
                Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
    }
}

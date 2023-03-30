package com.interfaces.interfaces;

import com.rpc.IActivityBooth;
import com.rpc.dto.ActivityDto;
import com.rpc.req.ActivityReq;
import com.rpc.res.ActivityRes;
import com.seven.dao.IActivityDao;
import com.seven.lottery.common.Constants;
import com.seven.lottery.common.Result;
import com.seven.po.Activity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: ActivityBooth
 * @Description: 活动服务
 * @Author: seven
 * @CreateTime: 2023-03-29 13:33
 * @Version: 1.0
 **/
@Service
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

package com.seven.lottery.doamin.activity.service.stateflow.impl;

import com.seven.lottery.common.Constants;
import com.seven.lottery.common.Result;
import com.seven.lottery.doamin.activity.service.stateflow.IStateHandler;
import com.seven.lottery.doamin.activity.service.stateflow.StateConfig;
import org.springframework.stereotype.Service;

/**
 * @ClassName: StateHandlerImpl
 * @Description: 状态处理服务
 * @Author: seven
 * @CreateTime: 2023-03-21 11:53
 * @Version: 1.0
 **/

@Service
public class StateHandlerImpl extends StateConfig implements IStateHandler {
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).arraignment(activityId,currentStatus);
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkPass(activityId, currentStatus);
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkRefuse(activityId, currentStatus);
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkRevoke(activityId, currentStatus);
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).close(activityId, currentStatus);
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).open(activityId, currentStatus);
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).doing(activityId, currentStatus);
    }
}

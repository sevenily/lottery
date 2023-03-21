package com.seven.lottery.doamin.activity.service.stateflow.event;

import com.seven.lottery.common.Constants;
import com.seven.lottery.common.Result;
import com.seven.lottery.doamin.activity.service.stateflow.AbstractState;

/**
 * @ClassName: RefuseState
 * @Description: 审核拒绝状态
 * @Author: seven
 * @CreateTime: 2023-03-21 14:22
 * @Version: 1.0
 **/

public class RefuseState extends AbstractState {
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "已审核状态不可重复提审。");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "已审核状态不可重复审核。");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "已审核状态不可重复拒绝。");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.REVOKE);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "撤销审核完成。") : Result.buildErrorResult("活动状态变更失败。");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "关闭审核完成。") : Result.buildErrorResult("活动状态变更失败。");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "已审核状态不可开启。");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "审核拒绝不可执行活动为进行中。");
    }
}

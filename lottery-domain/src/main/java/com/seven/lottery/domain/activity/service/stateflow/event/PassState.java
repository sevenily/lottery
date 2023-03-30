package com.seven.lottery.domain.activity.service.stateflow.event;

import com.seven.lottery.common.Constants;
import com.seven.lottery.common.Result;
import com.seven.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PassState
 * @Description: 审核通过状态
 * @Author: seven
 * @CreateTime: 2023-03-21 14:15
 * @Version: 1.0
 **/

@Component
public class PassState extends AbstractState {
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
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.REFUSE);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核拒绝成功。") : Result.buildErrorResult("活动状态变更失败。");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "已审核状态不可撤销审核（可先拒绝审核）。");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核关闭成功。") : Result.buildErrorResult("活动状态变更失败。");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "费关闭活动不可开启。");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.DOING);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动变更活动中成功。") : Result.buildErrorResult("活动状态变更失败。");
    }

}

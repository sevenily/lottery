package com.seven.lottery.domain.activity.service.stateflow.event;

import com.seven.lottery.common.Constants;
import com.seven.lottery.common.Result;
import com.seven.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ArraignmentState
 * @Description: 提审状态
 * @Author: seven
 * @CreateTime: 2023-03-21 11:56
 * @Version: 1.0
 **/

@Component
public class ArraignmentState extends AbstractState {


    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "待审核状态不可重复提审。");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.PASS);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核通过完成。") : Result.buildErrorResult("活动转台变更失败。");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.REFUSE);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动状态拒绝完成。") : Result.buildErrorResult("活动状态变更失败。");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.EDIT);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动撤销完成,回到编辑中。。") : Result.buildErrorResult("活动状态变更失败。");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE);
        return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核关闭完成") : Result.buildErrorResult("活动状态变更失败。");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "非关闭活动不可开启。");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {

        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "待审核活动不可执行 活动中 变更。。");
    }
}

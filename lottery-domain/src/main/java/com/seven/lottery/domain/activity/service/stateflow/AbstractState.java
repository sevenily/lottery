package com.seven.lottery.domain.activity.service.stateflow;

import com.seven.lottery.common.Constants;
import com.seven.lottery.common.Result;
import com.seven.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @ClassName: AbstractState
 * @Description:  活动状态抽象类
 * @Author: seven
 * @CreateTime: 2023-03-21 11:19
 * @Version: 1.0
 **/

public abstract class AbstractState {

    @Resource
    protected IActivityRepository activityRepository;

    /**
     *  活动提审
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return
     */
    public abstract Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 审核通过
     * @param activityId 活动ID
     * @param currentState  当前状态
     * @return  执行结果
     */
    public abstract Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     *  审核拒绝
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return  执行结果
     */
    public abstract Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     *  撤销审核
     * @param activityId 活动ID
     * @param currentState  当前状态
     * @return  执行结果
     */
    public abstract Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     *  活动关闭
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return  执行结果
     */
    public abstract Result close(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     *  活动开启
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return  执行结果
     */
    public abstract Result open(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     *  活动执行
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return  执行结果
     */
    public abstract Result doing(Long activityId, Enum<Constants.ActivityState> currentState);

}

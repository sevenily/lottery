package com.lottery.domain.activity.model.req;

import com.lottery.domain.activity.model.aggregates.ActivityConfigRich;

/**
 * @ClassName: ActivityConfigReq
 * @Description: 活动配置请求对象
 * @Author: seven
 * @CreateTime: 2023-03-20 14:56
 * @Version: 1.0
 **/

public class ActivityConfigReq {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动配置信息
     */
    private ActivityConfigRich activityConfigRich;

    public ActivityConfigReq() {
    }

    public ActivityConfigReq(Long activityId, ActivityConfigRich activityConfigRich) {
        this.activityId = activityId;
        this.activityConfigRich = activityConfigRich;
    }


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public ActivityConfigRich getActivityConfigRich() {
        return activityConfigRich;
    }

    public void setActivityConfigRich(ActivityConfigRich activityConfigRich) {
        this.activityConfigRich = activityConfigRich;
    }
}

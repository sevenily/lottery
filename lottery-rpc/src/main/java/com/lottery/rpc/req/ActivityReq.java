package com.lottery.rpc.req;

import java.io.Serializable;

/**
 * @ClassName: ActivityReq
 * @Description: 活动请求数据
 * @Author: seven
 * @CreateTime: 2023-03-30 09:26
 * @Version: 1.0
 **/

public class ActivityReq implements Serializable {
    private Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}

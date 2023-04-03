package com.lottery.domain.activity.service.deploy;

import com.lottery.domain.activity.model.req.ActivityConfigReq;

/**
 * @ClassName: IActivityDeploy
 * @Description: 部署活动配置接口
 * @Author: seven
 * @CreateTime: 2023-03-21 10:14
 * @Version: 1.0
 **/

public interface IActivityDeploy {
    /**
     * 创建活动信息
     * @param req   活动配置信息
     */
    void createActivity(ActivityConfigReq req);

    /**
     *  修改活动信息
     * @param req   活动配置信息
     */
    void updateActivity(ActivityConfigReq req);

}

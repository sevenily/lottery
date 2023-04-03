package com.lottery.domain.activity.service.partake;

import com.lottery.domain.activity.model.req.PartakeReq;
import com.lottery.domain.activity.model.res.PartakeResult;

/**
 * @ClassName: IActivityPartake
 * @Description: 抽奖活动参与接口
 * @Author: seven
 * @CreateTime: 2023-03-21 11:02
 * @Version: 1.0
 **/

public interface IActivityPartake {
    /**
     * 活动参与接口
     */
    PartakeResult doPartake(PartakeReq req);
}

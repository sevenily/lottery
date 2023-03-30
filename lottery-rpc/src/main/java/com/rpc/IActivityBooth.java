package com.rpc;

import com.rpc.req.ActivityReq;
import com.rpc.res.ActivityRes;

/**
 * @ClassName: IActivityBooth
 * @Description: 活动展台
 * 1. 创建活动
 * 2. 更新活动
 * 3. 查询活动
 * @Author: seven
 * @CreateTime: 2023-03-29 14:16
 * @Version: 1.0
 **/


public interface IActivityBooth {

    ActivityRes queryActivityById(ActivityReq req);


}

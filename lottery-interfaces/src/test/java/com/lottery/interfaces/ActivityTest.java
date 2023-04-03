package com.lottery.interfaces;

import com.alibaba.fastjson.JSON;
import com.lottery.domain.activity.model.req.PartakeReq;
import com.lottery.domain.activity.model.res.PartakeResult;
import com.lottery.domain.activity.service.partake.IActivityPartake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName: ActivityTest
 * @Description: 活动测试  分库分表
 * @Author: seven
 * @CreateTime: 2023-03-31 15:37
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.lottery.domain.activity.service.partake.*", "com.lottery.domain.activity.repository"})
public class ActivityTest {

    private Logger logger = LoggerFactory.getLogger(ActivityTest.class);

    @Resource
    private IActivityPartake activityPartake;

    @Test
    public void test_activityPartake(){
        PartakeReq partakeReq = new PartakeReq("ddfsaga", 100003l);
        PartakeResult partakeResult = activityPartake.doPartake(partakeReq);
        logger.info("请求参数：{}", JSON.toJSONString(partakeReq));
        logger.info("测试结果：{}", JSON.toJSONString(partakeResult));


    }
}

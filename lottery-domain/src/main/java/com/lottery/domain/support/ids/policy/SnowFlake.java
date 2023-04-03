package com.lottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.net.NetUtil;
import com.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName: SnowFlake
 * @Description: hutool 工具雪花算法
 * @Author: seven
 * @CreateTime: 2023-03-21 17:17
 * @Version: 1.0
 **/
@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowflake;

    @PostConstruct
    public void init(){
        //0-31 位， 可以采用配置的方式使用
        long workerId;

        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e){
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

        workerId = workerId >> 16 & 31;
        long dataCenterId = 1L;

        snowflake = IdUtil.createSnowflake(workerId, dataCenterId);

    }

    @Override
    public long nextId() {
        return snowflake.nextId();
    }
}

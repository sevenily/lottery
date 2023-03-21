package com.seven.lottery.doamin.support.ids.policy;

import com.seven.lottery.doamin.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName: IIdGenerator
 * @Description: Apache 工具类 RandomStringUtils
 * @Author: seven
 * @CreateTime: 2023-03-21 17:04
 * @Version: 1.0
 **/
@Component
public class RandomNumeric implements IIdGenerator {

    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}

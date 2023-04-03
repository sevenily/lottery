package com.lottery.domain.support.ids;

/**
 * @ClassName: IIdGenerator
 * @Description: 生成ID接口
 * @Author: seven
 * @CreateTime: 2023-03-21 16:59
 * @Version: 1.0
 **/

public interface IIdGenerator {
    /**
     * 获取ID，目前有三种实现方式
     * 1.雪花算法，用于生成单号
     * 2. 日期算法，用于生成活动编号类，特征是生成数字串较短，但指定时间内不能生成太多
     * 3. 随机算法，用于生成策略ID
     * @return
     */
    long nextId();

}

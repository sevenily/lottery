package com.seven.dao;

import com.seven.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: IStrategyDao
 * @Description: 策略表DAO
 * @Author: seven
 * @CreateTime: 2023-03-28 10:27
 * @Version: 1.0
 **/

@Mapper
public interface IStrategyDao {

    /**
     * 查询策略配置
     * @param strategyId    策略ID
     * @return              策略配置信息
     */
    Strategy queryStrategy(Long strategyId);

    /**
     * 插入策略配置
     * @param req   策略配置
     */
    void insert(Strategy req);
}

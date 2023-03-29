package com.seven.dao;

import com.seven.po.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;
import org.lottery.db.annotation.DBRouter;
import org.lottery.db.annotation.DBRouterStrategy;

/**
 * @ClassName: IUserStrategyExportDao
 * @Description: 用户策略计算结果表DAO
 * @Author: seven
 * @CreateTime: 2023-03-28 14:26
 * @Version: 1.0
 **/
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {
    /**
     *  新增数据
     * @param userStrategyExport    用户策略
     */
    @DBRouter(key = "uId")
    void insert(UserStrategyExport userStrategyExport);

    /**
     *  查询数据
     * @param uId   用户ID
     * @return      用户策略
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);
}

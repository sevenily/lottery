package com.lottery.dao;

import com.lottery.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;
import org.lottery.db.annotation.DBRouter;

/**
 * @ClassName: IUserTakeActivityDao
 * @Description: 用户领取活动表DAO
 * @Author: seven
 * @CreateTime: 2023-03-28 14:30
 * @Version: 1.0
 **/
@Mapper
public interface IUserTakeActivityDao {

    /**
     *  插入用户领取活动信息
     * @param userTakeActivity  入参
     */
    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);
}

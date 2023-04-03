package com.lottery.dao;

import com.lottery.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: IAwardDao
 * @Description: 奖品信息表DAO
 * @Author: seven
 * @CreateTime: 2023-03-28 10:24
 * @Version: 1.0
 **/

@Mapper
public interface IAwardDao {
    /**
     * 查询奖品信息
     * @param awardId   奖品ID
     * @return          奖品信息
     */
    Award queryAwardInfo(String awardId);

    /**
     * 插入奖品配置
     * @param list      奖品配置
     */
    void insertList(List<Award> list);

}

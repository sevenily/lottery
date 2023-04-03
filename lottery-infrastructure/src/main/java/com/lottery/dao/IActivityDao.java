package com.lottery.dao;

import com.lottery.domain.activity.model.vo.AlterStateVo;
import com.lottery.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: IActivityDao
 * @Description: 活动基础信息表DAO
 * @Author: seven
 * @CreateTime: 2023-03-28 09:47
 * @Version: 1.0
 **/

@Mapper
public interface IActivityDao {

    /**
     * 插入数据
     * @param req
     */
    void insert(Activity req);

    /**
     *  更具活动号查询活动信息
     * @param activityId    活动号
     * @return  活动信息
     */
    Activity queryActivityById(Long activityId);

    /**
     * 变更活动状态
     * @param alterStateVo  【activityId , beforeState, afterState】
     * @return  更新数量
     */
    int alterState(AlterStateVo alterStateVo);



}

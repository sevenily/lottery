package com.seven.repository;

import com.seven.dao.IActivityDao;
import com.seven.dao.IAwardDao;
import com.seven.dao.IStrategyDao;
import com.seven.dao.IStrategyDetailDao;
import com.seven.lottery.common.Constants;
import com.seven.lottery.domain.activity.model.vo.*;
import com.seven.lottery.domain.activity.repository.IActivityRepository;
import com.seven.po.Activity;
import com.seven.po.Award;
import com.seven.po.Strategy;
import com.seven.po.StrategyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ActivityRepository
 * @Description:
 * @Author: seven
 * @CreateTime: 2023-03-28 14:34
 * @Version: 1.0
 **/

@Component
public class ActivityRepository implements IActivityRepository {

    private Logger logger = LoggerFactory.getLogger(ActivityRepository.class);
    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();

        BeanUtils.copyProperties(activity, req);

        activityDao.insert(req);

    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> req = new ArrayList<>();

        for (AwardVO awardVO : awardList) {
            Award award = new Award();
            BeanUtils.copyProperties(awardVO, award);
            req.add(award);
        }

        awardDao.insertList(req);

    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();

        BeanUtils.copyProperties(strategy, req);

        strategyDao.insert(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();

        for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetail, strategyDetailVO);
        }

        strategyDetailDao.insertList(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVo alterStateVo = new AlterStateVo(activityId, ((Constants.ActivityState) beforeState).getCode(),
                ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterState(alterStateVo);

        return 1 == count;
    }
}

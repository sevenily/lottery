package com.seven.lottery.doamin.activity.service.stateflow;

import com.seven.lottery.common.Constants;
import com.seven.lottery.doamin.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: StateConfig
 * @Description: 状态流转配置
 * @Author: seven
 * @CreateTime: 2023-03-21 11:18
 * @Version: 1.0
 **/

public class StateConfig {
    @Resource
    private ArraignmentState arraignmentState;

    @Resource
    private CloseState closeState;

    @Resource
    private DoingState doingState;

    @Resource
    private EditingState editingState;

    @Resource
    private OpenState openState;

    @Resource
    private PassState passState;

    @Resource
    private RefuseState refuseState;

    protected Map<Enum<Constants.ActivityState>, AbstractState> stateMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        stateMap.put(Constants.ActivityState.ARRAIGNMENT, arraignmentState);
        stateMap.put(Constants.ActivityState.CLOSE, closeState);
        stateMap.put(Constants.ActivityState.DOING, doingState);
        stateMap.put(Constants.ActivityState.EDIT, editingState);
        stateMap.put(Constants.ActivityState.OPEN, openState);
        stateMap.put(Constants.ActivityState.PASS, passState);
        stateMap.put(Constants.ActivityState.REFUSE, refuseState);

    }

}

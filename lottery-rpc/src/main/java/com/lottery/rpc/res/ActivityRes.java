package com.lottery.rpc.res;

import com.lottery.rpc.dto.ActivityDto;
import com.lottery.common.Result;

import java.io.Serializable;

/**
 * @ClassName: ActivityRes
 * @Description: 活动响应数据
 * @Author: seven
 * @CreateTime: 2023-03-30 09:27
 * @Version: 1.0
 **/

public class ActivityRes implements Serializable {
    private Result result;

    private ActivityDto activity;

    public ActivityRes(Result result) {
        this.result = result;
    }

    public ActivityRes(Result result, ActivityDto activity) {
        this.result = result;
        this.activity = activity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }
}

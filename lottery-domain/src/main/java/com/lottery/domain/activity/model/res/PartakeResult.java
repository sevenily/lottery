package com.lottery.domain.activity.model.res;

import com.lottery.common.Result;

/**
 * @ClassName: PartakeResult
 * @Description: 活动参与结果
 * @Author: seven
 * @CreateTime: 2023-03-31 09:21
 * @Version: 1.0
 **/

public class PartakeResult extends Result {
    /**
     * 策略ID
     */
    private Long strategyId;

    public PartakeResult(String code, String info) {
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}

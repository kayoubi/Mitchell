package com.mitchell.claims.domain.builder;

import com.mitchell.claims.domain.LossInfo;

/**
 * @author Khaled Ayoubi
 */
public class LossInfoBuilder {
    private LossInfo lossInfo;

    public LossInfoBuilder() {
        lossInfo = new LossInfo();
    }

    public LossInfoBuilder withCauseOfLoss(String causeOfLoss) {
        lossInfo.setCauseOfLoss(causeOfLoss);
        return this;
    }

    public LossInfo build() {
        return lossInfo;
    }
}

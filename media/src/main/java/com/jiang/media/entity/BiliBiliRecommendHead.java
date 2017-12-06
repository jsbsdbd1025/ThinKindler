package com.jiang.media.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by knowing on 2017/12/6.
 */

public class BiliBiliRecommendHead extends SectionEntity<BiliBiliRecommendBody> {

    public BiliBiliRecommendHead(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public BiliBiliRecommendHead(BiliBiliRecommendBody biliBiliRecommendBody) {
        super(biliBiliRecommendBody);
    }
}

package com.jiang.common.recyclerview;

import android.view.View;



/**
 * Created by DB on 2016/11/1.
 */

public class EmptyHolder extends IViewHolder {

    public EmptyHolder(View itemView) {
        super(itemView);
    }

    public void setErrorType(PlaceHolderType status) {
//        this.setText(R.id.tv_placeholder, status.getTitle())
//                .setImageResource(R.id.img_placeholder, status.getIcon());
    }
}

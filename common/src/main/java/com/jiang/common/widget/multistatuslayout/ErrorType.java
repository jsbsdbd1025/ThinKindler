package com.jiang.common.widget.multistatuslayout;

/**
 * Created by jiang on 2017/7/15.
 */

public enum ErrorType {

    EMPTY(0, 0);

    int txtId;
    int imgId;

    ErrorType(int txtId, int imgId) {
        this.txtId = txtId;
        this.imgId = imgId;
    }


    public int getText() {
        return txtId;
    }

    public int getIcon() {
        return imgId;
    }
}




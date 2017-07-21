package com.jiang.thinkindler.douban;


import com.jiang.thinkindler.net.model.DoubanModel;
import com.jiang.thinkindler.ui.douban.contract.DoubanMainContract;
import com.jiang.thinkindler.ui.douban.presenter.DoubanMainPresenter;
import com.jiang.thinkindler.utils.pagelist.PageListHelper;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jiang on 2017/6/21.
 */
public class DoubanMainPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    DoubanModel mModel;
    @Mock
    DoubanMainContract.View mView;
    @Mock
    PageListHelper mPageListHelper;

    @InjectMocks
    DoubanMainPresenter mPresenter;

    @Test
    public void testTrue() {
        Mockito.when(mModel.check(2, 2)).thenReturn(true);
//        mPresenter.check(2, 2);
        assertTrue(mPresenter.check(2, 2));
    }

    @Test
    public void testFalse() {
        Mockito.when(mModel.check(1, 2)).thenReturn(false);
        mPresenter.check(1, 2);
        assertFalse(mPresenter.check(1, 2));
    }
}

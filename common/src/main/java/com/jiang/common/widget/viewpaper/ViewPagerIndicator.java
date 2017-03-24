package com.jiang.common.widget.viewpaper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jiang.common.R;
import com.jiang.common.utils.DisplayUtil;

import java.util.List;


public class ViewPagerIndicator extends LinearLayout {


    private int mWidth = 0;
    private Paint mPaint;

    private Path mPath;

    private int mLineWidth;

    private int mTriangleWidth;
    private int mTriangleHeight;

    private int mInitTranslationX;

    private int mTranslationX;

    private static final int DEFAUT_TAG_COUNT = 3;

    private int mTabVisibleCount;

    private boolean hasSplitLine = true;
    private List<String> mTitles;


    private static int DEFAUT_COLOR_TEXT_NORMAL = 0xff999999;
    private static int DEFAUT_COLOR_TEXT_HIGHLIGHT = 0xff6381DB;
    private static int DEFAUT_COLOR_BASELINE = 0xff6381DB;
    private int DEFAUT_TEXT_SIZE = 0;
    private int mTextHightLight;
    private int mTextNormal;
    private int mBaseLine;
    private int mTextSize;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }


    public ViewPagerIndicator(Context context, AttributeSet attrs) {

        super(context, attrs);
        // 获取可见Tab的数量
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);

        mTabVisibleCount = ta.getInt(R.styleable.ViewPagerIndicator_visible_tab_count, DEFAUT_TAG_COUNT);
        if (mTabVisibleCount < 0) {
            mTabVisibleCount = DEFAUT_TAG_COUNT;
        }

        mBaseLine = ta.getColor(R.styleable.ViewPagerIndicator_color_baseline, DEFAUT_COLOR_BASELINE);

        mTextNormal = ta.getColor(R.styleable.ViewPagerIndicator_color_text_normal, DEFAUT_COLOR_TEXT_NORMAL);

        mTextHightLight = ta.getColor(R.styleable.ViewPagerIndicator_color_text_high, DEFAUT_COLOR_TEXT_HIGHLIGHT);

        DEFAUT_TEXT_SIZE = DisplayUtil.dip2px(context, 16);
        mTextSize = (int) ta.getDimension(R.styleable.ViewPagerIndicator_tab_text_size, DEFAUT_TEXT_SIZE);
        ta.recycle();
        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mBaseLine);
        mPaint.setStyle(Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        mLineWidth = (int) (w / mTabVisibleCount);
        // mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGLE_WIDTH);
//        mInitTranslationX = 0;
//        mInitTranslationX = w / mTabVisibleCount / 2 - mLineWidth / 2;
//        initLine();
        // initTriangle();
//        mWidth = w;

    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        int cCount = getChildCount();
        if (cCount == 0) return;

        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight = 1;
            lp.width = 0;
        }
        setItemClickEvent();
    }

//    private int getScreenWidth() {
//        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(outMetrics);
//        return outMetrics.widthPixels;
//    }


    private void initTriangle() {
        mTriangleHeight = mTriangleWidth / 2;
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth, -mTriangleHeight);
        mPath.lineTo(0, -mTriangleHeight);
        mPath.close();
    }

    /**
     * 初始化下划线
     */
    private void initLine() {
        mInitTranslationX = DisplayUtil.getScreenWidth(getContext()) / mTabVisibleCount / 2 - mLineWidth / 2;
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mLineWidth, 0);
        mPath.lineTo(mLineWidth, -DisplayUtil.dip2px(getContext(), 2));
        mPath.lineTo(0, -DisplayUtil.dip2px(getContext(), 2));
        mPath.close();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitTranslationX + mTranslationX, getHeight());
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        super.dispatchDraw(canvas);
    }

    /**
     * 指示器跟随手指进行滚动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / mTabVisibleCount;
        mTranslationX = (int) (tabWidth * (offset + position));


        // 容器移动，在tab处于移动的最后一个时
        if (position >= mTabVisibleCount - 2 && offset > 0 && getChildCount() > mTabVisibleCount) {

            if (mTabVisibleCount != 1) {
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
                        + (int) (tabWidth * offset), 0);
            } else {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }
        }
        invalidate();
    }

    public void setTabItemTitiles(List<String> titles) {
        mTitles = titles;
        drawViewToLayout();
    }

    private void drawViewToLayout() {
        if (mTitles != null && mTitles.size() > 0) {
            this.removeAllViews();
            for (String title : mTitles) {
                addView(generateTextView(title));
            }
        }
        setItemClickEvent();
    }


    /**
     * 根据title创建Tab
     *
     * @param title
     * @return
     */
    private View generateTextView(String title) {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams lp =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        if (mLineWidth == 0) {
            mLineWidth = (int) textView.getPaint().measureText(textView.getText().toString());
        }
        lp.width = 0;
        lp.weight = 1;
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        textView.setTextColor(mTextNormal);
        textView.setLayoutParams(lp);
        return textView;
    }

    private ViewPager mViewPager;

    public interface PageOnChangeListener {
        public void onPageSelected(int posiiton);

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        public void onPageScrollStateChanged(int state);
    }

    public PageOnChangeListener mListener;

    public void setOnPageChangeListener(PageOnChangeListener listener) {
        this.mListener = listener;
    }

    /**
     * 设置关联的Viewpager
     *
     * @param viewPager
     * @param pos
     */
    public void setViewPager(ViewPager viewPager, int pos) {
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int posiiton) {
                if (mListener != null) {
                    mListener.onPageSelected(posiiton);
                }
                highLightTextView(posiiton);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListener != null) {
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });

        mViewPager.setCurrentItem(pos);
        highLightTextView(pos);
    }

    /**
     * 设置可见的tab数量
     *
     * @param count
     */
    public void setVisibleTabCount(int count) {
        mTabVisibleCount = count;
    }

    /**
     * 高亮某个Tab的文本
     *
     * @param pos
     */
    private void highLightTextView(int pos) {

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                if (i == pos) {
                    ((TextView) view).setTextColor(mTextHightLight);
                    TextView textView = (TextView) view;
                    mLineWidth = (int) textView.getPaint().measureText(textView.getText().toString());
                    initLine();
                } else {
                    ((TextView) view).setTextColor(mTextNormal);
                }
            }
        }
    }


    private void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            final int pos = i;
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(pos);
                }
            });
        }
    }

    public void setColorTextNormal(int colorTextNormal) {
        mTextNormal = colorTextNormal;
    }

    public void setColorTextHightLight(int colorTextHightLight) {
        mTextHightLight = colorTextHightLight;
    }


    public void setColorBaseline(int colorBaseline) {
        mBaseLine = colorBaseline;
        mPaint.setColor(mBaseLine);
    }

    public void setTextSize(int size) {
        this.mTextSize = size;
//        invalidate();
    }
}

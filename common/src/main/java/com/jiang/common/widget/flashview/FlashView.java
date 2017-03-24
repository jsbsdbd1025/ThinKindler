package com.jiang.common.widget.flashview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.jiang.common.R;
import com.jiang.common.utils.DisplayUtil;
import com.jiang.common.widget.flashview.effect.AccordionTransformer;
import com.jiang.common.widget.flashview.effect.CubeTransformer;
import com.jiang.common.widget.flashview.effect.DefaultTransformer;
import com.jiang.common.widget.flashview.effect.DepthPageTransformer;
import com.jiang.common.widget.flashview.effect.InRightDownTransformer;
import com.jiang.common.widget.flashview.effect.InRightUpTransformer;
import com.jiang.common.widget.flashview.effect.RotateTransformer;
import com.jiang.common.widget.flashview.effect.ZoomOutPageTransformer;
import com.jiang.common.widget.flashview.listener.FlashViewListener;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Android将军
 */
@SuppressLint("HandlerLeak")
public class FlashView extends FrameLayout {

    private ImageHandler mhandler = new ImageHandler(new WeakReference<FlashView>(this));
    private List<BannerBean> datas;
    private Context context;
    private List<BannerView> bannerViewsList;
    private List<ImageView> dotViewsList;
    private LinearLayout mLinearLayout;
    private ViewPager mViewPager;
    private FlashViewListener mFlashViewListener;//向外提供接口
    private boolean isTwo = false;
    private int pos;
    private int effect;//图片切换的动画效果

    public FlashView(Context context) {
        this(context, null);

    }

    public FlashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlashView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        //读取该自定义控件自定义的属性
        this.context = context;
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.FlashView);
        effect = mTypedArray.getInt(R.styleable.FlashView_effect, 2);

        initUI(context);
    }

    /**
     * 设置监听
     *
     * @param mFlashViewListener
     */
    public void setOnPageClickListener(FlashViewListener mFlashViewListener) {

        this.mFlashViewListener = mFlashViewListener;
    }

    private void initUI(Context context) {
        bannerViewsList = new ArrayList<BannerView>();
        dotViewsList = new ArrayList<ImageView>();
        datas = new ArrayList<BannerBean>();
        LayoutInflater.from(context).inflate(R.layout.layout_flashview, this, true);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        //mFlashViewListener必须实例化

    }

    public void setDatas(List<BannerBean> list) {

        if (datas.size() > 0) {
            datas.clear();
            bannerViewsList.clear();
            dotViewsList.clear();
            mLinearLayout.removeAllViews();
        }

        if (list.size() <= 0) {
            datas.add(new BannerBean());
        } else {
            if (list.size() == 2) {
                isTwo = true;
                datas.addAll(list);
                datas.addAll(list);
            } else {
                isTwo = false;
                datas.addAll(list);
            }

        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(DisplayUtil.dip2px(8), 0, 0, 0);
        for (int i = 0; i < datas.size(); i++) {
            BannerView bannerView = new BannerView(getContext());
            bannerView.bindData(datas.get(i));
            bannerViewsList.add(bannerView);
            ImageView viewDot = new ImageView(getContext());
            viewDot.setBackgroundResource(R.drawable.sl_banner_dot);
            if (i == 0) {
                viewDot.setSelected(true);
            } else {
                viewDot.setSelected(false);
            }
            viewDot.setLayoutParams(lp);
            if (isTwo)//为两张图片时加入的判断
            {
                if (i > 1) {

                } else {
                    dotViewsList.add(viewDot);
                    mLinearLayout.addView(viewDot);
                }
            } else {
                dotViewsList.add(viewDot);
                mLinearLayout.addView(viewDot);
            }

        }
        mViewPager.setFocusable(true);
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.setOnPageChangeListener(new MyPageChangeListener());
        setEffect(effect);
        if (datas.size() <= 1)//图片小于等于1张时，不轮播
        {

        } else {

            // 利用反射修改自动轮播的动画持续时间
            try {

                Field field = ViewPager.class.getDeclaredField("mScroller");

                field.setAccessible(true);

                FixedSpeedScroller scroller = new FixedSpeedScroller(
                        mViewPager.getContext(), new AccelerateInterpolator());

                field.set(mViewPager, scroller);

                scroller.setmDuration(500);

                mViewPager.setCurrentItem(100 * bannerViewsList.size());

                mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,
                        ImageHandler.MSG_DELAY);

            } catch (Exception e) {

            }
        }

    }

    /**
     * 切换轮播小点的显示
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < dotViewsList.size(); i++) {
            if (i == selectItems % dotViewsList.size()) {
                dotViewsList.get(i).setSelected(true);
            } else {
                dotViewsList.get(i).setSelected(false);
            }
        }
    }

    /**
     * 数据适配器
     */
    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public void destroyItem(View container, int position, Object object) {

        }

        @Override
        public Object instantiateItem(View container, int position) {

            position = position % bannerViewsList.size();


            if (position < 0) {
                position = position + bannerViewsList.size();

            }
            if (isTwo) {
                pos = position % 2;
            } else {
                pos = position;
            }
            final int posclick = pos;
            View view = bannerViewsList.get(position);
//		 if(view==null)
//		 {
//		 view = imageViewsList.get(position);
//		 }
            view.setTag(position);
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mFlashViewListener != null) {
                        mFlashViewListener.onClick(posclick);
                    } else {

                    }

                }
            });
            ViewParent vp = view.getParent();
            if (vp != null) {

                ViewPager pager = (ViewPager) vp;
                pager.removeView(view);
            }
            ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public int getCount() {
            if (datas.size() <= 1) {
                return 1;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    private class MyPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

            switch (arg0) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    mhandler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);

                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                    break;
//		 case ViewPager.SCROLL_STATE_SETTLING:
//		 System.out.println("GGGGGGGGG");
//		 mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,ImageHandler.MSG_DELAY);
//		 break;
                default:
                    break;
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
//		 System.out.println("GGGGGGGGG");
//		 mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,ImageHandler.MSG_DELAY);
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            mhandler.sendMessage(Message.obtain(mhandler, ImageHandler.MSG_PAGE_CHANGED, pos, 0));

            setImageBackground(pos);

        }

    }

    @SuppressWarnings("unused")
    private void destoryBitmaps() {
        for (int i = 0; i < bannerViewsList.size(); i++) {
            ImageView imageView = bannerViewsList.get(i).getImageView();
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
            }
        }
    }

    public void setEffect(int selectEffect) {
        switch (selectEffect) {
            case 0:
                setPageTransformer(true, new AccordionTransformer());
                break;
            case 1:
                setPageTransformer(true, new CubeTransformer());
                break;
            case 2:
                setPageTransformer(true, new DefaultTransformer());
                break;
            case 3:
                setPageTransformer(true, new DepthPageTransformer());
                break;
            case 4:
                setPageTransformer(true, new InRightDownTransformer());
                break;
            case 5:
                setPageTransformer(true, new InRightUpTransformer());
                break;
            case 6:
                setPageTransformer(true, new RotateTransformer());
                break;
            case 7:
                setPageTransformer(true, new ZoomOutPageTransformer());

                break;
            default:
                break;
        }
    }

    /**
     * 设置切换效果
     *
     * @param b
     * @param rotateTransformer
     */
    public void setPageTransformer(boolean b, PageTransformer rotateTransformer) {
        // TODO Auto-generated method stub
        mViewPager.setPageTransformer(b, rotateTransformer);
    }

    /**
     * FixedSpeedScroller类的源码来源于网络，在此谢过贡献此代码的道友
     */
    public class FixedSpeedScroller extends Scroller {
        private int mDuration = 5000;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {

            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {

            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }
    }

    private static class ImageHandler extends Handler {

        protected static final int MSG_UPDATE_IMAGE = 1;

        protected static final int MSG_KEEP_SILENT = 2;

        protected static final int MSG_BREAK_SILENT = 3;

        protected static final int MSG_PAGE_CHANGED = 4;

        protected static final long MSG_DELAY = 2000;

        private WeakReference<FlashView> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<FlashView> wk) {
            weakReference = wk;
            System.out.println("dsfdsfdsf:::" + currentItem);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            FlashView activity = weakReference.get();
            if (activity == null) {
                return;
            }
            if (activity.mhandler.hasMessages(MSG_UPDATE_IMAGE)) {
                if (currentItem > 0)// 这里必须加入currentItem>0的判断，否则不能完美的自动轮播
                {
                    activity.mhandler.removeMessages(MSG_UPDATE_IMAGE);
                }
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
//		 System.out.println("cccccc:::" + currentItem);
                    currentItem++;
                    activity.mViewPager.setCurrentItem(currentItem);
                    activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    break;
                case MSG_BREAK_SILENT:
                    activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    currentItem = msg.arg1;
                    activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                default:
                    break;
            }
        }
    }
}


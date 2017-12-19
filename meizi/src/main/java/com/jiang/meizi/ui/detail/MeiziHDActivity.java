package com.jiang.meizi.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.alexvasilkov.gestures.animation.ViewPosition;
import com.alexvasilkov.gestures.animation.ViewPositionAnimator;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.jiang.common.base.CommonActivity;
import com.jiang.common.utils.imageloader.ImageLoaderUtil;
import com.jiang.meizi.R;
import com.jiang.meizi.entity.event.ImageStatusEvent;
import com.jiang.meizi.entity.event.ViewPositionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by knowing on 2017/12/13.
 */

public class MeiziHDActivity extends CommonActivity {

    private static final String EXTRA_POSITION = "position";

    private static final String EXTRA_URL = "url";

    private GestureImageView image;
    private View background;

    public static void startAction(CommonActivity activity, ViewPosition viewPosition, String url) {

        Intent intent = new Intent(activity, MeiziHDActivity.class);
        intent.putExtra(EXTRA_POSITION, viewPosition.pack());
        intent.putExtra(EXTRA_URL, url);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0); // No activity animation
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_meizi_hd);

        EventBus.getDefault().register(this);

        image = (GestureImageView) findViewById(R.id.single_image_to);
        background = findViewById(R.id.single_image_to_back);

        // Making sure image and background are invisible at first
        image.setVisibility(View.INVISIBLE);
        background.setVisibility(View.INVISIBLE);

        ImageLoaderUtil.getInstance().display(image, getIntent().getStringExtra(EXTRA_URL), this, null);

        // Listening for animation state and updating our view accordingly
        image.getPositionAnimator().addPositionUpdateListener(new ViewPositionAnimator.PositionUpdateListener() {
            @Override
            public void onPositionUpdate(float position, boolean isLeaving) {
                applyImageAnimationState(position, isLeaving);
            }
        });

        // Starting enter image animation only once image is drawn for the first time to prevent
        // image blinking on activity start
        runAfterImageDraw(() -> {
            // Enter animation should only be played if activity is not created from saved state
            enterFullImage(savedInstanceState == null);

            // Hiding original image
            EventBus.getDefault().post(new ImageStatusEvent(true));
        });
    }

    private void enterFullImage(boolean animate) {
        // Updating gesture image settings
//        getSettingsController().apply(image);

        // Playing enter animation from provided position
        ViewPosition position = ViewPosition.unpack(getIntent().getStringExtra(EXTRA_POSITION));
        image.getPositionAnimator().enter(position, animate);
    }

    private void applyImageAnimationState(float position, boolean isLeaving) {
        boolean isFinished = position == 0f && isLeaving; // Exit animation is finished

        background.setAlpha(position);
        background.setVisibility(isFinished ? View.INVISIBLE : View.VISIBLE);
        image.setVisibility(isFinished ? View.INVISIBLE : View.VISIBLE);

        if (isFinished) {
            // Showing back original image
            EventBus.getDefault().post(new ImageStatusEvent(true));

            // By default end of exit animation will return GestureImageView into
            // fullscreen state, this will make the image blink. So we need to hack this
            // behaviour and keep image in exit state until activity is finished.
            image.getController().getSettings().disableBounds();
            image.getPositionAnimator().setState(0f, false, false);

            runOnNextFrame(() -> {
                finish();
                overridePendingTransition(0, 0);
            });
        }
    }

    /**
     * Runs provided action after image is drawn for the first time.
     */
    private void runAfterImageDraw(final Runnable action) {
        image.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                image.getViewTreeObserver().removeOnPreDrawListener(this);
                runOnNextFrame(action);
                return true;
            }
        });
        image.invalidate();
    }

    private void runOnNextFrame(Runnable action) {
        final long frameLength = 17L; // 1 frame at 60 fps
        image.postDelayed(action, frameLength);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImagePositionChanged(ViewPositionEvent event) {
        // If original image position is changed we should update animator with its new position.
        if (image.getPositionAnimator().getPosition() > 0f) {
            image.getPositionAnimator().update(event.getPosition());
        }
    }

    @Override
    public void onBackPressed() {
        // We should leave full image mode instead of finishing this activity,
        // activity itself should only be finished in the end of the "exit" animation.
        if (!image.getPositionAnimator().isLeaving()) {
            image.getPositionAnimator().exit(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}

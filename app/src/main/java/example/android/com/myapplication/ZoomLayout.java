package example.android.com.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class ZoomLayout extends FrameLayout {
    public ZoomLayout(Context context) {
        super(context);
    }

    public ZoomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void applyScaleAndTranslation(View cardLayout, View childView) {

        float SCALE = 1.0f;

        float XASPECT_RATIO = (float) this.getWidth() / childView.getWidth();
        float YASPECT_RATIO = (float) this.getHeight() / childView.getHeight();

        SCALE = XASPECT_RATIO < YASPECT_RATIO ? XASPECT_RATIO : YASPECT_RATIO;


        float CENTER_X_SCREEN = this.getWidth() / 2.0f;

        float y = cardLayout.getHeight();
        float y2 = y * SCALE;
        float y3 = childView.getY() * SCALE;

        float _dx = SCALE * (CENTER_X_SCREEN - (childView.getX() + ((float) childView.getWidth() / 2.0f)));
        float _dy = ((y2 - y) / 2.0f) - y3;

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(cardLayout, "scaleX", SCALE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(cardLayout, "scaleY", SCALE);
        ObjectAnimator transX = ObjectAnimator.ofFloat(cardLayout, "translationX", _dx);
        ObjectAnimator transY = ObjectAnimator.ofFloat(cardLayout, "y", _dy);
        AnimatorSet anim = new AnimatorSet();
        anim.play(transX)
                .with(transY)
                .with(scaleX)
                .with(scaleY);
        anim.setDuration(2000);
        anim.start();
    }

    public void revertZoom(View cardLayout2){
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(cardLayout2, "scaleX", 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(cardLayout2, "scaleY", 1.0f);
        ObjectAnimator transX = ObjectAnimator.ofFloat(cardLayout2, "translationX", 0);
        ObjectAnimator transY = ObjectAnimator.ofFloat(cardLayout2, "translationY", 0);
        AnimatorSet anim = new AnimatorSet();
        anim.play(transX)
                .with(transY)
                .with(scaleX)
                .with(scaleY);
        anim.setDuration(2000);
        anim.start();
    }

}
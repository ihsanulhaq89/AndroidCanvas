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

    public void applyScaleAndTranslation(View cardLayout2, View childView) {
        float CENTER_X_SCREEN = this.getWidth() / 2.0f;
        float SCALE = 1.5f;

        float y = cardLayout2.getHeight();
        float y2 = y * SCALE;
        float y3 = childView.getY() * SCALE;

        float _dx = SCALE * (CENTER_X_SCREEN - (childView.getX() + ((float) childView.getWidth() / 2.0f)));
        float _dy = ((y2 - y) / 2.0f) - y3;

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(cardLayout2, "scaleX", SCALE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(cardLayout2, "scaleY", SCALE);
        ObjectAnimator transX = ObjectAnimator.ofFloat(cardLayout2, "translationX", _dx);
        ObjectAnimator transY = ObjectAnimator.ofFloat(cardLayout2, "y", _dy);
        AnimatorSet anim = new AnimatorSet();
        anim.play(transX)
                .with(transY)
                .with(scaleX)
                .with(scaleY);
        anim.setDuration(2000);
        anim.start();
    }
}
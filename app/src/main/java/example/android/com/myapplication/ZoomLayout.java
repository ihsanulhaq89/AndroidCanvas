package example.android.com.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class ZoomLayout extends FrameLayout {
    private float scale = 1.5f;

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

        int centerX = cardLayout.getWidth() / 2;
        int centerY = cardLayout.getHeight() / 2;

        float dx = centerX - (childView.getX() + childView.getWidth() / 2);
        float dy = centerY - (childView.getY() + childView.getHeight() / 2);

        executeAnimations(dx, dy);
    }

    private void executeAnimations(float _dx, float _dy){
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(child(), "scaleX", scale);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(child(), "scaleY", scale);
        ObjectAnimator transX = ObjectAnimator.ofFloat(child(), "translationX", child().getX(), _dx * scale);
        ObjectAnimator transY = ObjectAnimator.ofFloat(child(), "translationY", child().getY(), _dy * scale);
        AnimatorSet anim = new AnimatorSet();
        anim.play(scaleX).with(scaleY).with(transX).with(transY);
        anim.start();
    }


    private View child() {
        return getChildAt(0);
    }
}
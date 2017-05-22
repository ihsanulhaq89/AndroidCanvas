package example.android.com.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnLayoutChangeListener {

    FrameLayout cardLayout;
    final double FRAME_X = 200;
    final double FRAME_Y = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardLayout = (FrameLayout) findViewById(R.id.card_layout);
        cardLayout.addOnLayoutChangeListener(this);

    }

    double getScaleX() {
        return ((double) cardLayout.getWidth()) / FRAME_X;
    }

    double getScaleY() {
        return ((double) cardLayout.getHeight()) / FRAME_Y;
    }

    private void addImage(@ColorInt int resource, double x, double y, double w, double h) {
        ImageView image = createImage(resource);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) (w * getScaleX()), (int) (h * getScaleY()));
        params.leftMargin = (int) (x * getScaleX());
        params.topMargin = (int) (y * getScaleY());
        image.setLayoutParams(params);
        cardLayout.addView(image);

    }

    private ImageView createImage(@ColorInt int resource) {
        ImageView image = new ImageView(this);
        image.setBackgroundColor(resource);
        return image;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        cardLayout.removeOnLayoutChangeListener(this);
        Toast.makeText(this, "Canvas W= " + cardLayout.getWidth() + " H= " + cardLayout.getHeight() + " DPI=" + getResources().getDisplayMetrics().density, Toast.LENGTH_LONG).show();
        addImage(Color.RED, 50, 50, 50, 50);
        addImage(Color.GREEN, 100, 100, 100, 100);
    }
}

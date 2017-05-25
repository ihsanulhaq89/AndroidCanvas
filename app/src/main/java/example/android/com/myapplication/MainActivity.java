package example.android.com.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnLayoutChangeListener, View.OnClickListener {

    FrameLayout cardLayout;
    ZoomLayout zoomLayout;

    private double SCALE_X;
    private double SCALE_Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardLayout = (FrameLayout) findViewById(R.id.card_layout);
        zoomLayout = (ZoomLayout) findViewById(R.id.zoom_layout);
        cardLayout.addOnLayoutChangeListener(this);
        zoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomLayout.revertZoom(cardLayout);
            }
        });

    }

    private void setScaleFactors() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 4 * metrics.xdpi;
        float py = 8 * metrics.ydpi;
        SCALE_X = ((double) cardLayout.getWidth()) / px;
        SCALE_Y = ((double) cardLayout.getHeight()) / py;
    }

    private void addImage(@ColorInt int resource, double x, double y, double w, double h) {
        ImageView image = createImage(resource);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) (w * SCALE_X), (int) (h * SCALE_Y));
        params.leftMargin = (int) (x * SCALE_X);
        params.topMargin = (int) (y * SCALE_Y);
        image.setLayoutParams(params);
        cardLayout.addView(image);

    }

    private ImageView createImage(@ColorInt int resource) {
        ImageView image = new ImageView(this);
        image.setBackgroundColor(resource);
        // to modify properties of image
        image.setOnClickListener(this);
        return image;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        cardLayout.removeOnLayoutChangeListener(this);
        setScaleFactors();
        Toast.makeText(this, "Canvas W= " + cardLayout.getWidth() + " H= " + cardLayout.getHeight(), Toast.LENGTH_LONG).show();
        addImage(Color.RED, 50, 50, 50, 50); //
        addImage(Color.GREEN, 100, 100, 100, 100);
    }

    @Override
    public void onClick(View v) {
        zoomLayout.applyScaleAndTranslation(cardLayout, v);
    }
}

package com.dean.floatbackground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dean.library.FloatBackground;

public class MainActivity extends AppCompatActivity {

    private static final float[][] STAR_LOCATION = new float[][]{
            {0.5f, 0.2f}, {0.68f, 0.35f}, {0.5f, 0.05f},
            {0.15f, 0.15f}, {0.5f, 0.5f}, {0.15f, 0.8f},
            {0.2f, 0.3f}, {0.77f, 0.4f}, {0.75f, 0.5f},
            {0.8f, 0.55f}, {0.9f, 0.6f}, {0.1f, 0.7f},
            {0.1f, 0.1f}, {0.7f, 0.8f}, {0.5f, 0.6f}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatBackground floatBackground = (FloatBackground) this.findViewById(R.id.float_view);

        Button start = (Button) this.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatBackground.startFloat();
            }
        });

        Button end = (Button) this.findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatBackground.endFloat();
            }
        });

        floatBackground.addFloatView(new FloatRect(0.2f, 0.3f, 30, 40));
        floatBackground.addFloatView(new FloatRect(0.9f, 0.3f, 140, 30));
        floatBackground.addFloatView(new FloatRect(0.1f, 0.1f, 170, 30));
        floatBackground.addFloatView(new FloatBitmap( this, 0.2f, 0.3f, R.drawable.gr_ptn_03));
        floatBackground.addFloatView(new FloatBitmap( this, 0.8f, 0.3f, R.drawable.ico_setting));
        floatBackground.addFloatView(new FloatCircle( 0.8f, 0.8f));
        floatBackground.addFloatView(new FloatBitmap( this, 0.1f, 0.6f, R.drawable.gr_ptn_03));
        floatBackground.addFloatView(new FloatText( 0.3f, 0.6f, "E"));
        floatBackground.addFloatView(new FloatText( 0.5f, 0.6f, "S"));
        floatBackground.addFloatView(new FloatRing( 0.4f, 0.8f, 10 ,40));
        floatBackground.addFloatView(new FloatRing( 0.6f, 0.2f, 15 ,20));
        floatBackground.addFloatView(new FloatBitmap( this, 0.3f, 0.7f, R.drawable.ico_camera));
        floatBackground.addFloatView(new FloatBitmap( this, 0.8f, 0.2f, R.drawable.gr_ptn_05));
    }
}

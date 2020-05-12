package org.techtown.imagescaletype;

import android.graphics.Matrix;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        Matrix matrix = new Matrix();
        matrix.postRotate(45.0f);

        imageView.setImageMatrix(matrix);
    }
}

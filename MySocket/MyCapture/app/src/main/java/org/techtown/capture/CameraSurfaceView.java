package org.techtown.capture;

import android.content.Context;
import android.graphics.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    SurfaceHolder holder;
    android.hardware.Camera camera = null;

    public CameraSurfaceView(Context context) {
        super(context);

        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        holder = getHolder();
        holder.addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = android.hardware.Camera.open();

        try {
            camera.setPreviewDisplay(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
    }


    // 없어질 때.
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.startPreview();
        camera.release();
        camera = null;
    }

    public boolean capture(android.hardware.Camera.PictureCallback callback) {
        if(callback!=null) {
            camera.takePicture(null,null,callback);
            return true;
        }
        else {
            return false;
        }
    }
}

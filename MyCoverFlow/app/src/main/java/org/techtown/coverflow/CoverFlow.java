package org.techtown.coverflow;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class CoverFlow extends Gallery {


    // graphic 으로 선택
    Camera camera = new Camera();


    public static int maxRotationAngle = 55;

    public static int maxZoom = -60;

    private int centerPoint;

    public CoverFlow(Context context) {
        super(context);
        init(context);
    }

    public CoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        // 각각의 아이템(이미지)에 대해서 변형시키는 것을 가능하게 해주겠다!
        setStaticTransformationsEnabled(true);
    }


    /*
    아이템으로 들어간 이미지를 변형할 때 그 변형된 상태로 아이템이 보여지게 하기 위해!
     */
    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {

        int childCenter = child.getLeft()+child.getWidth()/2;
        int childWidth = child.getWidth();

        int rotationAngle=0;
        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);


        // 아이템이 가운데에 보이고 있는 경우
        if(childCenter==centerPoint) {
            transformImageBitmap((ImageView) child, t, 0);
        } else {
            // 가운데로 멀어질 수록 더욱더 회전시킴.
            rotationAngle = (int) (((float)(centerPoint - childCenter)/childWidth)*maxRotationAngle);
            if(Math.abs(rotationAngle)>maxRotationAngle) {
                // 왼쪽과 오른쪽에 대해서 로테이션을 반대로 줌.
                rotationAngle = (rotationAngle<0)?-maxRotationAngle:maxRotationAngle;
            }
            transformImageBitmap((ImageView) child,t,rotationAngle);
        }

        return true;
    }


    private void transformImageBitmap(ImageView child, Transformation t, int rotationAngle) {
        // 설정값을 저장하겠다.
        camera.save();

        // 매트릭스는 이미지 변환 등에 사용되는 객체임.
        Matrix matrix = t.getMatrix();

        int imageHeight = child.getLayoutParams().height;
        int imageWidth = child.getLayoutParams().width;
        int rotation = Math.abs(rotationAngle);


        // 이동시켜줌. z에 값을 넣어서 앞뒤로 이동하게 해줌.
        camera.translate(0.0f,0.0f,100.0f);

        if(rotation < maxRotationAngle) {
            // rotationAngle 에 따라서 앞으로 나오게 할 것인지를 결정.
            float zoomAmount = (float) (maxZoom+(rotation*1.5));
            camera.translate(0.0f,0.0f,zoomAmount);
        }

        camera.rotateZ(rotationAngle);
        camera.getMatrix(matrix);

        // 일정 위치로 이동시킴.
        matrix.preTranslate(-(imageWidth/2),-(imageHeight/2));
        matrix.postTranslate((imageWidth/2),(imageHeight/2));

        camera.restore();
    }

    /*
    뷰의 크기가 변경될 때 호출됨.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerPoint = (getWidth()-getPaddingLeft()-getPaddingRight())/2+getPaddingLeft();
    }
}

package org.techtown.fragment2;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ViewerFragment extends Fragment {

    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_viewer,container,false);

         imageView = (ImageView)rootView.findViewById(R.id.imageView);


        return rootView;
    }

    public void setImage(int index)
    {
        if(index==0){
            imageView.setImageResource(R.drawable.dream01);
        }
        else if(index==1){
            imageView.setImageResource(R.drawable.dream02);
        }
        else if(index==2){
            imageView.setImageResource(R.drawable.dream03);
        }
    }
}

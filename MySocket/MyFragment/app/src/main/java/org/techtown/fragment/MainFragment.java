package org.techtown.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    MainActivity activity;


    // 프래그먼트가 액티비티 위에 올라옴! => 프래그먼트로서 동작할 수 있음.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // MainActivity 를 참조할 수 있음. => 해당 프래그먼트가 위에서 동작하는 액티비티!
        activity = (MainActivity) getActivity();
    }

    // 프래그먼트가 액티비티에서 내려감!
    @Override
    public void onDetach() {
        super.onDetach();

        activity=null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 최상위 레이아웃이 rootView 에 저장됨.
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main,container,false);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메뉴 프래그먼트가 보이게 됨! => MainActivity 에서 설정했으니까.
                activity.onFragementChange(1);
            }
        });


        return rootView;

    }
}

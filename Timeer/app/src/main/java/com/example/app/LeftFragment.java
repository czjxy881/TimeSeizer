package com.example.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;


public class LeftFragment extends Fragment {
    private Button btnpingjia;
    private RatingBar rbmysocre;
    private static LeftFragment leftFragment=null;



    //单实例化
    public static LeftFragment getInstance(){
        if (leftFragment==null){
            leftFragment=new LeftFragment();
            return leftFragment;
        }else return leftFragment;
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_left, container, false);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser ) {

        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            rbmysocre = (RatingBar)getActivity().findViewById(R.id.rbmyscore);
            btnpingjia = (Button)getActivity().findViewById(R.id.btnpingjia);

            //RatingBar的设置
            rbmysocre.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    Toast.makeText(getActivity(), "你获得星星是"+v, Toast.LENGTH_SHORT).show();
                }
            });

            btnpingjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO::完成完成评价的按钮的设置
                }
            });

        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }

}

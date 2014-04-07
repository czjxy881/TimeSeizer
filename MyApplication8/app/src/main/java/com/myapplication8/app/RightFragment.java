package com.myapplication8.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by xxx on 2014/3/31.
 */
public class RightFragment extends android.support.v4.app.Fragment {
    private RatingBar rbmysocre;
    private static RightFragment rigthFragment=null;
    public static RightFragment getInstance(){
        if (rigthFragment==null){
            rigthFragment=new RightFragment();
            return rigthFragment;
        }else return rigthFragment;
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rbmysocre = (RatingBar)getActivity().findViewById(R.id.rbmyscore);
        rbmysocre.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(getActivity(),"xxx",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

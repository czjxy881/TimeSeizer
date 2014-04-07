package com.myapplication8.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class CenterFragment extends Fragment {
    private static CenterFragment centerFragment=null;
    public static CenterFragment getInstance(){
        if (centerFragment==null){
            centerFragment=new CenterFragment();
            return centerFragment;
        }else return centerFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }



}

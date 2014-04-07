package com.myapplication8.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CenterFragment extends Fragment {
    public CenterFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    public void setTime(String st){
        TextView s=(TextView)getActivity().findViewById(R.id.RemindTime);
        s.setText(st);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }



}

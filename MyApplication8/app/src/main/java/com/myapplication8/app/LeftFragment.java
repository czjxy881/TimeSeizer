package com.myapplication8.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xxx on 2014/4/1.
 */
public class LeftFragment extends android.support.v4.app.Fragment {
    private Button btntask;

    private static LeftFragment leftFragment=null;
    public static LeftFragment getInstance(){
        if (leftFragment==null){
            leftFragment=new LeftFragment();
            return leftFragment;
        }else return leftFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        final Button btntask = (Button) v.findViewById(R.id.btntask);
        //btntask.setFocusable(true);


        btntask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(),ListActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
}
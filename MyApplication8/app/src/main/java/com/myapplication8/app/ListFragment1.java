package com.myapplication8.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ListFragment1 extends Fragment {
    private Button btnadd;

    public ListFragment1(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_fragment1, container, false);
        final Button btnadd = (Button) v.findViewById(R.id.btnadd);
        //btntask.setFocusable(true);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(),TabActivity.class);
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

package com.example.timerseizer;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;



public class RightFragment extends android.support.v4.app.Fragment  {
    public static RightFragment getInstance(){
        if (rightFragment==null){
            rightFragment=new RightFragment();
            return rightFragment;
        }else return rightFragment;
    }
    public RightFragment(){

    }
    private static RightFragment rightFragment=null;

    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar=((MainActivity)this.getActivity()).getMyActionBar();

        // Set up the action bar to show a dropdown list.

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // actionBar=getFragmentManager().
       // actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.

        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        new String[] {
                                getString(R.string.title_section1),
                                getString(R.string.title_section2),
                                getString(R.string.title_section3),
                                getString(R.string.title_section4),
                        }),
                (ActionBar.OnNavigationListener) getActivity());

        return inflater.inflate(R.layout.fragment_right, container, false);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

            ((MainActivity)this.getActivity()).changeActionBarButton(actionBar.getSelectedNavigationIndex());
            TaskListControllor.update();
        }
    }

}


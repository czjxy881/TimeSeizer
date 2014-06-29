package com.example.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.Note.Date;
import com.example.app.Note.DrawLine;
import com.example.app.Note.Notepad;
import com.example.app.Note.NotepadAdapter;

import java.util.Vector;

/**
 * Created by xxx on 14-6-27.
 */
public class TFragment extends Fragment  {
    private int id;
    private View mMainView;
    public NotepadAdapter adapter;
    Vector<String> presidents = new Vector<String>();
    public ListView listView;
    private EditText etworker;
    private static TFragment tFragment=null;

    TableLayout layout ;
    TextView textView ;
    EditText editText ;
    EditText beizhutext;
    Date getDate ;
    String date ;


    public static TFragment getInstance(){
        if (tFragment==null){
            tFragment=new TFragment();
            return tFragment;
        }else return tFragment;
    }
public TFragment(){
    presidents.add("123");
    presidents.add("222223");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.listfragment_one, (ViewGroup)getActivity().findViewById(R.id.main_viewpager), false);
        this.editText=((EditText)mMainView.findViewById(R.id.edittext));
       
        this.listView = ((ListView) mMainView.findViewById(R.id.listview));
        this.adapter = new NotepadAdapter(getActivity(),tFragment,presidents);
        this.listView.setAdapter(this.adapter);



        this.listView.setDivider(null);
        this.listView.setOnItemClickListener(new ItemClick());


    }

    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent();
            intent.setClass(getActivity(),CenterActivity.class);
            intent.putExtra("Name", presidents.get(i));
            startActivity(intent);

        }
    }
    Menu mMenu;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        mMenu=menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.mymain, menu);
        MenuItemCompat.setShowAsAction(menu.getItem(2), MenuItemCompat.SHOW_AS_ACTION_ALWAYS | MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        MenuItemCompat.setShowAsAction(menu.getItem(1), MenuItemCompat.SHOW_AS_ACTION_ALWAYS|MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS|MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return mMainView;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    public void showUpdate() {
        this.adapter = new NotepadAdapter(getActivity(),tFragment,presidents);
        this.listView.setAdapter(this.adapter);
        this.listView.setDivider(null);
    }

    public void TaskAdd(){
        layout = (TableLayout)(getActivity().getLayoutInflater().inflate(R.layout.alertdialog_freetimework, null));
        textView = ((TextView)layout.findViewById(R.id.tvworker));
        //editText = ((DrawLine)layout.findViewById(R.id.edittext));
        etworker =((EditText)layout.findViewById(R.id.etworker));
        getDate = new Date();
        date = getDate.getDate();
        //textView.setText(date);
        new AlertDialog.Builder(getActivity()).setView(layout).setPositiveButton("保存", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Name = etworker.getText().toString();
                if (Name.equals("")) {
                    Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
                    presidents.add("请输入内容");
                }
                else {
                    presidents.add(Name);
                }
                showUpdate();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        }).show();


    }

}

package com.example.app;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ListFragment;

import com.example.app.Note.Date;
import com.example.app.Note.Notepad;
import com.example.app.Note.NotepadAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by xxx on 14-4-25.
 */
public class ListFragment1 extends ListFragment {
    public ArrayList<Map<String, Object>> itemList;
    private Button numberButton;
    private View mMainView;
    RelativeLayout layout ;
    TextView textView ;
    EditText editText ;
    Date getDate ;
    String date ;
    private Button btnadd;
    public NotepadAdapter adapter;
    private Button btnfreeworker;
    private EditText etworker;
    private EditText etfanqie;
    private EditText etbeizhu;
    private Button start1;
    private int number;
    public ListView listView;
    Vector<String> presidents = new Vector<String>();
    HashMap<String, Object> localHashMap = new HashMap<String, Object>();



    public static ListFragment1 listFragment1 =null;
    public static ListFragment1 getInstance(){
        if (listFragment1 ==null){
            listFragment1 =new ListFragment1();
            return listFragment1;
        }else return listFragment1;
    }
    public ListFragment1() {
        presidents.add("123");
        presidents.add("2312");
        //Vector<TodayTask> TodayLi = InnerforUI.getInstance(MainActivity.activity).showTodayList();
        //for(TodayTask s:TodayLi) {
        //presidents.add(s.getName());

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.listfragment_one, container, false);


        return v;
    }
 /*
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
      //this.itemList = new ArrayList<Map<String, Object>>();

        super.onActivityCreated(savedInstanceState);
       // this.adapter = new NotepadAdapter(getActivity(),this.presidents);
        //his.listView.setAdapter(this.adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //btnfreeworker = (Button) getActivity().findViewById(R.id.btnfreeworker);

        btnfreeworker.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        builder.setTitle("添加任务");
                        final TableLayout freework = (TableLayout) getLayoutInflater(null).inflate(R.layout.alertdialog_freetimework, null);
                        builder.setView(freework);
                        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //点击添加按钮的事件
                                EditText etworker = (EditText)freework.findViewById(R.id.etworker);
                                EditText etfanqie = (EditText)freework.findViewById(R.id.etfanqie);
                                EditText etbeizhu = (EditText)freework.findViewById(R.id.etbeizhu);
                                String a = etworker.getText().toString();
                                String b = etfanqie.getText().toString();
                                String c = etbeizhu.getText().toString();
                                Toast.makeText(getActivity(), a + "+" + b + "+" + c, Toast.LENGTH_SHORT).show();


                            }
                        });
                        builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //取消
                            }
                        });
                        builder.create().show();
                    }


                }
        );


    }



    public void onCreate(Bundle savedInstanceState) {
        //this.itemList = new ArrayList<Map<String, Object>>();
        //this.adapter = new NotepadAdapter(getActivity(),this.itemList);
        super.onCreate(savedInstanceState);
        //this.listView.setAdapter(this.adapter);
    // setListAdapter(new ArrayAdapter<String>(getActivity(),
    //android.R.layout.simple_list_item_1, presidents));
}

    public void onListItemClick(ListView parent, View v,
                                int position, long id)
    {
        Toast.makeText(getActivity(),
                "selected " + presidents.get(position),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        btnadd = (Button) getActivity().findViewById(R.id.btnadd);
        start1=(Button)getActivity().findViewById(R.id.start1);

        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(getActivity(),CenterActivity.class);
                startActivity(intent1);
                //getActivity().finish();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(),TabActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


*/

}


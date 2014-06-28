package com.example.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
    private Button btnadd;
    private Button numberButton;
    private View mMainView;
    public NotepadAdapter adapter;
    Vector<String> presidents = new Vector<String>();
    public ListView listView;
    private static TFragment tFragment=null;

    RelativeLayout layout ;
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
        this.beizhutext=((EditText)mMainView.findViewById(R.id.beizhutext));
        this.numberButton = ((Button) mMainView.findViewById(R.id.numberbtn));
        this.listView = ((ListView) mMainView.findViewById(R.id.listview));
        this.adapter = new NotepadAdapter(getActivity(),tFragment,presidents);
        this.listView.setAdapter(this.adapter);
        numberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout = (RelativeLayout)(getActivity().getLayoutInflater().inflate(R.layout.writedown, null));
                textView = ((TextView)layout.findViewById(R.id.writedate));
                editText = ((DrawLine)layout.findViewById(R.id.edittext));
                getDate = new Date();
                date = getDate.getDate();
                textView.setText(date);

                new AlertDialog.Builder(getActivity()).setView(layout).setPositiveButton("保存", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Notepad localNotepad = new Notepad();
                        String Name = editText.getText().toString();
                        if (Name.equals("")) {
                            Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
                            presidents.add("请输入内容");
                        }
                        //localNotepad.setContent(strContent);
                        //localNotepad.setdata(date);
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
        });


        this.listView.setDivider(null);
        this.listView.setOnItemClickListener(new ItemClick());


    }

    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(),CenterActivity.class);
            intent.putExtra("Name", presidents.get(i));
            startActivity(intent);

        }
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
        btnadd=(Button)getActivity().findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TabActivity.class);
                startActivity(intent);

            }
        });
    }

    public void showUpdate() {
        this.adapter = new NotepadAdapter(getActivity(),tFragment,presidents);
        this.listView.setAdapter(this.adapter);
    }



}

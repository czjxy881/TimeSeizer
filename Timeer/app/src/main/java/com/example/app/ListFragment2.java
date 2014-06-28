package com.example.app;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
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

import com.example.app.Note.Date;
import com.example.app.Note.DrawLine;
import com.example.app.Note.Notepad;
import com.example.app.Note.NotepadAdapter;
import com.example.app.Note.NotepadAdapter2;

import java.util.Vector;

public class ListFragment2 extends Fragment {
    private Button numberButton2;
    private View mMainView;
    public NotepadAdapter2 adapter2;
    Vector<String> presidents = new Vector<String>();
    public ListView listView2;
    RelativeLayout layout ;
    TextView textView ;
    EditText editText ;
    Date getDate ;
    String date ;

    private static ListFragment2 listFragment2 = null;
    public static ListFragment2 getInstance(){
        if (listFragment2==null){
            listFragment2=new ListFragment2();
            return listFragment2;
        }else return listFragment2;
    }

    public ListFragment2(){
        presidents.add("123");
        presidents.add("222223");
    }



     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
         return mMainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());




    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.listfragment_two, (ViewGroup)getActivity().findViewById(R.id.main_viewpager), false);

        this.numberButton2 = ((Button) mMainView.findViewById(R.id.numberbtn2));
        this.listView2 = ((ListView) mMainView.findViewById(R.id.listview2));
        this.adapter2 = new NotepadAdapter2(getActivity(),listFragment2,presidents);
        this.listView2.setAdapter(this.adapter2);
        numberButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();
                   showUpdate();
            }
        });
        this.listView2.setDivider(null);
        this.listView2.setOnItemClickListener(new ItemClick());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
       getActivity().setContentView(R.layout.listfragment_two);
    }
    public void showUpdate() {
        this.adapter2 = new NotepadAdapter2(getActivity(),listFragment2,presidents);
        this.listView2.setAdapter(this.adapter2);

    }
    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Toast.makeText(getActivity(),"还剩xx天会执行"+presidents.get(i)+"任务",Toast.LENGTH_SHORT).show();

        }
    }
}

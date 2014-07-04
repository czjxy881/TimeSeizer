package com.example.timerseizer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timerseizer.sql.InnerforUI;
import com.example.timerseizer.sql.PeroidTask;
import com.example.timerseizer.sql.Task;
import com.example.timerseizer.sql.TodayTask;

import java.util.Calendar;
import java.util.Formatter;

/**
 * Created by jxy on 14-7-4.
 */
public class EditDialog  extends Dialog {
    TextView TitleView,ContentView,TomatoView,DateView;
    RadioGroup radioGroup;
    LinearLayout linearLayout;
    public EditDialog(Context context,final TaskListControllor.ListKind listKind) {
        super(context, R.style.mydialog);
        setContentView(R.layout.alertdialog_freetimework);
        TitleView = ((TextView) findViewById(R.id.TitleAddText));
        ContentView = ((TextView) findViewById(R.id.ContentAddText));
        TomatoView = ((TextView) findViewById(R.id.TomatoAddText));
        DateView = ((TextView) findViewById(R.id.DateTextDialog));
        DateView.setText(android.text.format.DateFormat.format("yyyy-MM-dd", Calendar.getInstance().getTime()));
        TitleView.clearComposingText();
        ContentView.clearComposingText();
        TomatoView.clearComposingText();

        radioGroup = (RadioGroup) findViewById(R.id.DialogRadioGroup);
        linearLayout = (LinearLayout) findViewById(R.id.DateLinearLayout);
        if (listKind == TaskListControllor.ListKind.AllList) {
            radioGroup.setVisibility(View.VISIBLE);
        } else {
            radioGroup.setVisibility(View.GONE);
            radioGroup.clearCheck();
        }
        if (listKind == TaskListControllor.ListKind.TodayList) {
            linearLayout.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
        }
        DateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dateDialog = new Dialog(EditDialog.this.getContext(), R.style.mydialog);
                dateDialog.setContentView(R.layout.date_dialog);
                final DatePicker Datepicker = ((DatePicker) dateDialog.findViewById(R.id.Datepicker));
                dateDialog.findViewById(R.id.DateDialogSave).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Formatter formatter = new Formatter();
                        formatter.format("%04d-%02d-%02d", Datepicker.getYear(), Datepicker.getMonth() + 1, Datepicker.getDayOfMonth());
                        DateView.setText(formatter.toString());
                        dateDialog.cancel();
                    }
                });
                dateDialog.findViewById(R.id.DateDialogCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dateDialog.cancel();
                    }
                });
                dateDialog.show();
            }
        });
        findViewById(R.id.DialogSaveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:数据库更新
                String Name = TitleView.getText().toString();
                String NumS=TomatoView.getText().toString();
                if(Name.equals("")||NumS.equals("")){
                    //TODO:可以设成红色
                    Toast.makeText(EditDialog.this.getContext(), "名称和期望数目不能为空", Toast.LENGTH_SHORT).show();
                    //dialog.cancel();
                }else {
                    int Num = Integer.valueOf(NumS);
                    String Note = ContentView.getText().toString();
                    String EDate = DateView.getText().toString();
                    Task task = new Task();
                    task.set(Name, -1, Num, EDate, Note, -1, -1);
                    TaskListFragment listFragment;
                    if (listKind == TaskListControllor.ListKind.TodayList || radioGroup.getCheckedRadioButtonId() == R.id.DialogRadioOnce) {
                        listFragment = TaskListControllor.getInstance(TaskListControllor.ListKind.TodayList);
                        listFragment.add(task);
                        listFragment.showUpdate();
                    } else {
                        listFragment = TaskListControllor.getInstance(TaskListControllor.ListKind.PeriodList);
                        listFragment.add(task);
                        listFragment.showUpdate();
                    }
                    listFragment = TaskListControllor.getInstance(TaskListControllor.ListKind.AllList);
                    // listFragment.add(Name);

                    listFragment.showUpdate();
                    cancel();
                }
            }
        });
        findViewById(R.id.DialogCancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }
    public EditDialog(Context context, TaskListControllor.ListKind listKind,int RunnerID) {
        this(context, listKind);
        final TodayTask now= InnerforUI.getInstance().findTodayTaskByRunnerID(RunnerID);
        TomatoView.setText(""+now.getExpectNum());
        TitleView.setText(now.getName());
        ContentView.setText(now.getNoteString());
        DateView.setText(now.getExpectDate());
        if(InnerforUI.getInstance().findPeroidTaskByID(now.getID()).size()!=0) {
            radioGroup.check(R.id.DialogRadioPeriod);
            listKind= TaskListControllor.ListKind.PeriodList;
        }else{
            radioGroup.check(R.id.DialogRadioOnce);
            listKind= TaskListControllor.ListKind.TodayList;
        }
        radioGroup.setEnabled(false);
        findViewById(R.id.DialogRadioPeriod).setEnabled(false);
        findViewById(R.id.DialogRadioOnce).setEnabled(false);

        final TaskListControllor.ListKind kind=listKind;
        findViewById(R.id.DialogSaveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = TitleView.getText().toString();
                String NumS=TomatoView.getText().toString();
                if(Name.equals("")||NumS.equals("")){
                    //TODO:可以设成红色
                    Toast.makeText(EditDialog.this.getContext(), "名称和期望数目不能为空", Toast.LENGTH_SHORT).show();
                    //dialog.cancel();
                }else {
                    int Num = Integer.valueOf(NumS);
                    String Note = ContentView.getText().toString();
                    String EDate = DateView.getText().toString();
                    now.set(Name, now.getID(), Num, EDate, Note,now.getRunnerID(), now.getPriority());
                    now.save();
                    TaskListControllor.update();
                    cancel();
                }
            }
        });

    }
}










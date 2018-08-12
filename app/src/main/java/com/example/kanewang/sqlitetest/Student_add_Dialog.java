package com.example.kanewang.sqlitetest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student_add_Dialog extends DialogFragment {
        public static  final String EXTRA_Student =
                "com.example.kanewang.sqlitetest.student";
        private OnNewInforCompleted mOnNewInforCompleted;
        private static final String DIALOG_DATE = "DialogDate";
        private static final  int REQUEST_DATE = 0;
        private EditText studentName;
        private RadioGroup radioGroup;
        private RadioButton radioButton;
        private EditText studentAge;
        private  Button DateButton;

        @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        @SuppressLint("InflateParams") final View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_student_dialog,null);


            DateButton = view.findViewById(R.id.student_BirthDate);
            DateButton.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            DateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = getFragmentManager();
                    java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String s = DateButton.getText().toString();
                    Date date = null;
                    try {
                        date = formatter.parse(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    DatePickerFragment dialog = DatePickerFragment.newInstance(date);
                    dialog.setTargetFragment(Student_add_Dialog.this,REQUEST_DATE);
                    dialog.show(manager,DIALOG_DATE);
                }
            });
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("请输入新学生信息")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         studentName = view.findViewById(R.id.student_Name);
                         radioGroup = (RadioGroup)view.findViewById(R.id.sex_RadioGroup);
                         radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                         studentAge = view.findViewById(R.id.student_Age);


                        Student student = new Student();
                        student.setName(studentName.getText().toString());
                        student.setSex(radioButton.getText().toString());
                        student.setage(studentAge.getText().toString());
                        student.setBirthDate(DateButton.getText().toString());
                        mOnNewInforCompleted.onCompleteClick(student);



                    }
                })
                .create();

    }

        public interface OnNewInforCompleted {
            void onCompleteClick(Student student);
        }
        public void setOnDialogListener(OnNewInforCompleted NewInforCompleted){
            this.mOnNewInforCompleted = NewInforCompleted;
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode ==REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            DateButton.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
        }
    }
}

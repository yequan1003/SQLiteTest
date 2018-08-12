package com.example.kanewang.sqlitetest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private StudentAdapter mStudentAdapter;
    private static final String Student_Add_DIALOG= "NewStudentDialog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // StudentsLab studentsLab = StudentsLab.getStudentsLab(getApplicationContext());

        mRecyclerView = findViewById(R.id.student_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        updateUI();
        Button add_button = findViewById(R.id.button_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                Student_add_Dialog studentAddDialog = new Student_add_Dialog();
                studentAddDialog.setOnDialogListener(new Student_add_Dialog.OnNewInforCompleted() {
                    @Override
                    public void onCompleteClick(Student student) {
                        StudentsLab studentsLab = StudentsLab.getStudentsLab(getApplicationContext());
                        studentsLab.addStudent(student);
                        updateUI();
                    }
                });
                studentAddDialog.show(fragmentManager, Student_Add_DIALOG);

            }
        });


    }

    private void updateUI(){
        StudentsLab studentsLab = StudentsLab.getStudentsLab(this);
        List<Student> students = studentsLab.getStudents();

        if (mStudentAdapter == null) {
            mStudentAdapter = new StudentAdapter(students);
            mRecyclerView.setAdapter(mStudentAdapter);
        }else{
            mStudentAdapter.setStudents(students);
            mStudentAdapter.notifyDataSetChanged();
            mRecyclerView.getLayoutManager().scrollToPosition(students.size() -1);
        }
    }


    private class StudentHolder extends RecyclerView.ViewHolder{

        private  Student mStudent;
        private TextView mStudentNameTextView;
        private TextView mStudentSexTextView;
        private TextView mStudentAgeTextView;
        private TextView mStudentBirthDateTextView;
        public StudentHolder(View itemView) {
            super(itemView);
            mStudentNameTextView = itemView.findViewById(R.id.student_Name_TextView);
            mStudentSexTextView = itemView.findViewById(R.id.Student_sex_TextView);
            mStudentAgeTextView = itemView.findViewById(R.id.student_Age_TextView);
            mStudentBirthDateTextView = itemView.findViewById(R.id.student_BirthDate_TextView);
        }

        public void bindStudent(Student student){
            mStudent = student;
            mStudentNameTextView.setText(mStudent.getName());
            mStudentSexTextView.setText(mStudent.getSex());
            mStudentAgeTextView.setText(mStudent.getage());
            mStudentBirthDateTextView.setText(mStudent.getBirthDate());
        }

    }

    private class StudentAdapter extends RecyclerView.Adapter<StudentHolder>{

        private List<Student> mStudents;


        public StudentAdapter(List<Student> students) {
            mStudents = students;
        }

        @Override
        public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.list_item_student,parent,false);
            return new StudentHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(StudentHolder holder, int position) {

            Student student = mStudents.get(position);
            holder.bindStudent(student);
        }

        @Override
        public int getItemCount() {
            return mStudents.size();
        }

        public void setStudents(List<Student> students){
            mStudents = students;
        }
    }
}

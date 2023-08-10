package com.example.gradingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterGradesFragment extends Fragment {
    View v;
    EditText edtName, edtProgram, edtCourse1, edtCourse2, edtCourse3, edtCourse4;
    Button btnSubmit;
    DBHelper dbh; //Instance of the Database helper class 'DBH'
    Boolean insertStatus;

    //Constructor
    public EnterGradesFragment(){}

    // Create Grade Obj composed by the user's input
    public Grade CreateGrade()
    {
        Grade objGrd1 = new Grade();
        int grdId = 0;
        objGrd1.setStudentid(grdId);
        objGrd1.setStudentname(edtName.getText().toString().trim());
        objGrd1.setProgram(edtProgram.getText().toString().trim());
        objGrd1.setCourse1(Integer.parseInt(edtCourse1.getText().toString().trim()));
        objGrd1.setCourse2(Integer.parseInt(edtCourse2.getText().toString().trim()));
        objGrd1.setCourse3(Integer.parseInt(edtCourse3.getText().toString().trim()));
        objGrd1.setCourse4(Integer.parseInt(edtCourse4.getText().toString().trim()));

        return objGrd1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_enter_grades, container, false);
        // Receive User's inputs
        edtName = v.findViewById(R.id.edtName);
        edtProgram = v.findViewById(R.id.edtProg);
        edtCourse1 = v.findViewById(R.id.edtCourse1);
        edtCourse2 = v.findViewById(R.id.edtCourse2);
        edtCourse3 = v.findViewById(R.id.edtCourse3);
        edtCourse4 = v.findViewById(R.id.edtCourse4);
        btnSubmit = v.findViewById(R.id.btnSubmit);
        // Instantiate DBHelper
        dbh = new DBHelper(getActivity());

        // Show the result of click event ( Insert into Grade DB)
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Grade objGrd = CreateGrade();
                insertStatus = dbh.InsertGrade(objGrd);
                if(insertStatus)
                {
                    Toast.makeText(getActivity(),"Record added successfully",Toast.LENGTH_LONG ).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Record insertion successfully",Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }
}
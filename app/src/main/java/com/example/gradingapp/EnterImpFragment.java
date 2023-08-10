package com.example.gradingapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class EnterImpFragment extends Fragment {
    View v;
    EditText edtId, edtMark;
    Spinner spnCourse;
    Button btnSubmit;
    DBHelper dbh; //Instance of the Database helper class 'DBH'
    String courseOption;
    int stdId, mark, prevMark;
    Boolean insertStatus, updateStatus;

    public EnterImpFragment() {
        // Required empty public constructor
        courseOption = "course1";
    }

    // Create Improvement Obj composed by the user's input
    public Improvement CreateImp()
    {
        Improvement objImp1 = new Improvement();
        int impId = 0;
        objImp1.setImprovementid(impId);
        objImp1.setStudentid(stdId);
        objImp1.setCourse(courseOption);
        objImp1.setMarks(mark + prevMark);
        return objImp1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_enter_imp, container, false);
        edtId = v.findViewById(R.id.edtStdId);
        edtMark = v.findViewById(R.id.edtMark);
        spnCourse = v.findViewById(R.id.spnCourse);
        btnSubmit = v.findViewById(R.id.btnSubmit);


        dbh = new DBHelper(getActivity());

        ArrayAdapter<CharSequence> adapterCourse = ArrayAdapter.createFromResource(getContext(),
                R.array.course_array, android.R.layout.simple_spinner_item);

        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCourse.setAdapter(adapterCourse);

        spnCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseOption = spnCourse.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Show the result of click event ( Insert into Improvement DB)
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stdId = Integer.parseInt(edtId.getText().toString().trim());
                mark = Integer.parseInt(edtMark.getText().toString().trim());
                Cursor cursor1 =  dbh.FetchStudentById(stdId);
                prevMark = 0;

                // Compare selected course option and get current mark
                if(cursor1 != null)
                {
                    cursor1.moveToFirst();
                    if(courseOption.equals("course1")) {
                        prevMark = cursor1.getInt(3);
                    }else if(courseOption.equals("course2")) {
                        prevMark = cursor1.getInt(4);
                    }else if(courseOption.equals("course3")) {
                        prevMark = cursor1.getInt(5);
                    }else if(courseOption.equals("course4")) {
                        prevMark = cursor1.getInt(6);
                    }
                }
                //The new mark of the course after adding improvement mark should not exceed 100.
                if((prevMark + mark) > 100){
                    Toast.makeText(getActivity(),"Mark cannot exceed 100",Toast.LENGTH_LONG).show();
                }else{
                    Improvement objImp = CreateImp();
                    insertStatus = dbh.InsertImprovement(objImp);
                    if(insertStatus )
                        if(insertStatus)
                        {
                            Toast.makeText(getActivity(), "Record added successfully", Toast.LENGTH_LONG).show();
                            updateStatus = dbh.UpdateGrade(objImp);
                            if(updateStatus)
                            {
                                Toast.makeText(getActivity(), "Record updated successfully", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Record update fail", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Record insertion fail", Toast.LENGTH_LONG).show();
                        }
                }
            }
        });
        return v;
    }
}
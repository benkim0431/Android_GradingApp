package com.example.gradingapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SearchGradeFragment extends Fragment {

    View v;
    TextView txtName, txtProgram, txtCourse1, txtCourse2, txtCourse3, txtCourse4, txtTotal;
    EditText edtId;
    Button btnSearch;
    DBHelper dbh;
    int stdId;

    public SearchGradeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search_grade, container, false);
        // Assign widget to variable
        edtId = v.findViewById(R.id.edtStdId);
        txtName = v.findViewById(R.id.txtName);
        txtProgram = v.findViewById(R.id.txtProgram);
        txtCourse1 = v.findViewById(R.id.txtCourse1);
        txtCourse2 = v.findViewById(R.id.txtCourse2);
        txtCourse3 = v.findViewById(R.id.txtCourse3);
        txtCourse4 = v.findViewById(R.id.txtCourse4);
        txtTotal = v.findViewById(R.id.txtTotal);
        btnSearch = v.findViewById(R.id.btnSearch);

        dbh = new DBHelper(getActivity());

        // Show the result of click event ( Fetch from Grade DB)
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stdId = Integer.parseInt(edtId.getText().toString().trim());
                Cursor cursor1 =  dbh.FetchStudentById(stdId);
                if(cursor1 != null)
                {
                    // Show the data on Fragment
                    cursor1.moveToFirst();
                    txtName.setText(cursor1.getString(1));
                    txtProgram.setText(cursor1.getString(2));
                    txtCourse1.setText(String.valueOf(cursor1.getInt(3)));
                    txtCourse2.setText(String.valueOf(cursor1.getInt(4)));
                    txtCourse3.setText(String.valueOf(cursor1.getInt(5)));
                    txtCourse4.setText(String.valueOf(cursor1.getInt(6)));
                    // Show Total marks of all course
                    txtTotal.setText(String.valueOf(cursor1.getInt(3) + cursor1.getInt(4)+cursor1.getInt(5)+cursor1.getInt(6)));
                }else{
                    //If cannot find result, show message
                    Toast.makeText(getActivity(), "Cannot find the result for input Id", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}
package com.example.gradingapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ListStudentFragment extends Fragment {

    View v;
    RecyclerView rcView;
    List<Grade> mList = new ArrayList<>();
    DBHelper dbh;
    ListAdapter mAdapter;
    public ListStudentFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_student, container, false);
        rcView = (RecyclerView) v.findViewById(R.id.rcView);
        dbh = new DBHelper(getActivity());

        Cursor cursor1 = dbh.ListStudent();
        if(cursor1 == null)
        {
            Toast.makeText(getActivity(),"No student records found",Toast.LENGTH_LONG).show();
            return v;
        }
        else
        {
            // Fetch data from cursor and set at student object
            // Move cursor back to first
            cursor1.moveToFirst();
            do{
                Grade grdObj = new Grade();
                grdObj.setStudentid(cursor1.getInt(0));
                grdObj.setStudentname(cursor1.getString(1));
                grdObj.setProgram(cursor1.getString(2));
                grdObj.setCourse1(cursor1.getInt(3));
                grdObj.setCourse2(cursor1.getInt(4));
                grdObj.setCourse3(cursor1.getInt(5));
                grdObj.setCourse4(cursor1.getInt(6));

                mList.add(grdObj);
            }while (cursor1.moveToNext());
            cursor1.close();
            dbh.close();
            BindAdapter();
            return v;
        }
    }

    // controller (MVC Pattern) of recycler view
    private void BindAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcView.setLayoutManager(layoutManager);
        //Calls the constructor of ListAdapter and passes the list consisting of all student records
        mAdapter = new ListAdapter(mList,getContext());
        rcView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
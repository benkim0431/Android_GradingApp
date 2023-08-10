package com.example.gradingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Grade> mList;

    // To show the list of students using recycler view, use this adapter
    // Controller (MVC) for Recyclerview
    public ListAdapter(List<Grade> listGrd, Context context)
    {
        super();
        mList=listGrd;
    }

    // Each row data of recyclerview
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout object from xml layout file
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(v);
        return viewHolder1;
    }

    // Bind holder and data
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Grade grdObj = mList.get(position);
        ((ViewHolder) holder).mId.setText(String.valueOf(grdObj.getStudentid()));
        ((ViewHolder) holder).mName.setText(grdObj.getStudentname());
        ((ViewHolder) holder).mProgram.setText(grdObj.getProgram());
        ((ViewHolder) holder).mCourse1.setText(String.valueOf(grdObj.getCourse1()));
        ((ViewHolder) holder).mCourse2.setText(String.valueOf(grdObj.getCourse2()));
        ((ViewHolder) holder).mCourse3.setText(String.valueOf(grdObj.getCourse3()));
        ((ViewHolder) holder).mCourse4.setText(String.valueOf(grdObj.getCourse4()));
        ((ViewHolder) holder).mTotal.setText(String.valueOf(grdObj.getCourse1()+grdObj.getCourse2()+grdObj.getCourse3()+grdObj.getCourse4()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mId, mName, mProgram, mCourse1, mCourse2, mCourse3, mCourse4, mTotal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mId = (TextView) itemView.findViewById(R.id.txtId);
            mName = (TextView) itemView.findViewById(R.id.txtName);
            mProgram = (TextView) itemView.findViewById(R.id.txtProgram);
            mCourse1 = (TextView) itemView.findViewById(R.id.txtCourse1);
            mCourse2 = (TextView) itemView.findViewById(R.id.txtCourse2);
            mCourse3 = (TextView) itemView.findViewById(R.id.txtCourse3);
            mCourse4 = (TextView) itemView.findViewById(R.id.txtCourse4);
            mTotal = (TextView) itemView.findViewById(R.id.txtTotal);
        }
    }
}

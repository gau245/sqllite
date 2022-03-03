package com.example.sqlitedatabasedemo;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DataHolder> {
    MainActivity activity;
    List<ModalData> dataList;
    LayoutInflater inflater;

    public MyAdapter(MainActivity mainActivity, List<ModalData> dataList) {
        activity = mainActivity;
        this.dataList = dataList;
        inflater = LayoutInflater.from(activity);

    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        final ModalData modalData = dataList.get(position);
        holder.txtid.setText(dataList.get(position).getId());
        holder.txtname.setText(dataList.get(position).getName());
        holder.txtsurname.setText(dataList.get(position).getSurname());
        holder.txtcource.setText(dataList.get(position).getCource());

        MyDatabase myDatabase = new MyDatabase(activity);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Confirmation!!!")
                        .setMessage("Are sure, you want to Delete Data?")
                        .setCancelable(false)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int result = myDatabase.deleteData(modalData.getId());
                                if (result > 0) {
                                    Toast.makeText(activity, "Deleted Data", Toast.LENGTH_SHORT).show();
                                    dataList.remove(modalData);
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(activity, "Not Deleted", Toast.LENGTH_SHORT).show();

                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog exitDialog = builder.create();
                exitDialog.show();
            }
        });


        public void onClick(View v) {
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
                final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                View mView = LayoutInflater.from(activity).inflate(R.layout.dialog_item, null);
                final EditText ename = (EditText) mView.findViewById(R.id.dialogname);
                final EditText esurname = (EditText) mView.findViewById(R.id.dialogsurname);
                final EditText ecource = (EditText) mView.findViewById(R.id.dialogcourse);

                Button cancel = (Button) mView.findViewById(R.id.dcancel);
                Button diupdate = (Button) mView.findViewById(R.id.dupdate);
                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();
                    }
                });
                diupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String setname = ename.getText().toString();
                        String setsurname = esurname.getText().toString();
                        String setcource = ecource.getText().toString();



                        if (setname.length() != 0 || setsurname.length() != 0 || setcource.length() != 0) {
                            int result = myDatabase.updateData(activity, setname, setsurname, setcource, dataList.get(position).getId());
                            notifyDataSetChanged();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(activity, "Enter the Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtname, txtsurname, txtcource;
        Button update, delete;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.tid);
            txtname = itemView.findViewById(R.id.tname);
            txtsurname = itemView.findViewById(R.id.tsurname);
            txtcource = itemView.findViewById(R.id.tcource);
            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}

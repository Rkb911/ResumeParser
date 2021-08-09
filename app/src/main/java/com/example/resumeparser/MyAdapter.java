package com.example.resumeparser;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Models data[];

    Integer id;

    private static final String urlParse = "https://resume-parserapp.herokuapp.com/api/resume/parse/" ;

    public MyAdapter(Models[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return  new MyViewHolder(view);
        
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.fileName.setText(data[position].getResume());
        holder.isParsed.setText(data[position].getIs_parsed());




        holder.buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = data[holder.getAdapterPosition()].getId();
                Log.i("proper?", id.toString());

                Toast.makeText(v.getContext(), urlParse + id, Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fileName;
        TextView isParsed;
        Button buttonParse;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fileName = itemView.findViewById(R.id.fileName);
            isParsed = itemView.findViewById(R.id.isParsed);
            buttonParse = itemView.findViewById(R.id.buttonParse);



        }
    }
}

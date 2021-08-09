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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Models data[];

    Integer id;

    private static final String urlParse = "https://resume-parserapp.herokuapp.com/api/resume-parse/" ;

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

        if (data[position].getIs_parsed() == "true"){
            holder.isParsed.setText("Parsed");

        }else {
            holder.isParsed.setText("Not Parsed");

        }



        holder.buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = data[holder.getAdapterPosition()].getId();

                String PUT_URL = urlParse + id + "/";

                StringRequest putRequest = new StringRequest(Request.Method.PUT, PUT_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(v.getContext(), "Hello God", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(v.getContext(), "Hello Bot", Toast.LENGTH_SHORT).show();

                    }
                });



                RequestQueue queue = Volley.newRequestQueue(v.getContext());
                queue.add(putRequest);

                Log.i("proper?", id.toString());

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

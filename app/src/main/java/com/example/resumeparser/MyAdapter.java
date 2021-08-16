package com.example.resumeparser;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


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
        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteFileDialog(v);
                return false;
            }
        });
        return  new MyViewHolder(view);

        
    }


    private void deleteFileDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        //alertDialog.setTitle("Delete File");
        alertDialog.setMessage("Do you want to Delete this file?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(view.getContext(), "this file will be deleted", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.fileName.setText(data[position].getResume().substring(13));

        if (data[position].getIs_parsed() == "true"){
            holder.isParsed.setText("Parsed");

        }else {
            holder.isParsed.setText("Not Parsed");

        }



        holder.buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = data[holder.getAdapterPosition()].getId();

                RequestQueue queue = Volley.newRequestQueue(v.getContext());

                String PUT_URL = urlParse + id + "/";

                //This solves the Parsed thing
                JSONObject obj = new JSONObject();

                try {
                    obj.put("is_parsed",true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, PUT_URL,obj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(), "Hello God", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });




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

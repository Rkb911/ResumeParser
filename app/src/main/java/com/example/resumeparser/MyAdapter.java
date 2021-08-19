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
    private static final String urlDelete = "https://resume-parserapp.herokuapp.com/api/resume-delete/" ;


    public MyAdapter(Models[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        
        return  new MyViewHolder(view);

        
    }

    private void deleteFile(int id,View view){

        String DELETE_URL = urlDelete + id + "/";

        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, DELETE_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(deleteRequest);
    }

    private void deleteFileDialog(View view, int fileId ) {
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        //alertDialog.setTitle("Delete File");
        alertDialog.setMessage("Do you want to Delete this file?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteFile(fileId,view);
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




    //Confirmation For Parsing Already Parsed File
    private void confirmParseFile(View view, RequestQueue queue, String PUT_URL) {
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        alertDialog.setMessage("This File is already Parsed.\n\nDo you want to Parse it again ? ");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                parseFile(view,queue,PUT_URL);
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

    private void parseFile (View v, RequestQueue queue, String PUT_URL) {

        JSONObject obj = new JSONObject();

        try {
            obj.put("is_parsed", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, PUT_URL, obj, new Response.Listener<JSONObject>() {
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
                if(data[holder.getAdapterPosition()].getIs_parsed() == String.valueOf(false))
                {
                    parseFile(v,
                            queue,
                            PUT_URL);

                }
                else
                {

                    confirmParseFile(v,queue,PUT_URL);

                }







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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer fileID = data[getAdapterPosition()].getId();
                    Toast.makeText(itemView.getContext(), String.valueOf(fileID), Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Integer fileID = data[getAdapterPosition()].getId();
                    deleteFileDialog(v,fileID);
                    return false;
                }
            });





        }
    }
}

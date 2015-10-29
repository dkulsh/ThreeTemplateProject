package com.deep.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Deep on 10/21/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<JSONObject> dataSet;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewDescription;
        TextView textViewCost;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_2);
            textViewDescription = (TextView) itemView.findViewById(R.id.item_description);
            textViewCost = (TextView) itemView.findViewById(R.id.item_cost);
        }
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_two_detail, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int i) {

        try {
            String label = dataSet.get(i).getString(ThreeTemplatesApp.TAG_LABEL);
            String image = dataSet.get(i).getString(ThreeTemplatesApp.TAG_IMAGE);
            String web_url = dataSet.get(i).getString(ThreeTemplatesApp.TAG_WEBURL);

            Picasso.with(context).load(image).into(viewHolder.imageView);
            viewHolder.textViewDescription.setText(label);
            viewHolder.textViewCost.setText("100 Rupees");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public RecyclerAdapter(List<JSONObject> dataSet){
        this.dataSet = dataSet;
    }
}

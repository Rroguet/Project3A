package com.example.project3a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Galaxie> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        ImageView icon;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView)  v.findViewById(R.id.secondLine);
            icon = (ImageView) v.findViewById(R.id.icon);
        }

    }

    private void Description(int position, Context c) {
        Intent intent = new Intent(c, DescriptionActivity.class);
        intent.putExtra(Constants.EXTRA_GALAXIE_NAME, values.get(position).getName());
        intent.putExtra(Constants.EXTRA_GALAXIE_IMAGE, values.get(position).getUrl());
        //intent.putExtra(Constants.EXTRA_GALAXIE_DESCRIPTION, values.get(position).getDescription());
        c.startActivity(intent);
    }

    public ListAdapter(List<Galaxie> galaxies) {
        values = galaxies;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Galaxie galaxie = values.get(position);
        holder.txtHeader.setText(galaxie.getName());
        holder.txtFooter.setText("Constellation: "+galaxie.getConstellation());
        Picasso.get().load(galaxie.getUrl()).fit().into(holder.icon);

        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Description(position, v.getContext());
            }
        });
    }

    // Return the number of galaxies
    @Override
    public int getItemCount() {
        return values.size();
    }

}
package com.example.project3a.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project3a.R;
import com.example.project3a.presentation.model.Galaxie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Galaxie> values;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Galaxie item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
        TextView txtFooter;
        ImageView icon;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            icon = v.findViewById(R.id.icon);
        }

    }

    public ListAdapter(List<Galaxie> galaxies, OnItemClickListener listener) {
        this.values = galaxies;
        this.listener = listener;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Galaxie galaxie = values.get(position);
        holder.txtHeader.setText(galaxie.getName());
        holder.txtFooter.setText("Constellation: "+galaxie.getConstellation());
        Picasso.get().load(galaxie.getUrl()).fit().into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(galaxie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
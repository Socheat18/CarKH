package com.example.carkh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<MyDataModel> dataList;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(List<MyDataModel> dataList) {
        this.dataList = dataList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (dataList != null && position < dataList.size()) {
            MyDataModel data = dataList.get(position);
            holder.bind(data);
        }
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView cardTitle;
        TextView cardDescription;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.card_image);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardDescription = itemView.findViewById(R.id.card_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }

        public void bind(MyDataModel dataModel) {
            if (!dataModel.getImageResIds().isEmpty()) {
                cardImage.setImageResource(dataModel.getImageResIds().get(0)); // Display the first image
            } else {
                // Handle the case where there are no images
                // You might want to set a placeholder image or hide the ImageView
            }
            cardTitle.setText(dataModel.getTitle());
            cardDescription.setText(dataModel.getDescription());
        }

    }
}

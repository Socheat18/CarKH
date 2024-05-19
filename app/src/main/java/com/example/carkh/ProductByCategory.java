package com.example.carkh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductByCategory extends RecyclerView.Adapter<ProductByCategory.ViewHolder> {

    private List<MyDataModel> categoryList;
    private OnItemClickListener onItemClickListener;

    public ProductByCategory(List<MyDataModel> categoryList) {
        this.categoryList = categoryList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyDataModel item = categoryList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_image);
            titleTextView = itemView.findViewById(R.id.card_title);
            descriptionTextView = itemView.findViewById(R.id.card_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(MyDataModel dataModel) {
            if (!dataModel.getImageResIds().isEmpty()) {
                imageView.setImageResource(dataModel.getImageResIds().get(0)); // Display the first image
            } else {
                // Handle the case where there are no images
                // You might want to set a placeholder image or hide the ImageView
            }
//            imageView.setImageResource(dataModel.getImageResId());
            titleTextView.setText(dataModel.getTitle());
            descriptionTextView.setText(dataModel.getDescription());
        }
    }
}

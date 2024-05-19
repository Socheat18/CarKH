package com.example.carkh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment {

    private ViewPager2 viewPager;
    private TextView productTitleTextView;
    private TextView productDescriptionTextView;
    private TextView productPriceTextView;
    private Button orderButton;
    private Button cancelButton;
    private NumberPicker itemNumberPicker; // Changed to NumberPicker

    private List<Integer> imageList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        productTitleTextView = view.findViewById(R.id.productTitleTextView);
        productDescriptionTextView = view.findViewById(R.id.productDescriptionTextView);
        productPriceTextView = view.findViewById(R.id.productPriceTextView);
        orderButton = view.findViewById(R.id.orderButton);
        cancelButton = view.findViewById(R.id.cancelButton);
        itemNumberPicker = view.findViewById(R.id.itemNumberPicker); // Assign to NumberPicker

        // Set the minimum and maximum values for the NumberPicker
        itemNumberPicker.setMinValue(1);
        itemNumberPicker.setMaxValue(10); // Change this to your desired maximum value

        if (getArguments() != null) {
            String title = getArguments().getString("title");
            String description = getArguments().getString("description");
            double price = getArguments().getDouble("price", 0.0);
            imageList = getArguments().getIntegerArrayList("imageList");

            productTitleTextView.setText(title);
            productDescriptionTextView.setText(description);
            productPriceTextView.setText(String.format("Price: $%.2f", price));

            if (imageList != null && !imageList.isEmpty()) {
                setupViewPager();
            } else {
                Toast.makeText(getContext(), "No images to display", Toast.LENGTH_SHORT).show();
            }
        }

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = itemNumberPicker.getValue(); // Get selected quantity
                // Perform order processing with selected quantity
                Toast.makeText(getContext(), "Order placed for " + quantity + " items", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back or perform cancel operation
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void setupViewPager() {
        ImagePagerAdapter adapter = new ImagePagerAdapter(imageList);
        viewPager.setAdapter(adapter);
    }

    private class ImagePagerAdapter extends RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder> {

        private List<Integer> images;

        public ImagePagerAdapter(List<Integer> images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            holder.imageView.setImageResource(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ImageViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
}

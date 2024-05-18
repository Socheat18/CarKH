package com.example.carkh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment {

    private ViewPager viewPager;
    private TextView productTitleTextView;
    private TextView productDescriptionTextView;
    private TextView productPriceTextView;
    private Button orderButton;
    private Button cancelButton;
    private Button addItemButton;

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
        addItemButton = view.findViewById(R.id.addItemButton);

        if (getArguments() != null) {
            String title = getArguments().getString("title");
            String description = getArguments().getString("description");
            double price = getArguments().getDouble("price", 0.0);
            imageList = getArguments().getIntegerArrayList("imageList");

            List<Integer> imageList = new ArrayList<>();
            imageList.add(R.drawable.odi_car); // replace with your actual drawable resource IDs
            imageList.add(R.drawable.odi_car);
            imageList.add(R.drawable.odi_car);


            productTitleTextView.setText(title);
            productDescriptionTextView.setText(description);
            productPriceTextView.setText(String.format("Price: $%.2f", price));

            if (imageList != null && !imageList.isEmpty()) {
                setupViewPager(imageList);
            } else {
                Toast.makeText(getContext(), "No images to display", Toast.LENGTH_SHORT).show();
            }
        }

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Order placed", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back or perform cancel operation
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setupViewPager(List<Integer> imageList) {
        ImagePagerAdapter adapter = new ImagePagerAdapter(imageList);
        viewPager.setAdapter(adapter);
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private List<Integer> images;

        public ImagePagerAdapter(List<Integer> images) {
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(images.get(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }
    }
}

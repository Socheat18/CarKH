package com.example.carkh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private MyAdapter adapter;
    private ProductByCategory adapter2;
    private List<MyDataModel> dataList;
    private List<MyDataModel> categoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 = view.findViewById(R.id.recyclerView2);

        // Set LayoutManagers
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Initialize data lists
        initializeData();

        // Set up adapters
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click for dataList
                MyDataModel dataModel = dataList.get(position);
                openProductDetail(dataModel);
            }
        });

        adapter2 = new ProductByCategory(categoryList);
        recyclerView2.setAdapter(adapter2);

        adapter2.setOnItemClickListener(new ProductByCategory.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click for categoryList
                MyDataModel dataModel = categoryList.get(position);
                openProductDetail(dataModel);
            }
        });

        return view;
    }

    private void initializeData() {
        // Sample data for first RecyclerView
        dataList = new ArrayList<>();
        dataList.add(new MyDataModel("Title 1", "Description 1", R.drawable.odi_car, 12.4F));
        dataList.add(new MyDataModel("Title 2", "Description 2", R.drawable.odi_car,12.4F));
        dataList.add(new MyDataModel("Title 3", "Description 3", R.drawable.odi_car, 20.20F ));
        dataList.add(new MyDataModel("Title 4", "Description 4", R.drawable.odi_car, 20.20F));
        dataList.add(new MyDataModel("Title 5", "Description 5", R.drawable.odi_car, 20.20F));

        // Sample data for second RecyclerView
        categoryList = new ArrayList<>();
        categoryList.add(new MyDataModel("Category 1", "Description A", R.drawable.odi_car, 20.20F));
        categoryList.add(new MyDataModel("Category 2", "Description B", R.drawable.odi_car, 20.20F));
        categoryList.add(new MyDataModel("Category 3", "Description C", R.drawable.odi_car, 20.20F));
        categoryList.add(new MyDataModel("Category 4", "Description D", R.drawable.odi_car, 20.20F));
        categoryList.add(new MyDataModel("Category 5", "Description E", R.drawable.odi_car, 20.20F));
    }

    private void openProductDetail(MyDataModel dataModel) {
        // Open product detail fragment and pass data
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", dataModel.getTitle());
        bundle.putString("description", dataModel.getDescription());
        bundle.putInt("imageResource", dataModel.getImageResId());
        bundle.putDouble("price", dataModel.getPrice());
        fragment.setArguments(bundle);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}


package com.example.carkh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<CategoryItem> categoryItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Initialize the list and adapter
        categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(R.drawable.bmw, "BMW"));
        categoryItemList.add(new CategoryItem(R.drawable.toyota, "TOYOTA"));
        categoryItemList.add(new CategoryItem(R.drawable.ford_logo, "FORD"));
        categoryItemList.add(new CategoryItem(R.drawable.honda, "HONDA"));
        adapter = new CategoryAdapter(getContext(), categoryItemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

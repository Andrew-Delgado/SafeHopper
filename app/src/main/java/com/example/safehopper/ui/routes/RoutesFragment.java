package com.example.safehopper.ui.routes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.safehopper.R;
import com.example.safehopper.models.Route;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoutesFragment extends Fragment {

    private static final String TAG = "RoutesFragment";

    private RoutesViewModel routesViewModel;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_routes, container, false);
        mRecyclerView = root.findViewById(R.id.recycler_view);

        routesViewModel = ViewModelProviders.of(this).get(RoutesViewModel.class);

        routesViewModel.init();

        Log.d(TAG, "onCreateView: started");

        routesViewModel.getRoutes().observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initRouteListItems();
        return root;
    }

    private void initRouteListItems()
    {
        mAdapter = new RecyclerViewAdapter(getContext(), routesViewModel.getRoutes().getValue());
        RecyclerView.LayoutManager linearLayerManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayerManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
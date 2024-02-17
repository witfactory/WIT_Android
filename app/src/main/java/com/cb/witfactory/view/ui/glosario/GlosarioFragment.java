package com.cb.witfactory.view.ui.glosario;

import static org.chromium.base.ContextUtils.getApplicationContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cb.witfactory.R;
import com.cb.witfactory.adapter.ExpandableListGlosarioAdapter;
import com.cb.witfactory.data.classModel.ExpandableListDataPumpDumy;
import com.cb.witfactory.databinding.FragmentGlosarioBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GlosarioFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListGlosarioAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    private FragmentGlosarioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GlosarioViewModel loginViewModel =
                new ViewModelProvider(this).get(GlosarioViewModel.class);

        binding = FragmentGlosarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        expandableListView = (ExpandableListView) root.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPumpDumy.getData();


        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        expandableListAdapter = new ExpandableListGlosarioAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                                getApplicationContext(),
                                expandableListTitle.get(groupPosition)
                                        + " -> "
                                        + expandableListDetail.get(
                                        expandableListTitle.get(groupPosition)).get(
                                        childPosition), Toast.LENGTH_SHORT
                        )
                        .show();
                return false;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}
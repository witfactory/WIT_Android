package com.cb.witfactory.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cb.witfactory.databinding.FragmentActivityBinding;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;


public class ActivityFragment extends Fragment {

    private FragmentActivityBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityViewModel loginViewModel =
                new ViewModelProvider(this).get(ActivityViewModel.class);

        binding = FragmentActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configura un oyente para eventos de selección de fecha
        binding.collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {

            @Override
            public void onDaySelect() {
                String selectedDate = binding.collapsibleCalendar.getSelectedDay().toString();

            }

            @Override
            public void onItemClick(View v) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int position) {

            }
        });

        return root;
    }


}
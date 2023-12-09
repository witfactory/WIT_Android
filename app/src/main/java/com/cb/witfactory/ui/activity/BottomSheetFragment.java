package com.cb.witfactory.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.cb.witfactory.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.time.LocalDateTime;

import javax.annotation.Nullable;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private LocalDateTime timestamp;
    private Context context;
    private String deviceId;
    private String title;
    private Double value;
    private String color;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
    }

    public BottomSheetFragment newInstance(String color, View view) {
        BottomSheetFragment fragment = new BottomSheetFragment();
        Bundle args = new Bundle();
        args.putString("color", color);
        fragment.setArguments(args);
        View bottomSheetCardView = view.findViewById(R.id.boderTop);
        ImageView iconImageView = view.findViewById(R.id.iconImageView);
        if ("red".equals(color)) {
            bottomSheetCardView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red));
            iconImageView.setImageResource(R.drawable.baseline_warning_amber_24);
        } else if ("green".equals(color)) {
            bottomSheetCardView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green));
            iconImageView.setImageResource(R.drawable.baseline_check_circle_outline_24);
        }
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            timestamp = (LocalDateTime) args.getSerializable("timestamp");
            deviceId = args.getString("deviceId");
            title = args.getString("title");
            value = args.getDouble("value");
            color = args.getString("color");
            setEventData(view);
            newInstance(color, view);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        int fragmentHeight = (int) (screenHeight * 0.4);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = fragmentHeight;
    }

    public void setEventData(View view) {
        if (view != null) {
            try {
                TextView timestampTextView = view.findViewById(R.id.fechaTextView);
                TextView deviceIdTextView = view.findViewById(R.id.nombreSensorTextView);
                TextView titleTextView = view.findViewById(R.id.gasDetectadoTextView);
                TextView valueTextView = view.findViewById(R.id.valorTextView);

                timestampTextView.setText("Fecha: " + timestamp.toString());
                deviceIdTextView.setText("Nombre del sensor: " + deviceId);
                titleTextView.setText("Gas detectado: " + title);
                if ("red".equals(color)) {
                    valueTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                } else {
                    valueTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green));
                }
                valueTextView.setText("Valor promedio: " + value.toString());
            } catch (Error err) {
                System.out.println(err);
            }
        }
    }
}


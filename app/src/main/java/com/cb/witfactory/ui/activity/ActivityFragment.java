package com.cb.witfactory.ui.activity;

import static org.chromium.base.ContextUtils.getApplicationContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cb.witfactory.R;
import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.ListValueDeviceAdapter;
import com.cb.witfactory.data.classModel.MyDividerItemDecoration;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.databinding.FragmentActivityBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.ui.device.DeviceViewModel;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment implements DeviceAdapter.DeviceAdapterListener, ListValueDeviceAdapter.ValueDeviceAdapterListener, Callfun {

    private FragmentActivityBinding binding;
    private Spinner spinnerDevice;
    private DeviceViewModel deviceViewModel;
    private List<DeviceResponse> deviceList;  // Cambié ArrayList a List

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityViewModel loginViewModel =
                new ViewModelProvider(this).get(ActivityViewModel.class);

        binding = FragmentActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        spinnerDevice = root.findViewById(R.id.spinner_device);
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
        deviceViewModel.setListener(ActivityFragment.this);
        deviceViewModel.getDataDevice("c8174124-b6b3-4a35-8457-429a9b947ea3", "S");

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

    private List<String> getNameDevices(List<DeviceResponse> devices) {
        List<String> deviceNames = new ArrayList<>();
        for (DeviceResponse device : devices) {
            deviceNames.add(device.getDevice_name());
        }
        return deviceNames;
    }

    private void selectDevice(String device) {
        // acciones según el dispositivo seleccionado
    }

    @Override
    public void onDeviceSelected(DeviceResponse contact) {
        // acciones al seleccionar un dispositivo
    }

    @Override
    public void onListValueDeviceSelected(Metric device) {
        // acciones aseleccionar un valor de dispositivo
    }

    @Override
    public void onSuccess(String s) {
    }

    @Override
    public void onSuccess(Object o, String s) {
        if (s.equals("getdevice")) {
            deviceList = (List<DeviceResponse>) o;

            if (deviceList != null) {
                List<String> deviceNames = getNameDevices(deviceList);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, deviceNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerDevice.setAdapter(adapter);

                spinnerDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String selectedDevice = (String) parentView.getItemAtPosition(position);
                        selectDevice(selectedDevice);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // Acciones si no se ha seleccionado nada (opcional)
                    }
                });
            }
        }
    }

    @Override
    public void onError(String s) {
        // Implementa las acciones necesarias al encontrar un error
    }
}

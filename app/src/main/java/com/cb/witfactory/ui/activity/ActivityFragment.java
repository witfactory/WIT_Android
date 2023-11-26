package com.cb.witfactory.ui.activity;

import static org.chromium.base.ContextUtils.getApplicationContext;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cb.witfactory.R;
import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.ListValueDeviceAdapter;
import com.cb.witfactory.data.classModel.MyDividerItemDecoration;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.device.GetDeviceResponse;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.databinding.FragmentActivityBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.ui.device.DeviceViewModel;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityFragment extends Fragment implements DeviceAdapter.DeviceAdapterListener, ListValueDeviceAdapter.ValueDeviceAdapterListener, Callfun {

    private FragmentActivityBinding binding;
    private Spinner spinnerDevice;
    private DeviceViewModel deviceViewModel;
    private List<DeviceResponse> deviceList;  // Cambié ArrayList a List
    private AutoCompleteTextView autoCompleteTextViewDevice;
    private GetDeviceResponse getDeviceResponse;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityViewModel loginViewModel =
                new ViewModelProvider(this).get(ActivityViewModel.class);

        binding = FragmentActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        autoCompleteTextViewDevice = root.findViewById(R.id.txt_country);
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
        deviceViewModel.setListener(ActivityFragment.this);
        deviceViewModel.getDataDevice("c8174124-b6b3-4a35-8457-429a9b947ea3", "S");
        getDeviceResponse = new GetDeviceResponse((ArrayList<DeviceResponse>) deviceList, 0, 0);
        root = inflater.inflate(R.layout.fragment_activity, container, false);
        ImageButton btnCalendar = root.findViewById(R.id.btnCalendar);
        CollapsibleCalendar collapsibleCalendar = root.findViewById(R.id.collapsibleCalendar);

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

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (collapsibleCalendar.getVisibility() == View.VISIBLE) {
                    collapsibleCalendar.setVisibility(View.GONE);
                } else {
                    collapsibleCalendar.setVisibility(View.VISIBLE);
                }
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

    private void selectDevice(Object device) {
        Log.d("TAG", "selectDevice: " + device);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, deviceNames);
                autoCompleteTextViewDevice.setAdapter(adapter);

                autoCompleteTextViewDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedDevice = (String) parent.getItemAtPosition(position);
                        selectDevice(selectedDevice);
                        // Llama a la función correspondiente al dispositivo seleccionado
                        DeviceResponse deviceSelect = getDeviceResponse.getDeviceByName(deviceList, selectedDevice);
                        selectDevice(deviceSelect.getDevice_id());
                    }
                });
            }
        }
    }

    @Override
    public void onError(String s) {
        // Implementa las acciones necesarias al encontrar un error
    }

    public void getMetrics(List<DeviceResponse> deviceList) {
        Calendar calendar = Calendar.getInstance();
        Date fechaActual = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date fechaMenos30Dias = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String fechaFormateada = sdf.format(fechaMenos30Dias);
        String fechaActualFormateada = sdf.format(fechaActual);
        String deviceId = deviceList.get(0).getDevice_id();
        deviceViewModel.getMetrics(deviceId, fechaFormateada, fechaActualFormateada);
    }


}

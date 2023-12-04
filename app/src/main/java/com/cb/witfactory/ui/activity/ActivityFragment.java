package com.cb.witfactory.ui.activity;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;
import static org.chromium.base.ContextUtils.getApplicationContext;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import in.akshit.horizontalcalendar.Tools;

public class ActivityFragment extends Fragment implements DeviceAdapter.DeviceAdapterListener, ListValueDeviceAdapter.ValueDeviceAdapterListener, Callfun {

    private FragmentActivityBinding binding;
    private DeviceViewModel deviceViewModel;
    private List<DeviceResponse> deviceList;
    private AutoCompleteTextView autoCompleteTextViewDevice;
    private GetDeviceResponse getDeviceResponse;
    private ArrayAdapter<String> adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityViewModel loginViewModel =
                new ViewModelProvider(this).get(ActivityViewModel.class);
        binding = FragmentActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        autoCompleteTextViewDevice = root.findViewById(R.id.txt_autocomplete);
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
        deviceViewModel.setListener(ActivityFragment.this);
        deviceViewModel.getDataDevice("c8174124-b6b3-4a35-8457-429a9b947ea3", "S");
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line);
        getDeviceResponse = new GetDeviceResponse((ArrayList<DeviceResponse>) deviceList, 0, 0);
        ImageButton btnCalendar = root.findViewById(R.id.btnCalendar);
        CollapsibleCalendar collapsibleCalendar = root.findViewById(R.id.collapsibleCalendar);
        collapsibleCalendar.collapse(1);
        Calendar starttime = Calendar.getInstance();
        starttime.add(Calendar.MONTH, -6);

        Calendar endtime = Calendar.getInstance();
        endtime.add(Calendar.MONTH, 6);

        ArrayList datesToBeColored = new ArrayList();
        datesToBeColored.add(Tools.getFormattedDateToday());

        binding.collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {

            @Override
            public void onDaySelect() {
                Day selectedDay = binding.collapsibleCalendar.getSelectedDay();
                String selectDate = getFormatedDate(selectedDay);
                String range = getRange30Days(getFormatedDate(selectedDay));
                deviceViewModel.getMetrics("", range, selectDate);
                onDaySelected();
            }

            @Override
            public void onItemClick(View v) {
                Log.d("TAG", "item selected: "+ v);
            }

            @Override
            public void onDataUpdate() {
            }

            @Override
            public void onMonthChange() {
            }

            @Override
            public void onWeekChange(int position) {
                Log.d("TAG", "week sel: "+ position);
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
                adapter.clear();
                adapter.addAll(deviceNames);
                autoCompleteTextViewDevice.setAdapter(adapter);

                autoCompleteTextViewDevice.setOnItemClickListener((parent, view, position, id) -> {
                    String selectedDevice = (String) parent.getItemAtPosition(position);
                    selectDevice(selectedDevice);
                    DeviceResponse deviceSelect = getDeviceResponse.getDeviceByName(deviceList, selectedDevice);
                    selectDevice(deviceSelect.getDevice_id());
                    Day selectedDay = binding.collapsibleCalendar.getSelectedDay();
                    String selectDate = getFormatedDate(selectedDay);
                    String range = getRange30Days(getFormatedDate(selectedDay));
                    deviceViewModel.getMetrics(deviceSelect.getDevice_id(), range, selectDate);
                });
                getMetrics(deviceList);
            }
        }
        // events
        if (s.equals("getevents")) {
            List<Metric> deviceList = (List<Metric>) o;
            //RecyclerView recyclerView = getView().findViewById(R.id.events);
            if (deviceList.size() > 0) {
                //RecyclerView recyclerView1 = binding.getRoot().findViewById(R.id.events);
                RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                //recyclerView1.setLayoutManager(mLayoutManager2);
                //recyclerView1.setItemAnimator(new DefaultItemAnimator());
                //recyclerView1.addItemDecoration(new MyDividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL, 36));
            }
        }
    }

    @Override
    public void onError(String s) {
        // Implementa las acciones necesarias al encontrar un error
    }

    public void getMetrics(List<DeviceResponse> deviceList) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date newDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String formattedCurrentDate = sdf.format(currentDate);
        String formattedNewDate = sdf.format(newDate);
        String deviceId = deviceList.get(0).getDevice_id();
        deviceViewModel.getMetrics(deviceId, formattedNewDate, formattedCurrentDate);
    }
    public DeviceResponse getDeviceByName(List<DeviceResponse> devices, String deviceName) {
        for (DeviceResponse device : devices) {
            if (device.getDevice_name().equals(deviceName)) {
                return device;
            }
        }
        return null;  // Retorna null si no se encuentra el dispositivo con el nombre dado
    }

    public void getEventsByDate(String idDevice) {
        deviceViewModel.getMetrics(idDevice, "2023-09-19T19:47:45", "2023-09-19T25:47:46");
    }

    public String getFormatedDate(Day selectedDay) {
        Calendar selectedDateCalendar = Calendar.getInstance();
        selectedDateCalendar.set(selectedDay.getYear(), selectedDay.getMonth(), selectedDay.getDay());
        Date selectedDate = selectedDateCalendar.getTime();
        Log.d("TAG", "onDaySelect: " + selectedDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String selectedDateFormatted = sdf.format(selectedDate);
        return selectedDateFormatted;
    }

    public String getRange30Days(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        try {
            Date oldDate = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(oldDate);
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            Date dateRange = calendar.getTime();
            return sdf.format(dateRange);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onDaySelected() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }

}


package com.cb.witfactory.ui.activity;

import static org.chromium.base.ContextUtils.getApplicationContext;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cb.witfactory.R;
import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.Event;
import com.cb.witfactory.adapter.EventAdapter;
import com.cb.witfactory.data.classModel.MyDividerItemDecoration;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.device.GetDeviceResponse;
import com.cb.witfactory.data.retrofit.events.DeviceMetrics;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.databinding.FragmentActivityBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.ui.device.DeviceViewModel;
import com.cb.witfactory.ui.home.UserIdHolder;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import in.akshit.horizontalcalendar.Tools;

public class ActivityFragment extends Fragment implements DeviceAdapter.DeviceAdapterListener, Callfun {

    private FragmentActivityBinding binding;
    private DeviceViewModel deviceViewModel;
    private List<DeviceResponse> deviceList;
    private AutoCompleteTextView autoCompleteTextViewDevice;
    private GetDeviceResponse getDeviceResponse;
    private ArrayAdapter<String> adapter;
    private List<Callfun.ValueDevice> valueDeviceList;
    private EventAdapter listValueDeviceAdapter;
    List<Event> totalEventList;
    public String deviceId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityViewModel loginViewModel =
                new ViewModelProvider(this).get(ActivityViewModel.class);
        binding = FragmentActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        autoCompleteTextViewDevice = root.findViewById(R.id.txt_autocomplete);
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
        deviceViewModel.setListener(ActivityFragment.this);
        String userId = UserIdHolder.getInstance().getUserId();
        deviceViewModel.getDataDevice(userId, "S");
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

        RecyclerView recyclerView = root.findViewById(R.id.events);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL) {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                // No dibujar la línea divisoria
            }
        });
        binding.events.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                int action = e.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        handleActionUp(rv, e);
                        break;
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        binding.collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {

            @Override
            public void onDaySelect() {
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
    private void handleActionUp(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        int position = rv.getChildAdapterPosition(childView);

        if (position != RecyclerView.NO_POSITION) {
            Event selectedEvent = totalEventList.get(position);
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            Bundle args = new Bundle();
            String selectedDevice = autoCompleteTextViewDevice.getText().toString();
            args.putSerializable("timestamp", selectedEvent.getTimestamp());
            args.putString("deviceId", selectedDevice);
            args.putString("title", selectedEvent.getTitle());
            args.putDouble("value", selectedEvent.getValue());
            args.putString("color", selectedEvent.getColor());
            bottomSheetFragment.setArguments(args);

            bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
        }
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
                    this.deviceId = deviceSelect.getDevice_id();
                    Day selectedDay = binding.collapsibleCalendar.getSelectedDay();
                    String selectDate = getFormatedDate(selectedDay);
                    String range = getRange30Days(getFormatedDate(selectedDay));
                    onDaySelected();
                    //deviceViewModel.getMetrics(deviceSelect.getDevice_id(), range, selectDate);
                });
                getMetrics(deviceList);
            }
        }
        // events
        if (s.equals("gettotalevents")) {
            List<Event> eventList = (List<Event>) o;
            RecyclerView recyclerView = getView().findViewById(R.id.events);
            totalEventList = eventList;
            valueDeviceList = new ArrayList<>();
            listValueDeviceAdapter = new EventAdapter(getActivity(), eventList, this);
            Context context = requireContext();
            EventAdapter eventAdapter = new EventAdapter(context, eventList, this);
            // Asigna el adaptador al RecyclerView
            recyclerView.setAdapter(eventAdapter);
            RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            listValueDeviceAdapter.notifyDataSetChanged();
            binding.events.setLayoutManager(mLayoutManager2);
            binding.events.setItemAnimator(new DefaultItemAnimator());
            binding.events.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 5));
            binding.events.setAdapter(listValueDeviceAdapter);
            listValueDeviceAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String s) {
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
        onDaySelected();
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
        Day selectedDay = binding.collapsibleCalendar.getSelectedDay();
        String selectDate = getFormatedDate(selectedDay);
        LocalDateTime selectedDateTime = LocalDateTime.parse(selectDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        LocalDateTime startOfDayDateTime = selectedDateTime.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDayDateTime = selectedDateTime.withHour(23).withMinute(59).withSecond(59);
        String startRange = startOfDayDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String endRange = endOfDayDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String selectedDevice = autoCompleteTextViewDevice.getText().toString();
        if (TextUtils.isEmpty(selectedDevice)) {
            // No hay dispositivo seleccionado, muestra un diálogo de alerta
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setMessage("Por favor, selecciona un dispositivo")
                    .setPositiveButton("OK", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        } else {
            deviceViewModel.getMetrics(this.deviceId, startRange, endRange);
        }
    }
}


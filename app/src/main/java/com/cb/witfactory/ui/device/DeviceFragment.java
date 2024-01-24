package com.cb.witfactory.ui.device;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;
import static org.chromium.base.ContextUtils.getApplicationContext;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cb.witfactory.R;
import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.ListValueDeviceAdapter;
import com.cb.witfactory.data.classModel.MyDividerItemDecoration;
import com.cb.witfactory.data.retrofit.alarms.Alarm;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.data.retrofit.events.PayloadResponse;
import com.cb.witfactory.databinding.FragmentDeviceBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.model.PreferencesHelper;
import com.cb.witfactory.ui.home.UserIdHolder;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DeviceFragment extends Fragment implements DeviceAdapter.DeviceAdapterListener, ListValueDeviceAdapter.ValueDeviceAdapterListener, Callfun {

    private FragmentDeviceBinding binding;
    private DeviceViewModel deviceViewModel;
    private ArrayList<DeviceResponse> deviceList;
    private List<Callfun.ValueDevice> valueDeviceList;
    private DeviceAdapter mAdapter;
    private ListValueDeviceAdapter listValueDeviceAdapter;
    private TextInputEditText txtSearch;
    private ArrayList<DeviceResponse> deviceListAux;
    private ArrayList<Alarm> alarms;

    SweetAlertDialog pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDeviceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
        deviceList = new ArrayList<>();
        String userId = UserIdHolder.getInstance().getUserId();
        deviceViewModel.setListener(DeviceFragment.this);

        loadAlert();
        deviceViewModel.getDataDevice(userId, "S");

        txtSearch = root.findViewById(R.id.txt_search);

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterDevices(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.closeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alarms.size() > 0){
                    deviceViewModel.setAlarm(alarms);

                }else{
                    Toast.makeText(getActivity(),"no data", Toast.LENGTH_LONG).show();
                }



            }
        });


        binding.txtValvula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAlert();
                deviceViewModel.getDataDevice(userId, "V");
            }
        });

        binding.txtSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAlert();
                deviceViewModel.getDataDevice(userId, "S");
            }
        });

        return root;
    }

    @Override
    public void onDeviceSelected(DeviceResponse device) {
        String idDevice = device.getDevice_id().toString();
        String userEmail = PreferencesHelper.getEmail("email", "");
        Toast.makeText(getActivity(), "Selected: " + idDevice, Toast.LENGTH_LONG).show();
        deviceViewModel.getMetricsRealtime(idDevice, userEmail);
    }

    @Override
    public void onListValueDeviceSelected(Metric device) {
        Toast.makeText(getActivity(), "Selected: " + device.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String s) {
        if(s.equals("alarmaok")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),"silenced devices", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    @Override
    public void onSuccess(Object o, String s) {
        if (s.equals("getdevice")) {
            List<DeviceResponse> deviceList = (List<DeviceResponse>) o;
            if (deviceList.size() > 0) {
                binding.recyclerHorizontal.setVisibility(View.VISIBLE);
                this.deviceListAux = (ArrayList<DeviceResponse>) deviceList;
                mAdapter = new DeviceAdapter(getActivity(), deviceList, this,deviceViewModel);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.recyclerHorizontal.setLayoutManager(mLayoutManager);
                binding.recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerHorizontal.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL, 5));
                binding.recyclerHorizontal.setAdapter(mAdapter);


                alarms = new ArrayList<>();
                String userEmail = PreferencesHelper.getEmail("email", "");

                for (int i = 0; i < deviceList.size(); i++) {
                    Alarm alarm = new Alarm(deviceList.get(i).getDevice_id(),userEmail);
                    alarms.add(alarm);
                }



                getMetrics(deviceList);
            } else {
                /**######**/
                binding.recyclerHorizontal.setVisibility(View.GONE);
                binding.recyclerVertical.setVisibility(View.GONE);
                binding.imageView4.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(),"no data", Toast.LENGTH_LONG).show();
            }
        }

        if (s.equals("getevents")) {
            List<Metric> deviceList = (List<Metric>) o;
            if (deviceList.size() > 0) {
                binding.recyclerVertical.setVisibility(View.VISIBLE);
                valueDeviceList = new ArrayList<>();
                listValueDeviceAdapter = new ListValueDeviceAdapter(getActivity(), deviceList, this);
                RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                listValueDeviceAdapter.notifyDataSetChanged();
                binding.recyclerVertical.setLayoutManager(mLayoutManager2);
                binding.recyclerVertical.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerVertical.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 5));
                binding.recyclerVertical.setAdapter(listValueDeviceAdapter);
                listValueDeviceAdapter.notifyDataSetChanged();
            } else {
                binding.recyclerVertical.setVisibility(View.GONE);
                binding.imageView4.setVisibility(View.VISIBLE);
            }
        }

        hidenLoadAlert();
    }

    @Override
    public void onError(String s) {
        hidenLoadAlert();

        if(s.equals("alarmaerror")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),"no data", Toast.LENGTH_LONG).show();

                }
            });
        }


        if(s.equals("getDeviceError")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),"no data", Toast.LENGTH_LONG).show();
                    binding.recyclerHorizontal.setVisibility(View.GONE);
                    binding.recyclerVertical.setVisibility(View.GONE);
                }
            });
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.recyclerVertical.setVisibility(View.GONE);
                binding.imageView4.setVisibility(View.VISIBLE);
             }
        });



    }
    private void filterDevices(String searchText) {
        ArrayList<DeviceResponse> filteredList = new ArrayList<>();

        for (DeviceResponse device : deviceListAux) {
            if (device.getDevice_name().toLowerCase().contains(searchText.toLowerCase()) || device.getDevice_location().contains(searchText)) {
                filteredList.add(device);
            }
        }

        mAdapter.filterList(filteredList);
    }

    public void getMetrics(List<DeviceResponse> deviceList) {
        Calendar calendar = Calendar.getInstance();
        Date fechaActual = calendar.getTime();
        Date fechaMenos30Dias = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String fechaFormateada = sdf.format(fechaMenos30Dias);
        String fechaActualFormateada = sdf.format(fechaActual);
        String deviceId = deviceList.get(0).getDevice_id();
        String userEmail = PreferencesHelper.getEmail("email", "");
        deviceViewModel.getMetricsRealtime(deviceId,userEmail);
    }

    public void loadAlert(){
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void hidenLoadAlert(){
        if(pDialog != null){
            pDialog.dismiss();
        }

    }
}

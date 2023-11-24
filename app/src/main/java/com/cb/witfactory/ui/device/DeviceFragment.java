package com.cb.witfactory.ui.device;

import static org.chromium.base.ContextUtils.getApplicationContext;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cb.witfactory.R;
import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.ListValueDeviceAdapter;
import com.cb.witfactory.data.classModel.MyDividerItemDecoration;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.data.retrofit.events.PayloadResponse;
import com.cb.witfactory.databinding.FragmentDeviceBinding;
import com.cb.witfactory.model.Callfun;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DeviceFragment extends Fragment implements DeviceAdapter.DeviceAdapterListener, ListValueDeviceAdapter.ValueDeviceAdapterListener, Callfun {

    private FragmentDeviceBinding binding;
    private DeviceViewModel deviceViewModel;
    private ArrayList<DeviceResponse> deviceList;
    private List<Callfun.ValueDevice> valueDeviceList;
    private DeviceAdapter mAdapter;
    private ListValueDeviceAdapter listValueDeviceAdapter;
    private TextInputEditText txtSearch;
    private ArrayList<DeviceResponse> deviceListAux;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDeviceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);

        deviceList = new ArrayList<>();

        deviceViewModel.setListener(DeviceFragment.this);
        deviceViewModel.getDataDevice("c8174124-b6b3-4a35-8457-429a9b947ea3", "S");

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

        return root;
    }

    @Override
    public void onDeviceSelected(DeviceResponse device) {
        String idDevice = device.getDevice_id().toString();
        Toast.makeText(getActivity(), "Selected: " + idDevice, Toast.LENGTH_LONG).show();
        deviceViewModel.getMetrics(idDevice, "2023-09-19T19:47:45", "2023-09-19T25:47:46");
    }

    @Override
    public void onListValueDeviceSelected(Metric device) {
        Toast.makeText(getApplicationContext(), "Selected: " + device.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String s) {}

    @Override
    public void onSuccess(Object o, String s) {
        if (s.equals("getdevice")) {
            List<DeviceResponse> deviceList = (List<DeviceResponse>) o;
            if (deviceList.size() > 0) {
                this.deviceListAux = (ArrayList<DeviceResponse>) deviceList;
                mAdapter = new DeviceAdapter(getActivity(), deviceList, this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.recyclerHorizontal.setLayoutManager(mLayoutManager);
                binding.recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerHorizontal.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL, 5));
                binding.recyclerHorizontal.setAdapter(mAdapter);
                getMetrics(deviceList);
            } else {
                mAdapter = new DeviceAdapter(getActivity(), deviceList, this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.recyclerHorizontal.setLayoutManager(mLayoutManager);
                binding.recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerHorizontal.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL, 5));
                binding.recyclerHorizontal.setAdapter(mAdapter);
            }
        }

        if (s.equals("getevents")) {
            List<Metric> deviceList = (List<Metric>) o;
            if (deviceList.size() > 0) {
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
                valueDeviceList = new ArrayList<>();
                listValueDeviceAdapter = new ListValueDeviceAdapter(getActivity(), deviceList, this);
                RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                listValueDeviceAdapter.notifyDataSetChanged();
                binding.recyclerVertical.setLayoutManager(mLayoutManager2);
                binding.recyclerVertical.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerVertical.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 5));
                binding.recyclerVertical.setAdapter(listValueDeviceAdapter);
                listValueDeviceAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onError(String s) {}

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
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date fechaMenos30Dias = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String fechaFormateada = sdf.format(fechaMenos30Dias);
        String fechaActualFormateada = sdf.format(fechaActual);
        String deviceId = deviceList.get(0).getDevice_id();
        deviceViewModel.getMetrics(deviceId, fechaFormateada, fechaActualFormateada);
    }
}

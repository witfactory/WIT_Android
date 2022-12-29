package com.cb.witfactory.ui.device;

import static org.chromium.base.ContextUtils.getApplicationContext;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cb.witfactory.R;
import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.ListValueDeviceAdapter;
import com.cb.witfactory.data.classModel.MyDividerItemDecoration;
import com.cb.witfactory.data.model.Device;
import com.cb.witfactory.data.model.ValueDevice;
import com.cb.witfactory.databinding.FragmentDeviceBinding;
import com.cb.witfactory.databinding.FragmentLoginBinding;

import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends Fragment implements DeviceAdapter.DeviceAdapterListener,ListValueDeviceAdapter.ValueDeviceAdapterListener {

    private FragmentDeviceBinding binding;
    private DeviceViewModel mViewModel;


    private static final String TAG = DeviceFragment.class.getSimpleName();
    private List<Device> deviceList;
    private List<ValueDevice> valueDeviceList;
    private DeviceAdapter mAdapter;
    private ListValueDeviceAdapter listValueDeviceAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.cb.witfactory.ui.device.DeviceViewModel deviceViewModel =
                new ViewModelProvider(this).get(com.cb.witfactory.ui.device.DeviceViewModel.class);
        binding = FragmentDeviceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       //Horizontal
        deviceList = new ArrayList<>();
        Device objDevice = new Device(true,"","","Dispositivo","Garaje");
        Device objDevice2 = new Device(false,"","","Electorválvula","Cocina");
        Device objDevice3 = new Device(true,"","","Dispositivo","Baño");
        deviceList.add(objDevice);
        deviceList.add(objDevice2);
        deviceList.add(objDevice3);

        //ArrayList<Device> nombreArrayList = new ArrayList<Device>();
        mAdapter = new DeviceAdapter(getActivity(), deviceList, this);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,
                false);
        binding.recyclerHorizontal.setLayoutManager(mLayoutManager);
        binding.recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerHorizontal.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL, 5));
        binding.recyclerHorizontal.setAdapter(mAdapter);


        //vertical
        valueDeviceList = new ArrayList<>();
        ValueDevice objValueDevice = new ValueDevice("","45%",false,"Humedad Relativa");
        ValueDevice objValueDevice2 = new ValueDevice("","385ppb",true,"TVOC");
        ValueDevice objValueDevice3 = new ValueDevice("","75 F",false,"Temperatur<");
        valueDeviceList.add(objValueDevice);
        valueDeviceList.add(objValueDevice2);
        valueDeviceList.add(objValueDevice3);

        listValueDeviceAdapter = new ListValueDeviceAdapter(getActivity(), valueDeviceList, this);


        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,
                false);
        binding.recyclerVertical.setLayoutManager(mLayoutManager2);
        binding.recyclerVertical.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerVertical.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 5));
        binding.recyclerVertical.setAdapter(listValueDeviceAdapter);



        return root;
    }


    @Override
    public void onDeviceSelected(Device device) {
        Toast.makeText(getApplicationContext(), "Selected: " + device.getTitle(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onListValueDeviceSelected(ValueDevice device) {
        Toast.makeText(getApplicationContext(), "Selected: " + device.getTitle(), Toast.LENGTH_LONG).show();
    }
}
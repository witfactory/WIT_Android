package com.cb.witfactory.view.ui.device;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.ListValueDeviceAdapter;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.data.retrofit.events.PayloadResponse;
import com.cb.witfactory.databinding.FragmentDetailDeviceBinding;

import java.util.List;

public class DetailDevice extends Fragment  implements DeviceAdapter.DeviceAdapterListener,ListValueDeviceAdapter.ValueDeviceAdapterListener{

    private FragmentDetailDeviceBinding binding;
    private DetailDeviceViewModel mViewModel;

    private List<PayloadResponse> valueDeviceList;
    private ListValueDeviceAdapter listValueDeviceAdapter;


    public static DetailDevice newInstance() {
        return new DetailDevice();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailDeviceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailDeviceViewModel.class);
        // TODO: Use the ViewModel
    }

    /*
    public void datamock(){
        //Mock
        //vertical
        valueDeviceList = new ArrayList<>();
        PayloadResponse objValueDevice = new PayloadResponse();
        valueDeviceList.add(objValueDevice);

        listValueDeviceAdapter = new ListValueDeviceAdapter(getActivity(), valueDeviceList, this);


        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,
                false);
        binding.recyclerVertical.setLayoutManager(mLayoutManager2);
        binding.recyclerVertical.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerVertical.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 5));
        binding.recyclerVertical.setAdapter(listValueDeviceAdapter);
    }*/

    @Override
    public void onDeviceSelected(DeviceResponse contact) {

    }


    @Override
    public void onListValueDeviceSelected(Metric device) {

    }
}
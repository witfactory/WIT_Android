package com.cb.witfactory.ui.device;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;
import static org.chromium.base.ContextUtils.getApplicationContext;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.cb.witfactory.R;
import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.ListValueDeviceAdapter;
import com.cb.witfactory.data.classModel.MyDividerItemDecoration;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.device.ObjectResponseDevice;
import com.cb.witfactory.databinding.FragmentDeviceBinding;
import com.cb.witfactory.model.Callfun;
import com.cb.witfactory.ui.perfil.PerfilFragment;

import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends Fragment implements DeviceAdapter.DeviceAdapterListener,ListValueDeviceAdapter.ValueDeviceAdapterListener, Callfun {

    private FragmentDeviceBinding binding;
    private DeviceViewModel deviceViewModel;




    private static final String TAG = DeviceFragment.class.getSimpleName();
    private ArrayList<DeviceResponse> deviceList;
    private List<Callfun.ValueDevice> valueDeviceList;
    private DeviceAdapter mAdapter;
    private ListValueDeviceAdapter listValueDeviceAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
          binding = FragmentDeviceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        deviceViewModel = new ViewModelProvider(this).get(DeviceViewModel.class);

        deviceList = new ArrayList<DeviceResponse>();

        deviceViewModel.setListener(DeviceFragment.this);
        deviceViewModel.getDataDevice("c8174124-b6b3-4a35-8457-429a9b947ea3","S");


        return root;
    }




    @Override
    public void onDeviceSelected(DeviceResponse device) {
        String idDevice = device.getDevice_id().toString();

        Toast.makeText(getActivity(), "Selected: " + idDevice, Toast.LENGTH_LONG).show();

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_registro);
        navController.navigateUp();
        navController.navigate(R.id.device_detail);

    }


    @Override
    public void onListValueDeviceSelected(Callfun.ValueDevice device) {
        Toast.makeText(getApplicationContext(), "Selected: " + device.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onSuccess(Object o, String s) {

        ArrayList<DeviceResponse> deviceList = (ArrayList<DeviceResponse>) o;
        if (s.equals("getdevice")){

            if (deviceList.size() > 0){

                mAdapter = new DeviceAdapter(getActivity(), deviceList, this);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,
                        false);
                binding.recyclerHorizontal.setLayoutManager(mLayoutManager);
                binding.recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerHorizontal.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL, 5));
                binding.recyclerHorizontal.setAdapter(mAdapter);


                //Mock
                //vertical
                valueDeviceList = new ArrayList<>();
                Callfun.ValueDevice objValueDevice = new Callfun.ValueDevice("","45%",false,"Humedad Relativa");
                Callfun.ValueDevice objValueDevice2 = new Callfun.ValueDevice("","385ppb",true,"TVOC");
                Callfun.ValueDevice objValueDevice3 = new Callfun.ValueDevice("","75 F",false,"Temperatura");
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


            }
        }

    }

    @Override
    public void onError(String s) {

    }


}
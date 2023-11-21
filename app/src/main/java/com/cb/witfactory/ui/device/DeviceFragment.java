package com.cb.witfactory.ui.device;

import static org.chromium.base.ContextUtils.getApplicationContext;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cb.witfactory.adapter.DeviceAdapter;
import com.cb.witfactory.adapter.ListValueDeviceAdapter;
import com.cb.witfactory.data.classModel.MyDividerItemDecoration;
import com.cb.witfactory.data.retrofit.device.DeviceResponse;
import com.cb.witfactory.data.retrofit.events.Metric;
import com.cb.witfactory.data.retrofit.events.PayloadResponse;
import com.cb.witfactory.databinding.FragmentDeviceBinding;
import com.cb.witfactory.model.Callfun;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        deviceViewModel.getMetrics(idDevice,"2023-09-19T19:47:45","2023-09-19T25:47:46");

        /* NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
        navController.navigateUp();
        navController.navigate(R.id.device_detail);

        */

    }




    @Override
    public void onListValueDeviceSelected(Metric device) {
        Toast.makeText(getApplicationContext(), "Selected: " + device.getTitle(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onSuccess(Object o, String s) {

        if (s.equals("getdevice")){
            ArrayList<DeviceResponse> deviceList = (ArrayList<DeviceResponse>) o;

            if (deviceList.size() > 0){

              //  binding.recyclerHorizontal.setVisibility(View.VISIBLE);
                mAdapter = new DeviceAdapter(getActivity(), deviceList, this);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,
                        false);
                binding.recyclerHorizontal.setLayoutManager(mLayoutManager);
                binding.recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerHorizontal.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL, 5));
                binding.recyclerHorizontal.setAdapter(mAdapter);


                getMetrics(deviceList);
            }
            else{
               // binding.recyclerHorizontal.setVisibility(View.GONE);
                deviceList.clear();
                mAdapter = new DeviceAdapter(getActivity(), deviceList, this);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,
                        false);
                binding.recyclerHorizontal.setLayoutManager(mLayoutManager);
                binding.recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerHorizontal.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL, 5));
                binding.recyclerHorizontal.setAdapter(mAdapter);


            }
        }

        if (s.equals("getevents")){



           // ArrayList<PayloadResponse> deviceList = (ArrayList<PayloadResponse>) o;
             ArrayList<Metric> deviceList = (ArrayList<Metric>) o;
            if (deviceList.size() > 0) {
                //Mock
                //vertical
                //binding.recyclerVertical.setVisibility(View.VISIBLE);
                valueDeviceList = new ArrayList<>();

                listValueDeviceAdapter = new ListValueDeviceAdapter(getActivity(), deviceList, this);


                RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,
                        false);
                listValueDeviceAdapter.notifyDataSetChanged();
                binding.recyclerVertical.setLayoutManager(mLayoutManager2);
                binding.recyclerVertical.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerVertical.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 5));
                binding.recyclerVertical.setAdapter(listValueDeviceAdapter);
                listValueDeviceAdapter.notifyDataSetChanged();

            }
            else{
               // binding.recyclerVertical.setVisibility(View.GONE);
                deviceList.clear();
                valueDeviceList = new ArrayList<>();

                listValueDeviceAdapter = new ListValueDeviceAdapter(getActivity(), deviceList, this);


                RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,
                        false);
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
    public void onError(String s) {

    }



    public void getMetrics(ArrayList<DeviceResponse> deviceList){


        // Obtén la fecha actual
        Calendar calendar = Calendar.getInstance();
        Date fechaActual = calendar.getTime();

        // Resta 30 días a la fecha actual
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date fechaMenos30Dias = calendar.getTime();

        // Formatea la fecha en el formato deseado (yyyy-MM-dd'T'HH:mm:ss)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String fechaFormateada = sdf.format(fechaMenos30Dias);
        String fechaActualFormateada = sdf.format(fechaActual);
        String deviceId = deviceList.get(0).getDevice_id();

        // Imprime la fecha formateada

        deviceViewModel.getMetrics(deviceId,fechaFormateada,fechaActualFormateada);

    }


}
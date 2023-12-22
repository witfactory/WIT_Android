package com.cb.witfactory.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.cb.witfactory.R;
import javax.annotation.Nullable;


public class PingFragment extends Fragment {
    public static PingFragment newInstance() {
        return new PingFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ping, container, false);
    }
}
// PingFragment.java


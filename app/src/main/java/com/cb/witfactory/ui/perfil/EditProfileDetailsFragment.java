package com.cb.witfactory.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cb.witfactory.R;
import androidx.fragment.app.Fragment;

public class EditProfileDetailsFragment extends Fragment {

    public static EditProfileDetailsFragment newInstance() {
        return new EditProfileDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_profile_details, container, false);
        return root;
    }
}

package com.cb.witfactory.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cb.witfactory.R;
import com.cb.witfactory.databinding.FragmentEditProfileBinding;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class EditProfileDetailsFragment extends Fragment {
    private FragmentEditProfileBinding binding;
    public static EditProfileDetailsFragment newInstance() {
        return new EditProfileDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_profile_details, container, false);
        Button returnHomeButton = root.findViewById(R.id.return_home);
            returnHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para manejar el clic en el botón
                navigateToEditProfileDetailsFragment();
            }
        });
        return root;
    }

    private void navigateToEditProfileDetailsFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_login_menu);
        navController.navigateUp();
        navController.navigate(R.id.menu_home);
    }
}

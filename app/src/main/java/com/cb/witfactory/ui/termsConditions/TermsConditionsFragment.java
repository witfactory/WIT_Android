package com.cb.witfactory.ui.termsConditions;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.cb.witfactory.R;
import com.cb.witfactory.databinding.FragmentSlideshowBinding;
import com.cb.witfactory.databinding.FragmentTermsConditionsBinding;

public class TermsConditionsFragment extends Fragment {

    private TermsConditionsViewModel mViewModel;
    private FragmentTermsConditionsBinding binding;

    public static TermsConditionsFragment newInstance() {
        return new TermsConditionsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTermsConditionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.webview.getSettings().setJavaScriptEnabled(true); // Habilitar JavaScript
        binding.webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // Usar caché
        binding.webview.setWebViewClient(new WebViewClient()); // Manejar la navegación dentro del WebView
        binding.webview.loadUrl("https://wit-network.com/terminosycondiciones/");
        return root;
    }


}
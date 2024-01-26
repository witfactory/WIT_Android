package com.cb.witfactory.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;

import com.cb.witfactory.model.CountryCapitals;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<CountryCapitals> implements Filterable {

    private List<CountryCapitals> countryListFull;  // Lista completa de países para el filtro

    public CountryAdapter(@NonNull Context context, @NonNull List<CountryCapitals> countryList) {
        super(context, 0, countryList);
        countryListFull = new ArrayList<>(countryList);  // Inicializar la lista completa
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<CountryCapitals> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(countryListFull);  // Sin filtro, mostrar la lista completa
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CountryCapitals country : countryListFull) {
                    if (country.getName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(country);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear(); // Limpiar la lista actual
            addAll((List) results.values);  // Agregar los resultados filtrados
            notifyDataSetChanged();  // Notificar cambios en la vista
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((CountryCapitals) resultValue).getName();
        }
    };
}

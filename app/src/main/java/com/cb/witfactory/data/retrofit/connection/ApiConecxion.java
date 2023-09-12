package com.cb.witfactory.data.retrofit.connection;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiConecxion {
    private static MyApiService API_SERVICE;

    public static MyApiService getApiService() { //utiliza patron singelton se utiliza una instancia

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();//va a mostrar lo resultados de la peticion el codigo de respuetsa
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrlService = "https://b5tq0cyj8b.execute-api.us-east-1.amazonaws.com/staging/";

        //si api service es null se va instanciar de lo contrario va a devolver el objeto
        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrlService)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                            .build()) // <-- usamos el log level
                    .build();
            API_SERVICE = retrofit.create(MyApiService.class);
        }

        return API_SERVICE;
    }

}

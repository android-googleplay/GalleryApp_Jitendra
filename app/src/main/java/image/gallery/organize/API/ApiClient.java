package image.gallery.organize.API;

import image.gallery.organize.Helper.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static String BASE_URL;

    private static ApiClient retrofitCLient;

    private Retrofit retroMain;

    public ApiClient() {
        BASE_URL = Constant.getBase();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        chain -> {
                            Request original = chain.request();

                            Request.Builder requestBuilder = original.newBuilder()
                                    .method(original.method(), original.body());

                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                ).build();

        retroMain = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    public static synchronized ApiClient getInstance() {
        if (retrofitCLient == null) {
            retrofitCLient = new ApiClient();
        }
        return retrofitCLient;
    }

    public ApiInterface getApi2() {
        return retroMain.create(ApiInterface.class);
    }

}

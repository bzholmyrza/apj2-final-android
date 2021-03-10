package kz.edu.astanait.apj2_final_android.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIInterface {
    @GET("/api/hello")
    Call<String> sayHello(@Header("Authorization") String authorization);

    @GET("/api/hi")
    Call<String> sayHi();

    @POST("/login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("username") String username,
                             @Field("password") String password);
}

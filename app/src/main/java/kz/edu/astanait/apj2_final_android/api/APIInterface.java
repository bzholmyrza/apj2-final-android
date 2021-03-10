package kz.edu.astanait.apj2_final_android.api;

import java.util.List;

import kz.edu.astanait.apj2_final_android.model.QuestionWebModel;
import kz.edu.astanait.apj2_final_android.model.VoteModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("/login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("username") String username,
                             @Field("password") String password);

    @GET("/api/votes")
    Call<List<QuestionWebModel>> getVotes();

    @POST("/logout")
    Call<ResponseBody> logout();

    @POST("/api/votes")
    Call<ResponseBody> vote(@Body VoteModel voteModel);
}

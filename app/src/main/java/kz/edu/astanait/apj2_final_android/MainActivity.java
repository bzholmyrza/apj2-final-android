package kz.edu.astanait.apj2_final_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.CallScreeningService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kz.edu.astanait.apj2_final_android.api.APIClient;
import kz.edu.astanait.apj2_final_android.api.APIInterface;
import kz.edu.astanait.apj2_final_android.api.LoginRequest;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    EditText emailEditText;
    EditText passEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        emailEditText = findViewById(R.id.username);
        passEditText = findViewById(R.id.password);
        Button btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this::login);
    }

    public void login(View view) {

        //User user = new User(emailEditText.getText().toString(), passEditText.getText().toString());
        Call<ResponseBody> call = apiInterface.login(emailEditText.getText().toString().trim(), passEditText.getText().toString().trim());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("INFO: ", response.headers().get("Set-Cookie").toString());
                    //updAnswerTextView(response.body(), true);
                   // setToken(response.body());
                } else {
                    Log.d("INFO: ", "unsuccessful");
                 //   updAnswerTextView("Error code: "+response.code(), false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("INFO: ", "failure");
                //updAnswerTextView(t.getLocalizedMessage(), false);
                call.cancel();
            }
        });
    }
}
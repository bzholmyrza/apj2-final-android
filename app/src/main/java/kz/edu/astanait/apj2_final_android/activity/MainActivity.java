package kz.edu.astanait.apj2_final_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kz.edu.astanait.apj2_final_android.R;
import kz.edu.astanait.apj2_final_android.api.APIClient;
import kz.edu.astanait.apj2_final_android.api.APIInterface;
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
        Call<ResponseBody> call = apiInterface.login("bzholmyrza", "admin"/*emailEditText.getText().toString().trim(), passEditText.getText().toString().trim()*/);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("INFO: ", "success");
                    Intent intent = new Intent(getApplicationContext(), VoteActivity.class);
                    //Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("INFO: ", "unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("INFO: ", "failure");
                call.cancel();
            }
        });
    }
}
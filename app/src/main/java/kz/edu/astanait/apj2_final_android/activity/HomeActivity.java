package kz.edu.astanait.apj2_final_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import kz.edu.astanait.apj2_final_android.R;
import kz.edu.astanait.apj2_final_android.api.APIClient;
import kz.edu.astanait.apj2_final_android.api.APIInterface;
import kz.edu.astanait.apj2_final_android.model.QuestionWebModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    APIInterface apiInterface;
    private List<QuestionWebModel> tasks = new ArrayList<>();
    private ArrayAdapter<QuestionWebModel> itemsAdapter;
    private ListView lvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        lvItems = (ListView) findViewById(R.id.question_list);

        itemsAdapter = new ArrayAdapter<QuestionWebModel>(this,
                android.R.layout.simple_list_item_1, tasks);
        lvItems.setAdapter(itemsAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                QuestionWebModel todo = tasks.get(i);//TODO start activity for question and put extra
                lvItems.setAdapter(itemsAdapter);
            }
        });

        FloatingActionButton btn_logout = findViewById(R.id.logout);
        btn_logout.setOnClickListener(v->{
            logout();
        });
    }

    public void home() {
        Call<ResponseBody> call = apiInterface.home();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("INFO: ", "success");
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

    public void logout() {
        Call<ResponseBody> call = apiInterface.logout();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("INFO: ", "success");
                    finish();
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
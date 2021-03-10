package kz.edu.astanait.apj2_final_android.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import kz.edu.astanait.apj2_final_android.model.Question;
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
        home();
        lvItems = (ListView) findViewById(R.id.question_list);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                QuestionWebModel todo = tasks.get(i);
                Intent intent = new Intent(getApplicationContext(), VoteActivity.class);
                intent.putExtra("question", todo);
                startActivityForResult(intent, 0);
                lvItems.setAdapter(itemsAdapter);
            }
        });
        FloatingActionButton btn_logout = findViewById(R.id.logout);
        btn_logout.setOnClickListener(v->{
            logout();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        home();
    }

    public void home() {
        Call<List<QuestionWebModel>> call = apiInterface.getVotes();
        call.enqueue(new Callback<List<QuestionWebModel>>() {
            @Override
            public void onResponse(Call<List<QuestionWebModel>> call, Response<List<QuestionWebModel>> response) {
                if (response.isSuccessful()) {
                    Log.d("INFO: ", "success");
                    tasks = new ArrayList<>(response.body());
                    itemsAdapter = new ArrayAdapter<QuestionWebModel>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, tasks);
                    lvItems.setAdapter(itemsAdapter);
                } else {
                    Log.d("INFO: ", "unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<List<QuestionWebModel>> call, Throwable t) {
                Log.d("INFO: ", "failureHOME");
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
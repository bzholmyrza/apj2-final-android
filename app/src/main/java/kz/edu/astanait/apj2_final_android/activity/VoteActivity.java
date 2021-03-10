package kz.edu.astanait.apj2_final_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kz.edu.astanait.apj2_final_android.R;
import kz.edu.astanait.apj2_final_android.api.APIClient;
import kz.edu.astanait.apj2_final_android.api.APIInterface;
import kz.edu.astanait.apj2_final_android.model.Question;
import kz.edu.astanait.apj2_final_android.model.QuestionWebModel;
import kz.edu.astanait.apj2_final_android.model.VoteModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteActivity extends AppCompatActivity {
    APIInterface apiInterface;
    QuestionWebModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        Button btn_submit = findViewById(R.id.submit);
        QuestionWebModel webModel = (QuestionWebModel) getIntent().getSerializableExtra("question");
        model = webModel;
        ((TextView)findViewById(R.id.question_text)).setText(webModel.getQuestion().getQuestionText());
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton)radioGroup.getChildAt(i)).setText(webModel.getAnswers().get(i).getAnswerText());
        }
        btn_submit.setOnClickListener(v->{
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                if (radioGroup.getChildAt(i).getId() == checkedRadioButtonId) {
                    vote(i);
                    break;
                }
            }
        });
    }

    private void vote(int i) {
        VoteModel voteModel = new VoteModel();
        voteModel.setAnswerId(model.getAnswers().get(i).getAnswerId());
        Call<ResponseBody> call = apiInterface.vote(voteModel);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("INFO: ", "success");
                    Toast.makeText(getApplicationContext(), "Voted!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.d("INFO: ", "unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("INFO: ", "failureVOTE");
                call.cancel();
            }
        });
    }
}
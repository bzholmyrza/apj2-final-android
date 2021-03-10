package kz.edu.astanait.apj2_final_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import kz.edu.astanait.apj2_final_android.R;

public class VoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        Button btn_submit = findViewById(R.id.submit);

        btn_submit.setOnClickListener(v->{
            RadioButton checkedRadioButtonId = findViewById(radioGroup.getCheckedRadioButtonId());

        });
    }
}
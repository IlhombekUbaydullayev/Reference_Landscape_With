package com.example.savelandscapetext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText ed_tv;
    TextView tv_save;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0;
        initView();
    }
    void initView(){
        button = findViewById(R.id.btn_save);
        tv_save = findViewById(R.id.tv_save);
        ed_tv = findViewById(R.id.ed_tv);
        ed_tv.addTextChangedListener(loginTextWatcher);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   count += Integer.parseInt(ed_tv.getText().toString());
                   tv_save.setText("" + count);
                   ed_tv.getText().clear();
               }catch (Exception e){
                   Toast.makeText(MainActivity.this, "Please input only numbers", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String isEmptyInput = ed_tv.getText().toString().trim();
            button.setEnabled(!isEmptyInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("text",count);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("text",0);
        tv_save.setText("" + count);
    }

}
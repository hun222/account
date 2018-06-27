package com.hoonyeee.android.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {
    Spinner spinnerBank;
    EditText editAccount;
    Button btnDone;
    DBConnect con;
    int bankIdx = 0;
    String bankName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        editAccount = findViewById(R.id.editAccount);

        spinnerBank = findViewById(R.id.spinnerBank);
        spinnerBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // bankidx 넣어줘야함
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), position+"", Toast.LENGTH_SHORT).show();
                bankIdx = position;
                setAccount(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        con = new DBConnect(this);

        // 계좌 입력
        btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accNo = editAccount.getText().toString();
                try {
                    if(bankIdx == 0){
                        Toast.makeText(getBaseContext(), "(필수)은행을 선택하세요.", Toast.LENGTH_SHORT).show();
                    }else{
                        con.insert(bankIdx, bankName, accNo);
                        setResult(RESULT_OK);
                        // 작성이 완료되면 소멸
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    // bankName
    public void setAccount(int position){
        switch (position){
            case 1:
                bankName = "신한은행";
                break;
            case 2:
                bankName = "카카오뱅크";
                break;
            case 3:
                bankName = "우리은행";
                break;
            case 4:
                bankName = "농협";
                break;
        }
    }
}

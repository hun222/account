package com.hoonyeee.android.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hoonyeee.android.account.fingerprint.FingerprintAuthenticationDialogFragment;

public class InitActivity extends AppCompatActivity implements FingerprintAuthenticationDialogFragment.SecretAuthorize{

    private FingerprintAuthenticationDialogFragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //세팅부분
        mFragment = new FingerprintAuthenticationDialogFragment();
        mFragment.setCallback(this);
        mFragment.show(this.getFragmentManager(), "my_fragment");

    }


    @Override
    public void success() {
        Toast.makeText(this, "인증 성공", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void fail() {
        Toast.makeText(this, "인증 실패", Toast.LENGTH_SHORT).show();
    }
}

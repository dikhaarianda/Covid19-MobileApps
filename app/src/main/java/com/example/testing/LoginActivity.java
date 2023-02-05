package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.toolbox.Volley;
import com.example.testing.adapter.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton login;
    private TextView register;
    private EditText email;
    private EditText password;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.txt_regist);
        email = findViewById(R.id.txt_user);
        password = findViewById(R.id.txt_pass);
        loading = findViewById(R.id.loading);

        loading.setVisibility(View.GONE);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == login) {
            final String txtEmail = email.getText().toString().trim();
            final String txtPass = password.getText().toString().trim();

            if (txtEmail.isEmpty() || txtPass.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Input data belum lengkap", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 5) {
                Toast.makeText(LoginActivity.this, "Password harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
                Toast.makeText(LoginActivity.this, "Input email dengan benar", Toast.LENGTH_SHORT).show();
            } else {
                StringRequest stringRequest = stringRequest(txtEmail, txtPass);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(stringRequest);
            }
        } else {
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP & Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }

    private StringRequest stringRequest(String email, String password) {
        loading.setVisibility(View.VISIBLE);
        return new StringRequest(Request.Method.POST, URL.READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optString("success").equals("1")){
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                            Toast.makeText(LoginActivity.this, jsonObject.optString("message"), Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
    }
}
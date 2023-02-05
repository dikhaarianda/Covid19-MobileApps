package com.example.testing;

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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton register;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText retypePswrd;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.btn_regist);
        name = findViewById(R.id.txt_name);
        email = findViewById(R.id.txt_user);
        password = findViewById(R.id.txt_pass);
        retypePswrd = findViewById(R.id.txt_pass2);
        loading =findViewById(R.id.loading);

        loading.setVisibility(View.GONE);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String txtName = name.getText().toString().trim();
        final String txtEmail = email.getText().toString().trim();
        final String txtPass = password.getText().toString().trim();
        final String txtPass2 = retypePswrd.getText().toString().trim();

        if (txtName.isEmpty() || txtEmail.isEmpty() || txtPass.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Input data belum lengkap", Toast.LENGTH_SHORT).show();
        }
        else if (name.length() < 5 || password.length() < 5){
            Toast.makeText(RegisterActivity.this, "Nama & Password harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()){
            Toast.makeText(RegisterActivity.this, "Input type email dengan benar", Toast.LENGTH_SHORT).show();
        }
        else if (!txtPass.matches(txtPass2)){
            Toast.makeText(RegisterActivity.this, "Password tidak sesuai", Toast.LENGTH_SHORT).show();
        }
        else {
            StringRequest stringRequest = stringRequest(txtName, txtEmail, txtPass);
            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(stringRequest);
        }
    }

    private StringRequest stringRequest(String name, String email, String pswrd) {
        loading.setVisibility(View.VISIBLE);
        return new StringRequest(Request.Method.POST, URL.CREATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optString("success").equals("1")){
                                finish();
                            }
                            Toast.makeText(RegisterActivity.this, jsonObject.optString("message"), Toast.LENGTH_LONG).show();
                              loading.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name);
                params.put("email",email);
                params.put("password",pswrd);
                return params;
            }
        };
    }
}
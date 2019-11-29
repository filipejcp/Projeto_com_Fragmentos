package com.example.projeto_com_fragmentos;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.projeto_com_fragmentos.ui.main.PlaceholderFragment;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private CheckBox saveLoginCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final Boolean isloggedin=sharedPreferences.getBoolean("ISLOGGEDIN",false);

        if(isloggedin)
        {
            Intent main = new Intent(Login.this, MainActivity.class);
            startActivity(main);
        }

        final String required_password=sharedPreferences.getString("PASSWORD","DEFAULT_PASSWORD");

        final EditText name_field=(EditText)findViewById(R.id.login_name);
        final EditText password_field=(EditText)findViewById(R.id.login_password);
        Button login=(Button)findViewById(R.id.login_button);
        Button register=(Button)findViewById(R.id.register_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        final String nome=name_field.getText().toString();
        final String password=password_field.getText().toString();


                ////////////////////////////////////////////////////////////////////////////////////

                String url = "http://paroquiaprado.pt/pm21618/public/api/user/" + nome +"/" + password;

                // Formulate the request and handle the response.
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    //Manda os dados para o sharred preferences
                                    sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
                                    editor = sharedPreferences.edit();
                                    editor.putInt("IDUSER", response.getInt("id"));
                                    editor.putString("NAME", nome);
                                    editor.putString("PASSWORD", password);
                                    editor.putBoolean("ISLOGGEDIN", true);
                                    editor.commit();


                                    Intent main = new Intent(Login.this, MainActivity.class);
                                    startActivity(main);

                                }catch(JSONException ex){ }
                                //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Erro casao nao encontre resposta
                                Toast.makeText(Login.this, "Dados incorretos", Toast.LENGTH_SHORT).show();
                                //Log.d("Erro", error.toString());
                            }
                        });
                MySingleton.getInstance(Login.this).addToRequestQueue(jsObjRequest);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent register=new Intent(Login.this,Register.class);
                //register.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //startActivity(register);
            }
        });
    }


}
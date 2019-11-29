package com.example.projeto_com_fragmentos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.projeto_com_fragmentos.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Ver extends AppCompatActivity implements Serializable {

    Cursor cursor;
    String id;
    TextView et,et2,et3,et4,et5,et6,et7,et8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Declarar layout de ver
        setContentView(R.layout.ver);

        //Edittexts
        et   =  findViewById(R.id.nm);
        et2   = findViewById(R.id.ntm);
        et3   = findViewById(R.id.ntf);
        et4   = findViewById(R.id.em);
        et5   = findViewById(R.id.idad);
        et6   = findViewById(R.id.alt);
        et7   = findViewById(R.id.prof);
        et8   = findViewById(R.id.gen);

        //Para ir buscar o valor do contacto clicado
        id = getIntent().getExtras().getString("ver");
        //Preparar a base de dados
        Toast.makeText(this,id+"", Toast.LENGTH_SHORT).show();

        String url = "http://paroquiaprado.pt/pm21618/public/api/contatos/" + id;


        // Formulate the request and handle the response.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(Ver.this, response.toString(), Toast.LENGTH_SHORT).show();
                            et.setText(response.getString("nome"));
                            et2.setText(response.getString("ntelemovel"));
                            et3.setText(response.getString("ntelefone"));
                            et4.setText(response.getString("email"));
                            et5.setText(response.getString("idade"));
                            et7.setText(response.getString("altura"));
                            et8.setText(response.getString("genero"));
                        } catch (JSONException ex) {
                            //Toast.makeText(Ver.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Ver.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("Erro", error.toString());
                    }
                });
        MySingleton.getInstance(Ver.this).addToRequestQueue(jsObjRequest);
       }

    //Inserir o menu1
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trab1, menu);
        return true;
    }


    //Menu com o OK
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Itens clicados
        switch (item.getItemId()) {
            //Caso se clique no OK fecha a atividade
            case R.id.ok:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


    //Fechar as conexões no final da atividade



}

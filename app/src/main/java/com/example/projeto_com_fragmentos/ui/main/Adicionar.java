package com.example.projeto_com_fragmentos.ui.main;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projeto_com_fragmentos.R;
import com.example.projeto_com_fragmentos.entities.Contacto;
import java.util.Calendar;

import java.io.Serializable;

public class Adicionar extends AppCompatActivity {
    SharedPreferences sharedPreferences;



    //Declarar campos de editText
    EditText et,et2,et3,et4,et5,et6,et7,et8;
    int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Ao criar o objeto, é feito um cast para string
        //e os valores guardados nas variáveis temporárias
        //É feito para o código ficar mais limpo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar);


        et   = (EditText)findViewById(R.id.nm);
        et2   = (EditText)findViewById(R.id.ntm);
        et3   = (EditText)findViewById(R.id.ntf);
        et4   = (EditText)findViewById(R.id.em);
        et5   = (EditText)findViewById(R.id.idad);
        et6   = (EditText)findViewById(R.id.alt);
        et7   = (EditText)findViewById(R.id.prof);
        et8   = (EditText)findViewById(R.id.gen);




    }

    //Criar um contacto novo com os campos guardados anteriormentes nas variáveis temporárias
    public void newContacto(){
        ContentValues cv = new ContentValues();
        int b=0,c=0;
        float d=0;

        if((!et3.getText().toString().isEmpty())){
            b = Integer.parseInt(et3.getText().toString());
        }
        if((!et6.getText().toString().isEmpty())){
            d = Float.parseFloat(et6.getText().toString());
        }






    }


    //Botão de adicionar
    public void btnAdicionar(View v){

        //impedir que o utilizador insira contactos "vazios". Obriga o utilizador a inserir um número e o nome
        if( et2.getText().toString().equals("") || et.getText().toString().equals(""))
        {
            Toast.makeText(this, getResources().getString(R.string.CO), Toast.LENGTH_SHORT).show();
        }
        else {
            newContacto();
            finish();
        }
    }

    //End of class


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trab1, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ok:
                if( et2.getText().toString().equals("") || et.getText().toString().equals(""))
                {
                    Toast.makeText(this, getResources().getString(R.string.CO), Toast.LENGTH_SHORT).show();
                }
                else {
                    newContacto();
                    finish();
                }

                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


}


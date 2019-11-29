package com.example.projeto_com_fragmentos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projeto_com_fragmentos.R;

import java.util.Calendar;

public class Editar extends AppCompatActivity {
    //Selecionar a base de dados

    //Declarar Textbox's
    EditText et,et2,et3,et4,et5,et6,et7,et8;
    //Cursor para realizar queries na base de dados
    Cursor cursor;
    int id;

    final Calendar myCalendar = Calendar.getInstance();

    //Ao chamar o editor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Selecionar o layout de editar
        setContentView(R.layout.editar);
        //Ir buscar o conteudo das TextBox
        et   = (EditText)findViewById(R.id.nm);
        et2   = (EditText)findViewById(R.id.ntm);
        et3   = (EditText)findViewById(R.id.ntf);
        et4   = (EditText)findViewById(R.id.em);
        et5   = (EditText)findViewById(R.id.idad);
        et6   = (EditText)findViewById(R.id.alt);
        et7   = (EditText)findViewById(R.id.prof);
        et8 = (EditText)findViewById(R.id.gen);
        //id do contacto clicado (escolhido para editar)
        id = getIntent().getExtras().getInt("editar");

    }


    //Criar o menu dos 3 pontinhos, onde vai ter o OK para guardar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trab1, menu);
        return true;
    }



    //Ações do menu de opções, o que cada opção faz
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ok:
                //Se os campos de número de telefone e do nome estiverem vazios dá erro
                //Nenhum contacato deve ser criado sem estes 2 campos
                if( et2.getText().toString().equals("") || et.getText().toString().equals(""))
                {
                    Toast.makeText(this, getResources().getString(R.string.CO), Toast.LENGTH_SHORT).show();
                }
                //Caso tenha os campos com valores, é chamado o método de inserção
                else {
                    updateInDB(id);
                    finish();
                }

                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateInDB(int id){
        ContentValues cv = new ContentValues();
        int b=0;
        float d=0;

        //Se a caixa estiver vazia mete o zero e converte para string
        if((!et3.getText().toString().isEmpty())){
            b = Integer.parseInt(et3.getText().toString());
        }
        if((!et6.getText().toString().isEmpty())){
            d = Float.parseFloat(et6.getText().toString());
        }



    }

    //Fechar as conexões no final da atividade



}


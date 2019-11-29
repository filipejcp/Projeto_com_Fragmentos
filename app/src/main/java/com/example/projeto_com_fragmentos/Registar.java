package com.example.projeto_com_fragmentos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Registar extends AppCompatActivity {
    Intent next_activity;

    SQLiteDatabase db;
    String nameBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Preparar os valores para a base de dados
        final ContentValues cv = new ContentValues();

        //Próxima atividade, será a atividade de login
        next_activity=new Intent(this, Login.class);

        //Ir buscar os valores dos campos
        final EditText name_field=(EditText)findViewById(R.id.Name);
        final EditText password_field=(EditText)findViewById(R.id.Password);
        final EditText confirm_password_field=(EditText)findViewById(R.id.confirm_password);
        //Botão de registar
        Button registerbutton=(Button)findViewById(R.id.register);

        //Botão de Registar
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Converte o nome e as passwords para string
                //Caso forem inseridos só numeros e não forem convertidos, o programa crasha
                String name=name_field.getText().toString();
                String password_1 = password_field.getText().toString();
                String password_2 = confirm_password_field.getText().toString();

                //Se a password 1 for = à password 2, pode realizar a inserção
                if (password_1.equals(password_2)) {

                    //Iniciar a próxima atividade, neste caso, a atividade de login
                    startActivity(next_activity);
                } else {
                    //Caso as passwords não coincidam dá erro
                    Toast.makeText(Registar.this, "Passwords não correspondem", Toast.LENGTH_LONG).show();
                }
            }

        });
    }



}

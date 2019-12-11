package com.example.projeto_com_fragmentos.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.projeto_com_fragmentos.Editar;
import com.example.projeto_com_fragmentos.Login;
import com.example.projeto_com_fragmentos.MySingleton;
import com.example.projeto_com_fragmentos.R;
import com.example.projeto_com_fragmentos.Ver;
import com.example.projeto_com_fragmentos.adapters.CustomArrayAdapter;
import com.example.projeto_com_fragmentos.entities.Contacto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements SensorEventListener {


    ArrayList<Contacto> c = new ArrayList<>();
    SharedPreferences sharedPreferences;

    String user_name;
    int id_user;

    View root;
    ListView listView;



    //Preparar o sensor
    private SensorManager mSensorManager;
    private Sensor mProximity;
    //Sensibilidade do sensor
    private static final int SENSOR_SENSITIVITY = 4;


    //----------------------------------------------------------------------
    //
    //              CLASSE JAVA DO FRAGMENTO INICIAL
    //              ONDE APARECE A LISTA
    //
    //----------------------------------------------------------------------

    //Declarar um array de contactos -> devia-se chamar contactoS em vez de contacto, pois armazena vários

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    //Declarar o o fragemento
    public static PlaceholderFragment newInstance() {
        PlaceholderFragment fragment = new PlaceholderFragment();
        return fragment;
    }



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_main, container, false);

        registerForContextMenu((ListView) root.findViewById(R.id.lista));
        super.onCreateView(inflater, container, savedInstanceState);

        //Iniciar o sensor
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //Pesquisar o id e nome do user no sharedpreferences
        sharedPreferences=getActivity().getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        user_name=sharedPreferences.getString("NAME","DEFAULT_NAME");
        id_user = sharedPreferences.getInt("IDUSER", -1);





        //registerForContextMenu((ListView) root.findViewById(R.id.lista));
        listView = (ListView) root.findViewById(R.id.lista);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ação do botão de adicionar
                Intent intent = new Intent(getActivity(), Adicionar.class);

                // Espera pelo resultado
                startActivity(intent);
            }
        });



        //-------------------------------------------------------
        // apenas um clique, para ver!!!
        //-------------------------------------------------------
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Chamar a view de ver e passar o id do contacto
                Contacto cont = c.get(position);
                String id_contato = cont.getId();
                //Toast.makeText(getActivity(), id_contato.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Ver.class);
                intent.putExtra("ver", id_contato);
                startActivity(intent);
            }
        });



        //------------------------------------------------------
        //Por fim, retorna a atividade raíz
        return root;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Dizer que tem um menu
        setHasOptionsMenu(true);



    }


    //Quando a atividade é retomada, é necessário atualizar a lista, com possíveis alterações
    @Override
    public void onResume() {
        super.onResume();

        //Sensor
        //mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);

        //Fazder listagem principal

        if(!c.isEmpty()) {
            c.clear();
        }
        String url = "http://172.16.184.229/PM/contatos/public/api/contatos";


        Toast.makeText(getActivity(), "Teste", Toast.LENGTH_SHORT).show();
        // Formulate the request and handle the response.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr = response.getJSONArray("DATA");
                            //Percorre o array e pega nos valores pretendidos
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                //Cria pessoa
                                Contacto temp = new Contacto(obj.getString("id"), obj.getString("nome"), obj.getString("ntelemovel"), obj.getString("ntelefone"), obj.getString("email"),
                                        obj.getString("idade"), obj.getString("altura"), obj.getString("genero"));

                                c.add(temp);  //adiciona pessoa ao array
                            }
                            CustomArrayAdapter itemsAdapter = new CustomArrayAdapter(getActivity(), c);
                            ((ListView) getView().findViewById(R.id.lista)).setAdapter(itemsAdapter);
                            itemsAdapter.notifyDataSetChanged();
                        } catch (JSONException ex) {
                        }
                        //Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("Erro", error.toString());
                    }
                });
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsObjRequest);
    }

    @Override
    public void onPause() {
        super.onPause();
    }




    //Ação de criação do menu contextual de edições
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_cont_1, menu);
    }

    //Ação de seleção do item do menu contextual (longclick)
    //onContextItem para definir as funções de cada opção selecionada do menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        final Contacto p = c.get(index);
        String id_contato = p.getId();

        switch (item.getItemId()) {

            case R.id.edit:
                Intent editar = new Intent(getActivity(), Editar.class);
                editar.putExtra("editar", id_contato);
                startActivity(editar);
                return true;
            case R.id.remove:
                //Remover user
                Toast.makeText(getActivity(), id_contato+"", Toast.LENGTH_SHORT).show();
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.DELETE, "http://172.16.184.229/PM/contatos/public/api/contatos"+id_contato, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getActivity(), getResources().getString(R.string.aremove) , Toast.LENGTH_SHORT).show();
                                onResume();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                MySingleton.getInstance(getActivity()).addToRequestQueue(jsObjRequest);

                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
    //---------------------------------------------------------------------------------
    //-> DAQUI PARA BAIXO, PASSAR O TEXTO PARA STRINGS
    //---------------------------------------------------------------------------------


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    //Menu 3 pontinhos e dos filtros
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                //Criar um novo login
                Intent login=new Intent(getActivity(), Login.class);
                final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
                //Passar a sharedpreference ISLOGGEDIN para false, para terminar a sessão
                sharedPreferences.edit().putBoolean("ISLOGGEDIN", false).commit();
                //Ir para o login
                startActivity(login);
                //Terminar a atividade atual
                getActivity().finish();

                break;
            case R.id.action_sort:

                break;
            case R.id.orgAZ:


                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //Sensor!!!

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if( event.sensor.getType() == Sensor.TYPE_PROXIMITY)
        {
            //Toast.makeText(getActivity(), ""+event.values[0],Toast.LENGTH_SHORT).show();
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY){
                //Toast.makeText(getActivity(), "near", Toast.LENGTH_SHORT).show(); <- Toast para ver se está próximo ou longe


            }else {
                //Toast.makeText(getActivity(), "far", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Fechar as conexões no final da atividade



}




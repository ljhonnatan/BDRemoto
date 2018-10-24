package com.example.ljhonnatan.bdremoto;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljhonnatan.bdremoto.logica.medico;
import com.example.ljhonnatan.bdremoto.logica.planta;
import com.example.ljhonnatan.bdremoto.logica.producto;
import com.example.ljhonnatan.bdremoto.logica.sesion;
import com.example.ljhonnatan.bdremoto.util.Funciones;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class pedidoregistrar1 extends AppCompatActivity implements View.OnClickListener{

    EditText txtDni;
    TextView txtNroPed;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidoregistrar1);

        txtDni = (EditText)findViewById(R.id.txtDni);
        txtNroPed= (TextView) findViewById(R.id.txtNroPed);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean r = Funciones.mensajeConfirmacion(this, "Confirme", "Desea registrar la cita");
        if (r){
            String dni = txtDni.getText().toString();

            JSONArray jsonArrayDetalle = new JSONArray();
            for (int i = 0; i< pedidoAdapter.listaDatos.size();i++){
                //medico item = pedidoAdapter.listaDatos.get(i);
                planta item = pedidoAdapter.listaDatos.get(i);
                 String.valueOf(item.getCodigoPlanta()) ;
                    jsonArrayDetalle.put(item.getJSONItemDetalle());
            }
            String detallePedido = jsonArrayDetalle.toString();
            Log.e("Detalle Cita", detallePedido);

            new RegistrarPedidoTask(dni,detallePedido).execute();

        }
    }

    public class RegistrarPedidoTask extends AsyncTask<Void, Void, String> {
        private String dni;
        private String detalle;

        public RegistrarPedidoTask(String dni, String detalle) {
            this.dni = dni;
            this.detalle = detalle;
        }

        @Override
        protected String doInBackground(Void... params) {
            // LLamar a la WS para registrar el pedido
            try {
                String ws = Funciones.URL_WS + "planta.registrar.php";
                HashMap parametros = new HashMap<String, String>();
                parametros.put("token", sesion.token);
                parametros.put("dni", this.dni);
                parametros.put("det_cit", this.detalle);

                String resultado = new Funciones().getHttpContent(ws, parametros);
                return resultado;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "";
            }
        }


        protected void onPostExecute(final String resultado) {
            if ( ! resultado.isEmpty() ) {
                try {
                    JSONObject json = new JSONObject(resultado);

                    int estado = json.getInt("estado");
                    if (estado==200) {
                        JSONObject jsonDatos = json.getJSONObject("datos");
                        txtNroPed.setText(String.valueOf(jsonDatos.getInt("nc")));
                        Funciones.mensajeInformacion(pedidoregistrar1.this, "cita", "Registrado correctamente");
                        pedidoregistrar1.this.finish();


                    }else{
                        Log.e("Error", json.getString("mensaje"));
                    }
                }catch (Exception e){
                    Toast.makeText(pedidoregistrar1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}

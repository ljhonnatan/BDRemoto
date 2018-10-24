package com.example.ljhonnatan.bdremoto.logica;

import android.util.Log;

//import com.example.ljhonnatan.bdremoto.Datos.AccesoDatos;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ljhonnatan on 10/10/2018.
 */

public class planta{

    private int codigoPlanta;
    private String nombre;
    private String fecha;
    private String foto;
    private String hora;
    private String descripcion;

    public int getCodigoPlanta() {
        return codigoPlanta;
    }

    public void setCodigoPlanta(int codigoPlanta) {
        this.codigoPlanta = codigoPlanta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public JSONObject getJSONItemDetalle(){
        JSONObject objItemDetalle = new JSONObject();
        try {
            objItemDetalle.put("codigo_planta",getCodigoPlanta());
            //objItemDetalle.put("horario",getHorario());
        }catch (JSONException e){
            Log.e("Error", e.getMessage());
        }
        return objItemDetalle;
    }
}

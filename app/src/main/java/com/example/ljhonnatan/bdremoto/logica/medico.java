package com.example.ljhonnatan.bdremoto.logica;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ljhonnatan on 10/07/2017.
 */

public class medico {
    private int codigoMedico;
    private String nombre;
    private String especialidad;
    private String foto;
    private String horario;

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public JSONObject getJSONItemDetalle(){
        JSONObject objItemDetalle = new JSONObject();
        try {
            objItemDetalle.put("codigo_medico",getCodigoMedico());
            //objItemDetalle.put("horario",getHorario());
        }catch (JSONException e){
            Log.e("Error", e.getMessage());
        }
        return objItemDetalle;
    }
}

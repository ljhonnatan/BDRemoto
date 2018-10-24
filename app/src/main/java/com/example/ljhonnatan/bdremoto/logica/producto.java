package com.example.ljhonnatan.bdremoto.logica;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ljhonnatan on 22/06/2017.
 */

public class producto {

    private int codigoProducto;
    private String nombre;
    private double precioVenta;
    private String foto;
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static ArrayList<producto> listaProd= new ArrayList<producto>();

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public JSONObject getJSONItemDetalle(){
        JSONObject objItemDetalle = new JSONObject();
        try {
            objItemDetalle.put("codigo_producto",getCodigoProducto());
            objItemDetalle.put("cantidad",getCantidad());
        }catch (JSONException e){
            Log.e("Error", e.getMessage());
        }
        return objItemDetalle;
    }
}

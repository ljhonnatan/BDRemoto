package com.example.ljhonnatan.bdremoto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ljhonnatan.bdremoto.logica.medico;
import com.example.ljhonnatan.bdremoto.logica.planta;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ljhonnatan on 22/06/2017.
 */

public class pedidoAdapter extends BaseAdapter {

    //public static ArrayList<medico> listaDatos;
    public static ArrayList<planta> listaDatos;
    private LayoutInflater layoutInflater;
    private DisplayImageOptions options;

    private Context ncontext;

    private ImageLoadingListener animateFirstListener
            = new AnimateFirstDisplayListener();


    public pedidoAdapter(Context context, ArrayList<planta> listaDatos) {
        this.listaDatos = listaDatos;
        layoutInflater = LayoutInflater.from(context);

        ncontext = context;

        /*Sirve para configurar la carga de las fotos de los articulos*/
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                //.displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
                .displayer(new FadeInBitmapDisplayer(1500))
                .build();
        /*Sirve para configurar la carga de las fotos de los articulos*/

    }

    @Override
    public int getCount() {
        return listaDatos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setListaDatos(ArrayList<planta> mlistaDatos) {
        this.listaDatos = mlistaDatos;
        notifyDataSetChanged(); /*Notificar a la aplicación que se ha descargado nuevos datos*/
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_producto, null);
            holder = new Holder();
            holder.foto = (ImageView) convertView.findViewById(R.id.imgProducto);
            holder.nombre = (TextView) convertView.findViewById(R.id.lblNombre);
            holder.codigo = (TextView) convertView.findViewById(R.id.lblCodigo);

            holder.fecha = (TextView) convertView.findViewById(R.id.lblFech);
            //holder.especialidad = (TextView) convertView.findViewById(R.id.lblEspecialidad);
            //holder.precio = (TextView) convertView.findViewById(R.id.lblPrecio);
            //holder.horario = (TextView) convertView.findViewById(R.id.lblHorario);
            holder.hora = (TextView) convertView.findViewById(R.id.lblHora);
            holder.descripcion = (TextView) convertView.findViewById(R.id.lblDescripcion);
            holder.a1 = (Button) convertView.findViewById(R.id.btnDescripcion);
            //holder.a1 = (Button) convertView.findViewById(R.id.btnReservar);

            //holder.menos = (Button) convertView.findViewById(R.id.btnMenos);
            //holder.mas = (Button) convertView.findViewById(R.id.btnMas);
            //holder.cantidad = (EditText) convertView.findViewById(R.id.txtCantidad);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        planta item = listaDatos.get(position);
        //holder.codigo.setText("Código: " + String.valueOf(item.getCodigoMedico()));
        holder.codigo.setText("Código: " + String.valueOf(item.getCodigoPlanta()));
        holder.nombre.setText(item.getNombre());
        holder.fecha.setText("Fecha: " + item.getFecha());
        holder.hora.setText("Hora: " + item.getHora());
        //holder.especialidad.setText("Especialidad: " + item.getEspecialidad());
        //holder.horario.setText("horario: " + item.getHorario());
        holder.descripcion.setText("Descripcion: " + item.getDescripcion());

        //holder.precio.setText("S/. " + Funciones.formatearNumero(item.get));
        //holder.cantidad.setText( String.valueOf( item.getCantidad() ) );


        if ( item.getFoto().equalsIgnoreCase("none")) {
            holder.foto.setImageResource(R.drawable.ic_empty);
        }else{
            ImageLoader.getInstance().displayImage(item.getFoto(), holder.foto, options, animateFirstListener);

        }
        holder.a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//AQUI SE ESCRIBE EL CODIGO QUE FALTA
                Intent i = new Intent(ncontext,pedidoregistrar1.class);
                ncontext.startActivity(i);
                //pedidoAdapter.this.

            }
        });


/*
        holder.mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = Integer.parseInt( holder.cantidad.getText().toString());
                cantidad = cantidad + 1;
               // listaDatos.get(position).setCantidad(cantidad);
                holder.cantidad.setText(String.valueOf(cantidad));
            }
        });

        holder.menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = Integer.parseInt( holder.cantidad.getText().toString());
                cantidad = cantidad - 1;
                if (cantidad <= 0){
                    cantidad = 0;
                }
               // listaDatos.get(position).setCantidad(cantidad);
                holder.cantidad.setText(String.valueOf(cantidad));
            }
        });
/*/
        return convertView;
    }

    static class Holder{
        ImageView foto;
        TextView nombre;
        TextView fecha;
        TextView hora;

        TextView codigo;
        TextView descripcion;
        //TextView precio;
        //TextView especialidad;
        //TextView horario;

        Button a1;

        Button menos;
        Button mas;
        EditText cantidad;


    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}

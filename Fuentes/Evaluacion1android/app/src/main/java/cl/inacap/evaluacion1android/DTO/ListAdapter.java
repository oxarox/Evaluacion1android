package cl.inacap.evaluacion1android.DTO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.inacap.evaluacion1android.R;

public class ListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Locale espanol = new Locale("es","ES");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",espanol);
    NumberFormat formato = NumberFormat.getNumberInstance(espanol);

    Context context;
    List<Concierto> concierto = new ArrayList<>();
    int[] calificacionImg;
    int[] generoMusicalmg;

    public ListAdapter(Context context, List<Concierto> concierto, int[] calificacionImg, int[] generoMusicalmg) {
        this.context = context;
        this.concierto.addAll(concierto);
        this.calificacionImg = calificacionImg;
        this.generoMusicalmg = generoMusicalmg;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista,null);
        TextView generoMusica = (TextView) vista.findViewById(R.id.tvGeneroMusical);
        TextView nombreArtista = (TextView) vista.findViewById(R.id.tvNombreArtista);
        TextView valorEntrada = (TextView) vista.findViewById(R.id.tvValorEntrada);
        TextView fechaEvento = (TextView) vista.findViewById(R.id.tvFechaEvento);
        ImageView imgGeneroMusica = (ImageView) vista.findViewById(R.id.imgGeneroMusical);
        ImageView imgCalificacion = (ImageView) vista.findViewById(R.id.imgCalificacion);

        try {
            generoMusica.setText(concierto.get(i).getGeneroMusical());
            nombreArtista.setText(concierto.get(i).getNombreArtista());
            valorEntrada.setText(formato.format(concierto.get(i).getValorEntrada()));
            fechaEvento.setText(sdf.format(concierto.get(i).getFechaEvento()));
            switch (concierto.get(i).getCalificacion()) {
                case 1:
                case 2:
                case 3:
                    imgCalificacion.setImageResource(calificacionImg[0]);
                    imgCalificacion.setTag(0);
                    break;
                case 4:
                case 5:
                    imgCalificacion.setImageResource(calificacionImg[1]);
                    imgCalificacion.setTag(1);
                    break;
                case 6:
                case 7:
                    imgCalificacion.setImageResource(calificacionImg[2]);
                    imgCalificacion.setTag(2);
                    break;
            }
            switch (concierto.get(i).getGeneroMusical()) {
                case "Rock":
                    imgGeneroMusica.setImageResource(generoMusicalmg[0]);
                    imgGeneroMusica.setTag(0);
                    break;
                case "Jazz":
                    imgGeneroMusica.setImageResource(generoMusicalmg[1]);
                    imgGeneroMusica.setTag(1);
                    break;
                case "Pop":
                    imgGeneroMusica.setImageResource(generoMusicalmg[2]);
                    imgGeneroMusica.setTag(2);
                    break;
                case "Reguetoon":
                    imgGeneroMusica.setImageResource(generoMusicalmg[3]);
                    imgGeneroMusica.setTag(3);
                    break;
                case "Salsa":
                    imgGeneroMusica.setImageResource(generoMusicalmg[4]);
                    imgGeneroMusica.setTag(4);
                    break;
                case "Metal":
                    imgGeneroMusica.setImageResource(generoMusicalmg[5]);
                    imgGeneroMusica.setTag(5);
                    break;
            }
        }catch (IndexOutOfBoundsException ex){

        }


        return vista;
    }

    @Override
    public int getCount() {
        return calificacionImg.length;
    }
    public int getCount2() {
        return generoMusicalmg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}

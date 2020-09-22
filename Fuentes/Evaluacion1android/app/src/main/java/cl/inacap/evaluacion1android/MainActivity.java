package cl.inacap.evaluacion1android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.inacap.evaluacion1android.DTO.Concierto;
import cl.inacap.evaluacion1android.DTO.ListAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Concierto> conciertos = new ArrayList<>();
    private ArrayAdapter<Concierto> conciertoAdapter;
    private final static String[] listaGenerosMusicales = {"Seleccione", "Rock", "Jazz", "Pop", "Reguetoon", "Salsa", "Metal"};
    private final static Integer[] listaCalificaciones = {0, 1, 2, 3, 4, 5, 6, 7};
    private ArrayAdapter<String> adapterListaGenerosMusicales;
    private ArrayAdapter<Integer> adapterListaCalificaciones;
    private EditText etPlannedDate;
    private EditText etNombreArtista;
    private EditText etValorEntrada;
    private Calendar myCalendar = Calendar.getInstance();
    private Spinner spGeneroMusical;
    private Spinner spCalificacion;
    private Button btnRegistrar;
    private Button btnLimpiar;
    private ListView lViewPersonalizada;
    private static int[] imaCalificaciones = {R.drawable.bronze, R.drawable.gold,R.drawable.diamond};
    private static int[] imaGeneroMusica = {R.drawable.rock,R.drawable.jazz, R.drawable.pop, R.drawable.reggae, R.drawable.latin, R.drawable.metal};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conciertoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conciertos);
        adapterListaGenerosMusicales = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaGenerosMusicales);
        adapterListaCalificaciones = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, listaCalificaciones);
        this.etPlannedDate = findViewById(R.id.etPlannedDate);
        this.etNombreArtista = findViewById(R.id.etNombreArtista);
        this.etValorEntrada = findViewById(R.id.etValorEntrada);
        this.spGeneroMusical = findViewById(R.id.spGeneroMusical);
        this.spCalificacion = findViewById(R.id.spCalificacion);
        this.btnRegistrar = findViewById(R.id.btnRegistrar);
        this.btnLimpiar = findViewById(R.id.btnLimpiar);
        this.lViewPersonalizada = findViewById(R.id.lvListaConcientos);
        spGeneroMusical.setAdapter(adapterListaGenerosMusicales);
        spCalificacion.setAdapter(adapterListaCalificaciones);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNombreArtista.setText("");
                etPlannedDate.setText("");
                spGeneroMusical.setSelection(0);
                etValorEntrada.setText("");
                spCalificacion.setSelection(0);
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                int valor = 0, dia = 0, mes = 0, anno = 0;
                try {
                    if (etNombreArtista.getText().toString().isEmpty()) {
                        errores.add("Debe escribir un nombre de artista");
                    }
                    if (etPlannedDate.getText().toString().isEmpty()) {
                        errores.add("Debe señalar una fecha para el evento");
                    } else {
                        dia = Integer.parseInt(etPlannedDate.getText().toString().substring(0, 2));
                        mes = Integer.parseInt(etPlannedDate.getText().toString().substring(3, 5));
                        anno = Integer.parseInt(etPlannedDate.getText().toString().substring(6, 10));
                    }
                    if (spGeneroMusical.getSelectedItemPosition() == 0) {
                        errores.add("Debe elegir un genero musical");
                    }
                    if (spCalificacion.getSelectedItemPosition() == 0) {
                        errores.add("Debe elegir una calificacion mayor a 0");
                    }
                    valor = Integer.parseInt(etValorEntrada.getText().toString());
                    if (valor < 1) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    errores.add("Debe ingresar valor de la entrada");
                }
                if (errores.isEmpty()) {
                    Concierto c = new Concierto();
                    c.setNombreArtista(etNombreArtista.getText().toString());
                    c.setFechaEvento(new Date(anno, mes, dia));
                    c.setGeneroMusical(listaGenerosMusicales[spGeneroMusical.getSelectedItemPosition()]);
                    c.setValorEntrada(valor);
                    c.setCalificacion(listaCalificaciones[spCalificacion.getSelectedItemPosition()]);
                    conciertos.add(c);
                    conciertoAdapter.notifyDataSetChanged();
                    mostrarConcierto();
                } else {
                    mostrarErrores(errores);
                }
            }
        });
        lViewPersonalizada.setAdapter(new ListAdapter(this,conciertos,imaCalificaciones,imaGeneroMusica));
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }
    };

    private void actualizarInput() {
        String formatoDeFecha = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
        etPlannedDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void mostrarConcierto() {

    }

    private void mostrarErrores(List<String> errores) {
        String mensaje = "";
        for (String e : errores) {
            mensaje += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error de validacion")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();
    }
}
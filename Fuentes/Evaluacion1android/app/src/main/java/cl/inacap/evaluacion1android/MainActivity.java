package cl.inacap.evaluacion1android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cl.inacap.evaluacion1android.DTO.Concierto;

public class MainActivity extends AppCompatActivity {

    private List<Concierto> conciertos = new ArrayList<>();
    private final static String[] listaGenerosMusicales = {"Seleccione","Rock","Jazz","Pop","Reguetoon","Salsa","Metal"};
    private final static Integer[] listaCalificaciones = {0,1,2,3,4,5,6,7};
    private ArrayAdapter<Concierto> conciertoAdapter;
    private ArrayAdapter<String> adapterListaGenerosMusicales;
    private ArrayAdapter<Integer> adapterListaCalificaciones;
    private EditText etPlannedDate;
    private Calendar myCalendar = Calendar.getInstance();
    private EditText etNombreArtista;
    private EditText etValorEntrada;
    private Spinner spGeneroMusical;
    private Spinner spCalificacion;
    private Button btnRegistrar;
    private Button btnLimpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.conciertoAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,conciertos);
        adapterListaGenerosMusicales = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaGenerosMusicales);
        adapterListaCalificaciones = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,listaCalificaciones);
        this.etPlannedDate = findViewById(R.id.etPlannedDate);
        this.etNombreArtista = findViewById(R.id.etNombreArtista);
        this.etValorEntrada = findViewById(R.id.etValorEntrada);
        this.btnRegistrar =findViewById(R.id.btnRegistrar);
        this.btnRegistrar = findViewById(R.id.btnRegistrar);
        this.btnLimpiar = findViewById(R.id.btnLimpiar);
        spGeneroMusical = findViewById(R.id.spGeneroMusical);
        spCalificacion = findViewById(R.id.spCalificacion);
        spGeneroMusical.setAdapter(adapterListaGenerosMusicales);
        spCalificacion.setAdapter(adapterListaCalificaciones);
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
                int valor = 0;
                try {
                    if (etNombreArtista.getText().toString().isEmpty()){
                        errores.add("Debe escribir un nombre de artista");
                    }
                    if (etPlannedDate.getText().toString().isEmpty()){
                        errores.add("Debe señalar una fecha para el evento");
                    }
                    if (spGeneroMusical.getSelectedItemPosition() == 0){
                        errores.add("Debe elegir un genero musical");
                    }
                    if (spCalificacion.getSelectedItemPosition() == 0){
                        errores.add("Debe elegir una calificacion mayor a 0");
                    }
                    valor = Integer.parseInt(etValorEntrada.getText().toString());
                    if (valor > 0){
                        throw  new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("Debe ingresar valor de la entrada");
                }



                if (errores.isEmpty()){

                }else{
                    mostrarErrores(errores);
                }
            }
        });
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }
    };

    private void actualizarInput() {
        String formatoDeFecha = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
        etPlannedDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void mostrarErrores(List<String> errores){
        String mensaje = "";
        for (String e : errores){
            mensaje += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error de validacion")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",null)
                .create()
                .show();
    }
}
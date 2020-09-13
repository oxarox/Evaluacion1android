package cl.inacap.evaluacion1android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.evaluacion1android.DTO.Concierto;

public class MainActivity extends AppCompatActivity {

    private List<Concierto> conciertos = new ArrayList<>();
    private final static String[] listaGenerosMusicales = {"Rock","Jazz","Pop","Reguetoon","Salsa","Metal"};
    private final static Integer[] listaCalificaciones = {1,2,3,4,5,6,7};
    private ArrayAdapter<Concierto> conciertoAdapter;
    private ArrayAdapter<String> adapterListaGenerosMusicales;
    private ArrayAdapter<Integer> adapterListaCalificaciones;
    private EditText etPlannedDate;
    private EditText etNombreArtista;
    private EditText etValorEntrada;
    private Spinner spGeneroMusical;
    private Spinner spCalificacion;


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
        etPlannedDate.setOnClickListener((View.OnClickListener) this);
        spGeneroMusical = findViewById(R.id.spGeneroMusical);
        spCalificacion = findViewById(R.id.spCalificacion);
        spGeneroMusical.setAdapter(adapterListaGenerosMusicales);
        spCalificacion.setAdapter(adapterListaCalificaciones);
        /*spGeneroMusical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spCalificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }

}
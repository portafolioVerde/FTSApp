package pv.portafolioverde.ftsapp;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    EditText etNactividad,etPresupuesto,etNproyecto;
    Button guardarA;
    TextView tvFecha;
    RadioButton rbAvance;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        rbAvance = findViewById(R.id.rbAvance);
        etNproyecto = findViewById(R.id.et_nproyecto);
        etNactividad = findViewById(R.id.et_nactividad);
        etPresupuesto = findViewById(R.id.et_presupuesto);
        guardarA = findViewById(R.id.btnGuardarA);
        guardarA.setOnClickListener(this);
        tvFecha = findViewById(R.id.btnFechaNA);
        tvFecha.setOnClickListener(this);
        //traerNombreUsuario();
    }

    private void validarDatos() {
        if (TextUtils.isEmpty(etNactividad.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(etNproyecto.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(rbAvance.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(etPresupuesto.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(tvFecha.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else {
            guardarActividad();
            Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
            startActivity(intent);
        }
    }

    private void traerNombreUsuario() {
        //String extraName = getIntent().getExtras().getString("tvName");
        //userNameText.setText(extraName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFechaNA:

                int dia, mes,ano;
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int  year, int monthOfYear, int dayOfMonth) {
                        tvFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,dia,mes,ano);
                datePickerDialog.show();

                break;
            case R.id.btnGuardarA:
                /*Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","andreshcp@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Android APP - ");
                startActivity(Intent.createChooser(emailIntent,  getApplicationContext().getString(R.string.enviar_mail)));*/
                validarDatos();
                break;
        }
    }

    private void guardarActividad() {
        String extraName = getIntent().getExtras().getString("tvName");
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DataLegalizaciones").child("Actividades");
        String newKey = myRef.push().getKey();

        Map<String, Object> activ = new HashMap<>();
        activ.put("idAct",newKey);
        activ.put("usuario",extraName);
        activ.put("nombreActividad",etNactividad.getText().toString());
        activ.put("numeroProjecto",etNproyecto.getText().toString());
        activ.put("tipoActividad",rbAvance.getText().toString());
        activ.put("valorPresupuesto",etPresupuesto.getText().toString());
        activ.put("fechaActividad",tvFecha.getText().toString());
        activ.put("estado","pendiente");
        myRef.child(newKey).setValue(activ);

    }

}

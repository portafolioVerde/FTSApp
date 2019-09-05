package pv.portafolioverde.ftsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity6 extends AppCompatActivity implements View.OnClickListener {

    private static final String [] razonSocial= new String[]{"FundaciónPortafolioVerde","PortafolioVerde SAS"};
    TextView textTitle,nameUser,textView_tip;
    ImageView imageView;
    ImageButton btnAtras_6,btnSig_6;
    EditText etProv,etNit,etLugar;
    Button btnFecha;
    AutoCompleteTextView etRazon;

    final Handler handler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        etRazon = findViewById(R.id.et_razon);
        completeRazon(); //Llenar Razon social AutoComplete
        textTitle = (TextView)findViewById(R.id.textTitle_07);
        imageView = (ImageView)findViewById(R.id.imageViewLeft);

        nameUser = findViewById(R.id.tv_nameUser);
        textView_tip = findViewById(R.id.textView_tip0106);
        etProv = findViewById(R.id.et_prov);
        etNit = findViewById(R.id.et_nit);
        btnFecha = findViewById(R.id.btn_fecha);
        btnFecha.setOnClickListener(this);
        etLugar = findViewById(R.id.et_lugar);

        btnAtras_6 = (ImageButton) findViewById(R.id.btnAtras6);
        btnAtras_6.setOnClickListener(this);
        btnSig_6 = (ImageButton)findViewById(R.id.btnSiguiente6);
        btnSig_6.setOnClickListener(this);
        traerSharedPreferences_01();

        //ejecutar_01();
        //ejecutar_02();

    }

    private void traerSharedPreferences_01() {
        String extrasTitle = getIntent().getExtras().getString("titleText");
        String extrasName = getIntent().getExtras().getString("nameUser");
        nameUser.setText(extrasName);
        textTitle.setText(extrasTitle);

        Bundle bundle = getIntent().getExtras();
        int img = bundle.getInt("imgSelect");
        imageView.setImageResource(img);

    }

    private void completeRazon() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,razonSocial);
        etRazon.setAdapter(adapter);

    }

    private void ejecutar_02() {
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView_tip.setText(R.string.title_tip_0603);
                handler.postDelayed(this,5000);
                //ejecutar_01();
            }
        },5000);
    }

    private void ejecutar_01(){
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView_tip.setText(R.string.title_tip_0601);
                handler.postDelayed(this,5000);
                //ejecutar_02();
            }
        },5000);


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAtras6:
                onBackPressed();
                break;
            case R.id.btnSiguiente6:
                validarDatos();
                break;
            case R.id.btn_fecha:
                InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                int dia, mes,ano;
                final Calendar c = Calendar.getInstance();
                dia=c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int  year, int monthOfYear, int dayOfMonth) {
                        btnFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,dia,mes,ano);
                //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                //keyboard.hideSoftInputFromWindow(getWindow().getAttributes().token, 0);
                datePickerDialog.show();



                break;
        }
    }

    private void validarDatos() {
        if (TextUtils.isEmpty(etProv.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(etRazon.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(btnFecha.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(etLugar.getText().toString())){
            Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
        }else {

            siguienteAct();
        }

    }
    public void siguienteAct(){
        String idAct= getIntent().getExtras().getString("idAct");
        String extrasName = getIntent().getExtras().getString("nameUser");
        nameUser.setText(extrasName);
        Intent oi = new Intent(this,MainActivity7.class);
        oi.putExtra("idAct",idAct);
        oi.putExtra("userName6", nameUser.getText().toString());
        oi.putExtra("proveedor6", etProv.getText().toString());
        oi.putExtra("ccnit6", etNit.getText().toString());
        oi.putExtra("razon6", etRazon.getText().toString());
        oi.putExtra("fecha6", btnFecha.getText().toString());
        oi.putExtra("lugar6", etLugar.getText().toString());
        oi.putExtra("concepto6", textTitle.getText().toString());
        startActivity(oi);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //ejecutar_01();
        //ejecutar_02();
    }
}

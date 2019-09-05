package pv.portafolioverde.ftsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity implements View.OnClickListener{

    EditText etSubtotal,etImp,etRet,etIva;
    ImageButton ibtnAtras7,ibtnSig7;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        //ImageButton
        ibtnAtras7 = (ImageButton) findViewById(R.id.btnAtras7);
        ibtnAtras7.setOnClickListener(this);
        ibtnSig7 = (ImageButton) findViewById(R.id.btnSig7);
        ibtnSig7.setOnClickListener(this);
        //TextView
        userName = (TextView) findViewById(R.id.userName);
        String extrasName = getIntent().getExtras().getString("userName6");
        userName.setText(extrasName);
        //Edittext
        etSubtotal = findViewById(R.id.et_subtotal);
        etImp = findViewById(R.id.et_imp);
        etRet = findViewById(R.id.et_rete);
        etIva = findViewById(R.id.et_iva);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAtras7:
                onBackPressed();
                break;
            case R.id.btnSig7:
                validarDatos();
        }
    }

    private void validarDatos() {
            if (TextUtils.isEmpty(etSubtotal.getText().toString())){
                Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(etImp.getText().toString())){
                Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(etRet.getText().toString())){
                Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(etIva.getText().toString())){
                Toast.makeText(this, "Debes completar todos los campos...", Toast.LENGTH_SHORT).show();
            }else {
                siguienteAct();
            }
    }

    private void siguienteAct() {
        Bundle bundle = getIntent().getExtras();
        //int img = bundle.getInt("imgSelect");
        //imageView.setImageResource(img);
        String extraName = bundle.getString("userName6");
        String extraProv = bundle.getString("proveedor6");
        String extraCCNIT = bundle.getString("ccnit6");
        String extraRazon = bundle.getString("razon6");
        String extraFecha = bundle.getString("fecha6");
        String extraLugar = bundle.getString("lugar6");
        String extraConcep = bundle.getString("concepto6");
        String idAct= getIntent().getExtras().getString("idAct");

        Intent intent_07 = new Intent(getApplicationContext(),MainActivity8.class);
        intent_07.putExtra("idAct",idAct);
        intent_07.putExtra("userName7", extraName);
        intent_07.putExtra("proveedor7",extraProv);
        intent_07.putExtra("ccnit7",extraCCNIT);
        intent_07.putExtra("razon7",extraRazon);
        intent_07.putExtra("fecha7",extraFecha);
        intent_07.putExtra("lugar7",extraLugar);
        intent_07.putExtra("concepto7",extraConcep);
        intent_07.putExtra("subtotal7",etSubtotal.getText().toString());
        intent_07.putExtra("impuesto7",etImp.getText().toString());
        intent_07.putExtra("rete7",etRet.getText().toString());
        intent_07.putExtra("iva7",etIva.getText().toString());
        startActivity(intent_07);

    }
}

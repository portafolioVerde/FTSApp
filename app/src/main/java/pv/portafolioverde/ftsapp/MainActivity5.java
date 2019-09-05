package pv.portafolioverde.ftsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnAtras;
    GridView mGViewlistConcepto;
    String [] titleDescription = {"Alimentacion","Alojamiento","Anticipo"
            ,"No Ejecutado","Hidratacion","Materiales","M. Financieros"
            ,"Papeleria","Refrigerios","Transportes","Otros","Aeropuerto" };
    int [] idItemsConcepto = {R.drawable.alimentacion_02,R.drawable.alojamiento_02,R.drawable.anticipo_02
            ,R.drawable.noejecutado_02,R.drawable.hidratacion_02,R.drawable.materiales_02,R.drawable.movimientos_02
            ,R.drawable.papeleria_02,R.drawable.refrigerio_02,R.drawable.transporte_02,R.drawable.otros_02,R.drawable.vuelos_02};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        btnAtras = findViewById(R.id.btnAtras5);
        btnAtras.setOnClickListener(this);
        mGViewlistConcepto = findViewById(R.id.gv_listConcepto);
        traerConcepto();
    }

    private void traerConcepto() {
        ConceptAdapter adapterConcep = new ConceptAdapter(getApplicationContext(),titleDescription,idItemsConcepto);
        mGViewlistConcepto.setAdapter(adapterConcep);
        mGViewlistConcepto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String idAct= getIntent().getExtras().getString("idAct");
                String extrasImg = getIntent().getExtras().getString("nameUser");
                Intent nxt = new Intent(getApplicationContext(),MainActivity6.class);
                nxt.putExtra("idAct",idAct);
                nxt.putExtra("nameUser",extrasImg);
                nxt.putExtra("titleText",titleDescription[i]);
                nxt.putExtra("imgSelect",idItemsConcepto[i]);
                Log.d("TAG",""+extrasImg);
                startActivity(nxt);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAtras5:
                onBackPressed();
                break;
        }
    }
}

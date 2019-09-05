package pv.portafolioverde.ftsapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ListaAct extends AppCompatActivity {

    DatabaseReference mDatabase;
    public ActividadesAdapter actividadesAdapter;
    public RecyclerView recyclerView;
    public ArrayList<Actividades> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Función que se encarga de bloquear la rotación de pantalla

        mArrayList = new ArrayList<>();
        actividadesAdapter = new ActividadesAdapter(mArrayList);
        recyclerView = findViewById(R.id.rvActividades);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(actividadesAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference("DataLegalizaciones");
        getActividadesFromFirebase();

    }
    private void getActividadesFromFirebase(){

        mDatabase.child("Actividades").orderByChild("estado").equalTo("aprobado").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                        Actividades actividades = ds.getValue(Actividades.class);
                        mArrayList.add(actividades);
                    }
                    actividadesAdapter = new ActividadesAdapter(mArrayList);
                    recyclerView.setAdapter(actividadesAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
}
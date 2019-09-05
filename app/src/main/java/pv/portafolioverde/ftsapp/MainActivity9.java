package pv.portafolioverde.ftsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity9 extends AppCompatActivity implements View.OnClickListener{

    TextView btnAtras9,btnSig9,tvUsername,tvProveedor,tvNitCC,tvRSocial,tvConcepto,tvFecha,tvTotal,tv_IdKey;
    GoogleSignInClient mGoogleSignInClient;
    ImageView ivState;
    StorageReference mStorage;
    FirebaseDatabase dBase;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference mReference ;
    CircleImageView imageView9;
    public Uri imguri;
    public String urlImagen;
    public static final AtomicInteger count = new AtomicInteger(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        String extraName = getIntent().getExtras().getString("userName8");
        mStorage = FirebaseStorage.getInstance().getReference().child("SoportesGastos").child(extraName).child(""+count.getAndIncrement());

        imageView9 = findViewById(R.id.imageV9);
        tvProveedor = findViewById(R.id.tvProv);
        tvNitCC = findViewById(R.id.tvNit);
        tvRSocial = findViewById(R.id.tvRazS);
        tvConcepto = findViewById(R.id.tvConcep);
        tvFecha = findViewById(R.id.tvFec);
        tvTotal = findViewById(R.id.tvTot);

        ivState = findViewById(R.id.iv_state);
        btnAtras9 = findViewById(R.id.btnAtras9);
        btnAtras9.setOnClickListener(this);
        btnSig9 = findViewById(R.id.btnSig9);
        btnSig9.setOnClickListener(this);
        tvUsername = findViewById(R.id.tvUserName_9);
        validarLogin();

    }

    private void validarLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

    }

    private void enviarDatos() {

        Uri receivedImageUri = getIntent().getParcelableExtra("resImg8");
        mStorage.putFile(receivedImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadedUrl = uri;
                                final String extraName = getIntent().getExtras().getString("userName8");
                                String extraProv8 = getIntent().getExtras().getString("proveedor8");
                                String extraCCNIT8 = getIntent().getExtras().getString("ccnit8");
                                String extraRazon8 = getIntent().getExtras().getString("razon8");
                                String extraFecha8= getIntent().getExtras().getString("fecha8");
                                String extraLugar8 = getIntent().getExtras().getString("lugar8");
                                String extraConcep8 = getIntent().getExtras().getString("concepto8");
                                String extraDescrip = getIntent().getExtras().getString("descrip8");
                                String extraSubtotal8 = getIntent().getExtras().getString("subtotal8");
                                String extraImp8 = getIntent().getExtras().getString("impuesto8");
                                String extraRete8 = getIntent().getExtras().getString("rete8");
                                String extraIva8 = getIntent().getExtras().getString("iva8");
                                String idAct= getIntent().getExtras().getString("idAct");
                                int subINt = Integer.parseInt(""+extraSubtotal8);
                                int ivaINt = Integer.parseInt(""+extraIva8);
                                int reteINt = Integer.parseInt(""+extraRete8);
                                int impINt = Integer.parseInt(""+extraImp8);
                                int resultInt = subINt+impINt+ivaINt-reteINt;

                                Map<String, Object> infor = new HashMap<>();
                                infor.put("idAct",idAct);
                                infor.put("urlImagenGasto", downloadedUrl.toString());
                                infor.put("nombreUsuario", extraName);
                                infor.put("proveedorGasto", extraProv8);
                                infor.put("ccnit", extraCCNIT8);
                                infor.put("razonSocial", extraRazon8);
                                infor.put("fechaGasto", extraFecha8);
                                infor.put("lugarGasto", extraLugar8);
                                infor.put("conceptoGasto", extraConcep8);
                                infor.put("subtotalGasto", extraSubtotal8);
                                infor.put("totalGasto", ""+tvTotal.getText().toString());
                                infor.put("impuestoGasto", extraImp8);
                                infor.put("retencionGasto", extraRete8);
                                infor.put("ivaGasto", extraIva8);
                                infor.put("descripcionGasto", extraDescrip);

                                String extraTotal = Integer.toString(resultInt);
                                tvTotal.setText(extraTotal);

                                FirebaseDatabase dBa = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = dBa.getReference("DataLegalizaciones/");
                                String keyId = myRef.push().getKey();

                                myRef.child("Gastos").child(keyId).updateChildren(infor);

                                DatabaseReference mDatabase;
                                mDatabase = FirebaseDatabase.getInstance().getReference("DataLegalizaciones");
                                String kId = myRef.push().getKey();
                                mDatabase.child("Actividades").orderByChild("idAct").equalTo(idAct).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()){
                                            for (DataSnapshot ds: dataSnapshot.getChildren()) {
                                                Actividades actividades = ds.getValue(Actividades.class);
                                                String valorN = (String) actividades.getValorPresupuesto();
                                                Toast.makeText(getApplicationContext(), "Se guardaron!"+valorN, Toast.LENGTH_LONG).show();
                                                //mArrayList.add(actividades);
                                            }
                                            //actividadesAdapter = new ActividadesAdapter(mArrayList);
                                            //recyclerView.setAdapter(actividadesAdapter);
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                });
                                //mRef.child("Actividades").child(kId).updateChildren();
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                    }
                });
    }

    private void traerDatos() {
        String extraProv8 = getIntent().getExtras().getString("proveedor8");
        String extraCCNIT8 = getIntent().getExtras().getString("ccnit8");
        String extraRazon8 = getIntent().getExtras().getString("razon8");
        String extraFecha8= getIntent().getExtras().getString("fecha8");
        String extraConcep8 = getIntent().getExtras().getString("concepto8");
        Uri receivedImageUri = getIntent().getParcelableExtra("resImg8");
        Glide.with(this).load(receivedImageUri).into(imageView9);

        tvProveedor.setText(extraProv8);
        tvNitCC.setText(extraCCNIT8);
        tvRSocial.setText(extraRazon8);
        tvConcepto.setText(extraConcep8);
        tvFecha.setText(extraFecha8);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAtras9:
                mostrarAlertIntent();
                break;
            case R.id.btnSig9:
                //Toast.makeText(this, "Se guardaron los datos con éxito!", Toast.LENGTH_LONG).show();
                enviarDatos();
                Intent i = new Intent(this,MainActivity2.class);
                startActivity(i);
                break;
        }
    }

    private void mostrarAlertIntent() {
        final CharSequence [] opciones={"Anterior","Menú"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(this);
        alertOpciones.setTitle("¿Regresar al Menú o a la ventana Anterior?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Anterior")){
                    onBackPressed();
                }else{
                    if (opciones[i].equals("Menú")){
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                        intent.putExtra("nameUser",tvUsername.getText().toString());
                        startActivity(intent);
                    }
                }
            }

        });
        alertOpciones.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_item_cerrar:
                signOut();
                return true;
            case R.id.list_item_menu:
                return true;
            case R.id.list_item_perfil:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Se cerro sesión exitosamente...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Toast.makeText(this, "No se pudo subir la imagen", Toast.LENGTH_SHORT).show();
        }else{

            if (data != null) {
                imguri = data.getData();
                imageView9.setImageURI(imguri);
                Toast.makeText(this, "Se subió la imagen ", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se pudo subir la imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        traerDatos();
        calcularTotal();
    }

    private void calcularTotal() {
        String extraSubtotal8 = getIntent().getExtras().getString("subtotal8");
        String extraImp8 = getIntent().getExtras().getString("impuesto8");
        String extraRete8 = getIntent().getExtras().getString("rete8");
        String extraIva8 = getIntent().getExtras().getString("iva8");
        int subINt = Integer.parseInt(""+extraSubtotal8);
        int ivaINt = Integer.parseInt(""+extraIva8);
        int reteINt = Integer.parseInt(""+extraRete8);
        int impINt = Integer.parseInt(""+extraImp8);
        int resultInt = subINt+impINt+ivaINt-reteINt;
        String extraTotal = Integer.toString(resultInt);
        tvTotal.setText(extraTotal);
    }
}

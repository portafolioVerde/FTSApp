package pv.portafolioverde.ftsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener{

    GoogleSignInClient mGoogleSignInClient;
    ImageView btnAgregar;
    TextView tvUsername;
    ImageButton ibtnAtras4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        ibtnAtras4 = (ImageButton) findViewById(R.id.ibtnAtras4);
        ibtnAtras4.setOnClickListener(this);
        tvUsername = findViewById(R.id.tvUserName_04);
        btnAgregar = (ImageView) findViewById(R.id.btnAgregarGasto);
        btnAgregar.setOnClickListener(this);
        validarLogin();

    }

    private void validarLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            tvUsername.setText(personName);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_item_cerrar:
                signOut();
                return true;
            case R.id.list_item_menu:
                //signOut();
                return true;
            case R.id.list_item_perfil:
                //signOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAgregarGasto:
                String idAct= getIntent().getExtras().getString("idAct");
                Intent i = new Intent(this,MainActivity5.class);
                i.putExtra("idAct",idAct);
                i.putExtra("nameUser",""+tvUsername.getText().toString());
                startActivity(i);
                break;
            case R.id.ibtnAtras4:
                mostrarAlertIntent();
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
}

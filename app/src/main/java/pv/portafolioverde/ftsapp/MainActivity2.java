package pv.portafolioverde.ftsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{


    TextView tvName,tvEmail,tvId;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    CircleImageView ivPerfil;
    Button btnSalida,btnVSalidas,btnVGastos,btnPerfil,btnCSalida;
    //LottieAnimationView mLottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnCSalida = findViewById(R.id.btnCSalida);
        btnCSalida.setOnClickListener(this);
        btnSalida = findViewById(R.id.btnRGasto);
        btnSalida.setOnClickListener(this);
        btnVSalidas = findViewById(R.id.btnVSalidas);
        btnVSalidas.setOnClickListener(this);
        btnVGastos = findViewById(R.id.btnVGastos);
        btnVGastos.setOnClickListener(this);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(this);

        ivPerfil = findViewById(R.id.iv_perfil);
        tvName = findViewById(R.id.tv_name);
        tvEmail= findViewById(R.id.tv_email);
        tvId = findViewById(R.id.tv_id);

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
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            tvEmail.setText(personEmail);
            tvName.setText(personName);
            tvId.setText(personId);
            Glide.with(this).load(personPhoto).centerInside().into(ivPerfil);

        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity2.this, "Se cerro sesión exitosamente...", Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRGasto:
                Intent intent = new Intent(this,MainActivity4.class);
                startActivity(intent);
                break;
            case R.id.btnVSalidas:
                Intent intentAct = new Intent(this,ListaAct.class);
                intentAct.putExtra("tvName",tvName.getText().toString());
                startActivity(intentAct);
                break;
            case R.id.btnVGastos:
                //Intent iop = new Intent(this,ListaGastos.class);
                //startActivity(iop);
                break;
            case R.id.btnPerfil:
                Toast.makeText(this, "Ver Salidas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCSalida:
                Intent intentS = new Intent(this,MainActivity3.class);
                intentS.putExtra("tvName",tvName.getText().toString());
                startActivity(intentS);
                break;
        }

    }
}


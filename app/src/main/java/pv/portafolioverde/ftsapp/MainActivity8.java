package pv.portafolioverde.ftsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.inputmethodservice.Keyboard;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class MainActivity8 extends AppCompatActivity implements View.OnClickListener{

    TextView ibtnLoadImg,tvUserName,etUrlImg,tomarFoto,subirFoto;
    ImageButton btnAtras8,btnSig9;
    EditText etDescripcion;
    StorageReference mStorage;
    Uri imgUri;
    String urlImagen;
    FirebaseDatabase dBase = FirebaseDatabase.getInstance();
    ImageView img;
    Uri imgURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tomarFoto = findViewById(R.id.tvTomatFoto);
        tomarFoto.setOnClickListener(this);
        subirFoto = findViewById(R.id.tvSubirFoto);
        subirFoto.setOnClickListener(this);
        img = (ImageView) findViewById(R.id.image_pre);
        etDescripcion = findViewById(R.id.et_descripcion);
        //etUrlImg = findViewById(R.id.et_url_img);
        btnAtras8 = findViewById(R.id.btnAtras8);
        btnAtras8.setOnClickListener(this);
        btnSig9 = findViewById(R.id.btnSig8);
        btnSig9.setOnClickListener(this);

        tvUserName = findViewById(R.id.tvUserName_8);
        String extrasName = getIntent().getExtras().getString("userName");
        tvUserName.setText(extrasName);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSubirFoto:
                preloadphoto();
                break;
            case R.id.btnAtras8:
                onBackPressed();
                break;
            case R.id.btnSig8:
                validarDatos();
                break;
            case R.id.tvTomatFoto:
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;

        }

    }
    public void enviarCampos(){

        //Drawable bitmap = ((Drawable)img.getDrawable());

        Bundle bundle = getIntent().getExtras();
        //int img = bundle.getInt("imgSelect");
        //imageView.setImageResource(img);

        String extraName8 = bundle.getString("userName7");
        String extraProv8 = bundle.getString("proveedor7");
        String extraCCNIT8 = bundle.getString("ccnit7");
        String extraRazon8 = bundle.getString("razon7");
        String extraFecha8 = bundle.getString("fecha7");
        String extraLugar8 = bundle.getString("lugar7");
        String extraConcep8 = bundle.getString("concepto7");
        String extraSub8 = bundle.getString("subtotal7");
        String extraImp8 = bundle.getString("impuesto7");
        String extraRete8 = bundle.getString("rete7");
        String extraIva8 = bundle.getString("iva7");
        String idAct= getIntent().getExtras().getString("idAct");

        Intent intent_08 = new Intent(getApplicationContext(),MainActivity9.class);
        intent_08.putExtra("idAct",idAct);
        intent_08.putExtra("userName8",extraName8);
        intent_08.putExtra("proveedor8",extraProv8);
        intent_08.putExtra("ccnit8",extraCCNIT8);
        intent_08.putExtra("razon8",extraRazon8);
        intent_08.putExtra("fecha8",extraFecha8);
        intent_08.putExtra("lugar8",extraLugar8);
        intent_08.putExtra("concepto8",extraConcep8);
        intent_08.putExtra("subtotal8",extraSub8);
        intent_08.putExtra("impuesto8",extraImp8);
        intent_08.putExtra("rete8",extraRete8);
        intent_08.putExtra("iva8",extraIva8);
        intent_08.putExtra("urlImg8","");
        intent_08.putExtra("descrip8",etDescripcion.getText().toString());
        intent_08.putExtra("resImg8",imgURI);
        startActivity(intent_08);


    }

    public void validarDatos(){
        if ((img.getDrawable()==null)){
            Toast.makeText(this, "Debes cargar una imagen...", Toast.LENGTH_SHORT).show();
        }else{
            enviarCampos();
        }

    }

    public void preloadphoto() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        i.setAction(i.ACTION_GET_CONTENT);
        startActivityForResult(i,1);

    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Toast.makeText(this, "No se pudo subir la imagen", Toast.LENGTH_SHORT).show();
        }else{

            if (data != null) {
                imgUri = data.getData();

                img.setImageURI(imgUri);
                imgURI = imgUri;
                Glide.with(this).load(imgUri).into(img);

                Toast.makeText(this, "Se subió la imagen ", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se pudo subir la imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

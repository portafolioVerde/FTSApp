package pv.portafolioverde.ftsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import static androidx.core.view.MenuItemCompat.getActionView;

public class ListaGastos extends AppCompatActivity {

    DatabaseReference mReference;
    FirebaseDatabase mDatabase;
    RecyclerView mRecycler;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mAuth;
    TextView tvUserNameList;
    FirebaseRecyclerAdapter<Gastos,ViewHolder> firebaseAdapter;
    FirebaseRecyclerOptions<Gastos> mOptions;
    ArrayList<Gastos> mArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_gastos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Gastos List");
        tvUserNameList = findViewById(R.id.tvUserNameList);
        mRecycler = findViewById(R.id.rv_gastos);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mArray = new ArrayList<Gastos>();


        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    String userName = user.getDisplayName();
                    tvUserNameList.setText(userName);
                }else{

                }
            }
        };
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("DataLegalizaciones").child("Andres Mora");
        mOptions = new FirebaseRecyclerOptions.Builder<Gastos>().setQuery(mReference,Gastos.class).build();
        firebaseAdapter = new FirebaseRecyclerAdapter<Gastos, ViewHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Gastos gastos) {
                viewHolder.setDetails(getApplicationContext(),gastos.getConceptoGasto(),gastos.getProveedorGasto(),
                                       gastos.getRazonSocial(), gastos.getFechaGasto(),
                                        gastos.getUrlImagenGasto(),gastos.getTotalGasto());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(ListaGastos.this).inflate(R.layout.row,parent,false));
            }
        };

    }
    public void firebaseSearch(String searchText){
        Query firebaseSearchQuery = mReference.orderByChild("conceptoGasto").startAt(searchText).endAt(searchText+"\uf8ff");
        FirebaseRecyclerAdapter<Gastos,ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Gastos, ViewHolder>(mOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Gastos gastos) {
                viewHolder.setDetails(getApplicationContext(),gastos.getConceptoGasto(),gastos.getProveedorGasto(),
                        gastos.getRazonSocial(), gastos.getFechaGasto(),
                        gastos.getUrlImagenGasto(),gastos.getTotalGasto());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(ListaGastos.this).inflate(R.layout.row,parent,false));
            }
        };
        mRecycler.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Gastos> options =
                new FirebaseRecyclerOptions.Builder<Gastos>()
                        .setQuery(mReference, Gastos.class)
                        .build();

        firebaseAdapter = new FirebaseRecyclerAdapter<Gastos, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ViewHolder holder, int position, Gastos model) {
                // Bind the image_details object to the BlogViewHolder
                holder.setDetails(getApplicationContext(),model.getConceptoGasto(), model.getProveedorGasto(),
                                model.getRazonSocial(), model.getFechaGasto(),
                                model.getUrlImagenGasto(), model.getTotalGasto());
                // ...
            }
        };
        mRecycler.setAdapter(firebaseAdapter);
        firebaseAdapter.startListening();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String ss) {
                firebaseSearch(ss);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                firebaseSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //int id = item.getItemId();
        /*if (id==R.id.action_search){
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}

package pv.portafolioverde.ftsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ActividadesAdapter extends RecyclerView.Adapter<ActividadesAdapter.ViewHolder>{

    //private int resource;
    private ArrayList<Actividades> actividadesList;
    public ActividadesAdapter(ArrayList<Actividades> actividadesList){
        this.actividadesList = actividadesList;
        //this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_act,parent,false);

        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Actividades actividades = actividadesList.get(position);

        holder.tvNombreA.setText(actividades.getNombreActividad());
        holder.tvNumeroP.setText(actividades.getNumeroProjecto());
        holder.tvValorP.setText(actividades.getValorPresupuesto());
        holder.tvTipoA.setText(actividades.getTipoActividad());
        holder.tvFechaA.setText(actividades.getFechaActividad());
        holder.tvEstadoA.setText(actividades.getEstado());
        holder.tvUsuarioA.setText(actividades.getUsuario());
        holder.idAct.setText(actividades.getIdAct());

    }

    @Override
    public int getItemCount() {
        return actividadesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombreA;
        TextView tvNumeroP;
        TextView tvValorP;
        TextView tvTipoA;
        TextView tvFechaA;
        TextView tvEstadoA;
        TextView tvUsuarioA;
        TextView idAct;
        public View view;
        public ViewHolder(View itemView){
            super(itemView);
            view = itemView;

            this.view = itemView;
            this.tvNombreA = (TextView) view.findViewById(R.id.tvNombreA);
            this.tvNumeroP = (TextView) view.findViewById(R.id.tvNumeroP);
            this.tvValorP = (TextView) view.findViewById(R.id.tvValorP);
            this.tvTipoA = (TextView) view.findViewById(R.id.tvTipoA);
            this.tvFechaA = (TextView) view.findViewById(R.id.tvFechaA);
            this.tvEstadoA = (TextView) view.findViewById(R.id.tvEstadoA);
            this.tvUsuarioA = (TextView) view.findViewById(R.id.tvUsuarioA);
            this.idAct = (TextView) view.findViewById(R.id.idAct);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), MainActivity4.class);
                    intent.putExtra("idAct", actividadesList.get(getAdapterPosition()).getNombreActividad());
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}

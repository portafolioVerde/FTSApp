package pv.portafolioverde.ftsapp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }
    public void setDetails(Context context,String provG,String razonG,String concepG,String fechaG,String totalG,String imgURL){

        TextView titleProvG = mView.findViewById(R.id.provG);
        TextView titleRazonG = mView.findViewById(R.id.razonG);
        TextView titleConcepG = mView.findViewById(R.id.concepG);
        TextView titleFechaG = mView.findViewById(R.id.fechaG);
        TextView titleTotalG = mView.findViewById(R.id.totalG);
        ImageView viewImageView = mView.findViewById(R.id.imgG);
        titleProvG.setText(provG);
        titleRazonG.setText(razonG);
        titleConcepG.setText(concepG);
        titleFechaG.setText(fechaG);
        titleTotalG.setText(totalG);

        Picasso.get().load(imgURL).into(viewImageView);

    }

}

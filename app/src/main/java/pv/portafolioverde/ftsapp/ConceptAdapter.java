package pv.portafolioverde.ftsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConceptAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] titleDescription;
    private int [] idItemsConcepto;

    public ConceptAdapter(Context c, String [] titleDescription, int [] idItemsConcepto){
        context = c;
        this.titleDescription = titleDescription;
        this.idItemsConcepto = idItemsConcepto;
    }

    @Override
    public int getCount() {
        return titleDescription.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null){
            convertView = inflater.inflate(R.layout.row_item,null);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView_title);
        TextView  textView = convertView.findViewById(R.id.textView_title);
        imageView.setImageResource(idItemsConcepto[i]);
        textView.setText(titleDescription[i]);
        return convertView;
    }
}

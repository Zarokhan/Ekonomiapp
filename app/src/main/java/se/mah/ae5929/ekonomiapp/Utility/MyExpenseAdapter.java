package se.mah.ae5929.ekonomiapp.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.DBNodes.ExpenseObj;
import se.mah.ae5929.ekonomiapp.R;

/**
 * Created by Zarokhan on 2016-09-17.
 */
public class MyExpenseAdapter extends ArrayAdapter<ExpenseObj> {

    private LayoutInflater inflater;

    public MyExpenseAdapter(Context context, ExpenseObj[] objs) {
        super(context, R.layout.my_expense_listview_item, objs);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null){
            convertView = (LinearLayout)inflater.inflate(R.layout.my_expense_listview_item, parent, false);
            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.titleTv);
            holder.dateTv = (TextView) convertView.findViewById(R.id.dateTv);
            holder.priceTv = (TextView) convertView.findViewById(R.id.priceTv);
            holder.listIv = (ImageView) convertView.findViewById(R.id.listIv);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        ExpenseObj obj = this.getItem(pos);

        int imageres;
        switch (obj.getCategory()){
            case "Livsmedel":
                imageres = R.drawable.livsmedel;
                break;
            case "Fritid":
                imageres = R.drawable.fritid;
                break;
            case "Resor":
                imageres = R.drawable.resor;
                break;
            case "Boende":
                imageres = R.drawable.boende;
                break;
            default:
                imageres = R.drawable.other;
                break;
        }

        holder.listIv.setImageResource(imageres);
        holder.listIv.setAdjustViewBounds(true);
        holder.titleTv.setText(obj.getTitle());
        holder.dateTv.setText(obj.getVisualDate());
        holder.priceTv.setText("" + obj.getPrice());

        return convertView;
    }

    // View holder pattern
    class ViewHolder{
        TextView titleTv;
        TextView dateTv;
        TextView priceTv;
        ImageView listIv;
    }
}

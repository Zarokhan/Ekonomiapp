package se.mah.ae5929.ekonomiapp.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.DBNodes.IncomeObj;
import se.mah.ae5929.ekonomiapp.R;

/**
 * Created by Zarokhan on 2016-09-17.
 */
public class MyIncomeAdapter extends ArrayAdapter<IncomeObj> {

    private LayoutInflater inflater;

    public MyIncomeAdapter(Context context, IncomeObj[] objects) {
        super(context, R.layout.my_income_listview_item, objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null){
            convertView = (LinearLayout)inflater.inflate(R.layout.my_income_listview_item, parent, false);
            holder = new ViewHolder();
            holder.titleTv = (TextView) convertView.findViewById(R.id.titleTv);
            holder.dateTv = (TextView) convertView.findViewById(R.id.dateTv);
            holder.amountTv = (TextView) convertView.findViewById(R.id.amountTv);
            holder.listIv = (ImageView) convertView.findViewById(R.id.listIv);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        IncomeObj obj = this.getItem(pos);

        int imageres;
        switch (obj.getCategory()){
            case "Lön":
                imageres = R.drawable.pay;
                break;
            default:
                imageres = R.drawable.other;
                break;
        }
        holder.listIv.setImageResource(imageres);
        holder.listIv.setAdjustViewBounds(true);

        holder.titleTv.setText(obj.getTitle());
        holder.dateTv.setText(obj.getMydate());
        holder.amountTv.setText("" + obj.getAmount());

        return convertView;
    }

    class ViewHolder{
        TextView titleTv;
        TextView dateTv;
        TextView amountTv;
        ImageView listIv;
    }
}

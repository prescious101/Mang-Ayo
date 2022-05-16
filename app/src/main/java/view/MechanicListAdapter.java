package view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mangayo.R;

import java.util.ArrayList;

import model.AutoMechanicModel;

public class MechanicListAdapter extends BaseAdapter {

    Context context;
    ArrayList<AutoMechanicModel> list;
    LayoutInflater inflater;

    public MechanicListAdapter(Context context, ArrayList<AutoMechanicModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) { return list.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MechanicListHandler mechanicListHandler = null;
        if (view == null) {
            mechanicListHandler = new MechanicListHandler();
            view = inflater.inflate(R.layout.mechanics_list, null);
            mechanicListHandler.name = view.findViewById(R.id.txtMechanicName);
            mechanicListHandler.address = view.findViewById(R.id.txtMechanicAddress);
            mechanicListHandler.email = view.findViewById(R.id.txtMechanicSpecialty);
            mechanicListHandler.specialty = view.findViewById(R.id.txtMechanicSpecialty);
            view.setTag(mechanicListHandler);
        } else {mechanicListHandler = (MechanicListHandler) view.getTag();}
            mechanicListHandler.name.setText("Name: "+list.get(i).getfName()+" "+list.get(i).getlName());
            mechanicListHandler.address.setText("Address: "+list.get(i).getAddress());
            mechanicListHandler.email.setText("Email: "+list.get(i).getEmail());
            mechanicListHandler.specialty.setText("Specialty: "+list.get(i).getSpecialty());

        return view;
    }

    class MechanicListHandler{
        TextView name, address, email, specialty;
    }
}

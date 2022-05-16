package view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.os.StrictMode;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mangayo.R;

import java.util.ArrayList;

import model.TransactionModel;

public class TransactionAdapter extends BaseAdapter {

    Context context;
    ArrayList<TransactionModel> list;
    LayoutInflater inflater;

    public TransactionAdapter(Context context, ArrayList<TransactionModel> list) {
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
        TransactionHandler transactionHandler = null;
        if (view == null) {
            transactionHandler = new TransactionHandler();
            view = inflater.inflate(R.layout.transaction_list, null);
            transactionHandler.transNum = view.findViewById(R.id.txtTransactionNumber);
            transactionHandler.date = view.findViewById(R.id.txtDateServiced);
            transactionHandler.serviceType = view.findViewById(R.id.txtServiceType);
            transactionHandler.payType = view.findViewById(R.id.txtPaymentType);
            transactionHandler.payCost = view.findViewById(R.id.txtPaymentCost);
            view.setTag(transactionHandler);
        } else { transactionHandler = (TransactionHandler) view.getTag(); }

            transactionHandler.transNum.setText("Transaction No.: "+list.get(i).getTransaction_id());
            transactionHandler.date.setText("Date Issued: "+list.get(i).getDate_service());
            transactionHandler.serviceType.setText("Service Type: "+list.get(i).getService_type());
            transactionHandler.payType.setText("Payment Type: "+list.get(i).getPayment_type());
            transactionHandler.payCost.setText("Payment Cost: "+list.get(i).getService_cost()+" PHP");

        return view;
    }

    class TransactionHandler {
        TextView name, date, serviceType, payCost, payType, transNum;
    }
}

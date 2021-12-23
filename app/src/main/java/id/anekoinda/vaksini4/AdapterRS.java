package id.anekoinda.vaksini4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRS extends RecyclerView.Adapter<AdapterRS.viewholder> {

    ArrayList<ModelRs> dataholder = new ArrayList<>();
    Context context;
    SQLiteDatabase sqLiteDatabase;
    TextView is_valid;

    public AdapterRS(ArrayList<ModelRs> dataholder, Context context, SQLiteDatabase sqLiteDatabase) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rs, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.nama_rs.setText(dataholder.get(position).getNama_rs());
        holder.jalan.setText(dataholder.get(position).getJalan());
        holder.waktu.setText(dataholder.get(position).getWaktu());
        holder.vaksin.setText(dataholder.get(position).getVaksin());
    }

    @Override
    public int getItemCount() {
        return dataholder == null ? 0 : dataholder.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView nama_rs, jalan, waktu, vaksin;
        Button button_delete, button_update;
        public viewholder(@NonNull View itemView){
            super(itemView);
            nama_rs = (TextView) itemView.findViewById(R.id.nama_rs);
            jalan = (TextView) itemView.findViewById(R.id.jalan);
            waktu = (TextView) itemView.findViewById(R.id.waktu);
            vaksin = (TextView) itemView.findViewById(R.id.vaksin);
        }
    }
}

package id.anekoinda.vaksini4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRS extends RecyclerView.Adapter<AdapterRS.MyViewHolder>{
    private ArrayList<RS> items;
    private Context context;
    private DBHelper dbHelper;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtVaksin, txtNama, txtAlamat;
        private ImageView menu;

        public MyViewHolder(View itemView) {
            super(itemView);

//            txtVaksin = itemView.findViewById(R.id.nama_vaksin);
//            txtNama = itemView.findViewById(R.id.nama_lokasi);
//            txtAlamat = itemView.findViewById(R.id.jalan);
        }
    }

    public AdapterRS(ArrayList<RS> items, Context context) {
        this.items = items;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rs, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        RS obj = items.get(position);
        holder.txtVaksin.setText(obj.getVaksin());
        holder.txtNama.setText(obj.getNama());
        holder.txtAlamat.setText(obj.getAlamat());

//        final String id = obj.getId();
//        final String judul = obj.getJudul();
//        final String nama = obj.getNama();
//        final String kategori = obj.getKategori();
//        final String genre = obj.getGenre();
//        final String rating = obj.getRating();
//        final String verif = obj.getVerif();

//        holder.menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popup = new PopupMenu(context, holder.menu);
//                popup.inflate(R.menu.menu_rv);
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.menu_edit:
//                                Intent i_input = new Intent(context, UpdateActivity.class);
////                                i_input.putExtra("MODE", "EDIT");
//
//                                i_input.putExtra("JUDUL", judul);
//                                i_input.putExtra("NAMA", nama);
//                                i_input.putExtra("ID", id);
//                                i_input.putExtra("KATEGORI", kategori);
//                                i_input.putExtra("GENRE", genre);
//                                i_input.putExtra("RATING", rating);
//                                i_input.putExtra("VERIF", verif);
//                                context.startActivity(i_input);
//                                break;
//                            case R.id.menu_hapus:
//                                dbHelper.dml("delete from tb_buku where judul = '" + judul + "';");
//                                items.remove(position);
//                                notifyDataSetChanged();
//
//                                break;
//                        }
//                        return false;
//                    }
//                });
//                popup.show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

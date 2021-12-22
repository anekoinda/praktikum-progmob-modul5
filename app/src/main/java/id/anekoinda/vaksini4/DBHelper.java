package id.anekoinda.vaksini4;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private final Context context;

    public DBHelper(Context context) {
        super(context, "vaksini.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE data_vaksin(nik TEXT PRIMARY KEY, nama TEXT, telepon TEXT, jenis_kelamin TEXT, kondisdi_kesehatan TEXT, persentase_kondisi TEXT, keterangan TEXT DEFAULT '', is_valid TEXT)");
        db.execSQL("create table rs(id integer primary key, nama text, alamat text, vaksin text);");
        db.execSQL("INSERT INTO rs (id, nama, alamat, vaksin) VALUES ('01', 'RS Sanglah', 'Sanglah', 'Pfizer');");
        db.execSQL("INSERT INTO rs (id, nama, alamat, vaksin) VALUES ('02', 'RS Udayana', 'Jimbaran', 'Sinovac');");
        db.execSQL("INSERT INTO rs (id, nama, alamat, vaksin) VALUES ('03', 'RS Sanglah', 'Sanglah', 'Moderna');");
        db.execSQL("INSERT INTO rs (id, nama, alamat, vaksin) VALUES ('04', 'RS Karangasem', 'Karangase', 'Sinovac');");
        db.execSQL("INSERT INTO rs (id, nama, alamat, vaksin) VALUES ('05', 'Permata Ibu', 'Kedonganan', 'Pfizer');");

        db.execSQL("CREATE TABLE data_register(nik TEXT PRIMARY KEY, nama TEXT, telepon TEXT, password TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS vaksini.db");
        onCreate(db);
    }

    public Boolean insert(String nik, String nama, String telepon,
                          String jenis_kelamin, String kondisi_kesehatan,
                          String progress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nik", nik);
        contentValues.put("nama", nama);
        contentValues.put("telepon", telepon);
        contentValues.put("jenis_kelamin", jenis_kelamin);
        contentValues.put("kondisi_kesehatan", kondisi_kesehatan);
        contentValues.put("persentase_kondisi", progress);
        long result = db.insert("data_vaksin", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insert(String nik, String nama, String telepon,
                          String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nik", nik);
        contentValues.put("nama", nama);
        contentValues.put("telepon", telepon);
        contentValues.put("password", password);
        long result = db.insert("data_register", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT*FROM data_vaksin ORDER BY nik ASC");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void deleteData(String nik) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM data_vaksin WHERE nik='" + nik + "'");
    }

    void updateData(String nik, String keterangan, String is_valid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("keterangan", keterangan);
        cv.put("is_valid", is_valid);

        long result = db.update("data_vaksin", cv,
                "nik" + "='" + nik + "'", null);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(context, Data.class);
            context.startActivity(intent);
        }
    }

    //pilih rumah sakit
    public void dml(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(query);

    }
    public String[][] cari_array(String query, int baris, int kolom){
        String[][] data = new String[baris][kolom];
        SQLiteDatabase db = this.getReadableDatabase();

        int counter_baris = 0;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            for (int i=0; i<kolom; i++){
                data[counter_baris][i] = cursor.getString(i);
            }
            counter_baris++;
        }

        return data;
    }
    public String cari(String query){
        String hasil = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            hasil = cursor.getString(0);
        }
        return hasil;
    }
}



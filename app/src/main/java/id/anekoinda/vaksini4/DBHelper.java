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
        super(context, "database_vaksini", null, 1);
        context.openOrCreateDatabase("database_vaksini", context.MODE_PRIVATE, null);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE data_vaksin(nik TEXT PRIMARY KEY, nama TEXT, telepon TEXT, jenis_kelamin TEXT, kondisi_kesehatan TEXT, persentase_kondisi TEXT, keterangan TEXT DEFAULT '', is_valid TEXT)");
        db.execSQL("CREATE TABLE data_registrasi(id INTEGER PRIMARY KEY AUTOINCREMENT, nik TEXT, nama TEXT, telepon TEXT, password TEXT)");
        db.execSQL("CREATE TABLE data_covid(positif INT, negatif INT, meninggal INT)");
        db.execSQL("INSERT INTO " + "data_covid" + "(positif, negatif, meninggal) VALUES (2000, 454, 23)");
        db.execSQL("CREATE TABLE data_rs(id_rs INT PRIMARY KEY, nama_rs TEXT, jalan TEXT, waktu TEXT, vaksin TEXT)");
        db.execSQL("INSERT INTO " + "data_rs" + "(id_rs, nama_rs, jalan, waktu, vaksin) VALUES (1,'RS PTN Udayana', 'Jl Mawar 1, Mengwi, Bali', '12 Desember 2021 10.00 WITA', 'Astrazeneca')");
        db.execSQL("INSERT INTO " + "data_rs" + "(id_rs, nama_rs, jalan, waktu, vaksin) VALUES (2,'RS Wangaya', 'Jl Mawar 2, Mengwi, Bali', '12 Desember 2021 10.00 WITA', 'Astrazeneca')");
        db.execSQL("INSERT INTO " + "data_rs" + "(id_rs, nama_rs, jalan, waktu, vaksin) VALUES (3,'RS Sanglah', 'Jl Mawar 3, Mengwi, Bali', '12 Desember 2021 10.00 WITA', 'Astrazeneca')");
        db.execSQL("INSERT INTO " + "data_rs" + "(id_rs, nama_rs, jalan, waktu, vaksin) VALUES (4,'RS Kapal', 'Jl Mawar 4, Mengwi, Bali', '12 Desember 2021 10.00 WITA', 'Sinovac')");
        db.execSQL("INSERT INTO " + "data_rs" + "(id_rs, nama_rs, jalan, waktu, vaksin) VALUES (5,'Puskesmas Mengwi', 'Jl Mawar 5, Mengwi, Bali', '12 Desember 2021 10.00 WITA', 'Sinovac')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS database_vaksini");
        onCreate(db);
    }

    public Boolean insert(String nik, String nama, String telepon,
                          String jenis_kelamin, String kondisi_kesehatan,
                          String progress, String keterangan, String is_valid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nik", nik);
        contentValues.put("nama", nama);
        contentValues.put("telepon", telepon);
        contentValues.put("jenis_kelamin", jenis_kelamin);
        contentValues.put("kondisi_kesehatan", kondisi_kesehatan);
        contentValues.put("persentase_kondisi", progress);
        contentValues.put("keterangan", keterangan);
        contentValues.put("is_valid", is_valid);
        long result = db.insert("data_vaksin", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getUser(String nik){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from data_registrasi where nik = " + nik, null);
        return cursor;
    }

    void updateUser(Integer id, String nik, String nama, String telepon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nik", nik);
        cv.put("nama", nama);
        cv.put("telepon", telepon);

        long result = db.update("data_registrasi", cv,
                "id" + "='" + id + "'", null);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(context, login.class);
            context.startActivity(intent);
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
        long result = db.insert("data_registrasi", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checknikpassword(String nik, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from data_registrasi where nik = ? and password = ?", new String[] {nik, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT*FROM data_vaksin ORDER BY nik ASC");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor readDataCovid() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT*FROM data_covid");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor readDataRs() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT*FROM data_rs ORDER BY id_rs ASC LIMIT 2");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor readDataRsVaksin() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT*FROM data_rs");
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


}
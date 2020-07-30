package com.example.bottomnav.setmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    private static final String DB_NAME = "sample.db";
    private static final String DB_TABLE = "mysheet";
    private static final int DB_VERSION = 1;

    public final static String COL_ID = "id";
    public final static String COL_PRODUCT = "product";
    public final static String COL_MADEIN = "madein";
    public final static String COL_NUMBER = "number";
    public final static String COL_PRICE = "price";

    private SQLiteDatabase db = null;
    private DBHelper dbHelper = null;
    private Context context;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DBHelper(this.context);
    }

    public DBAdapter openDB() {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public DBAdapter readDB() {
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public void closeDB() {
        db.close();
        db = null;
    }

    public void saveDB(String product, String madein, String number, String price) {

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();     // ContentValuesでデータを設定していく
            values.put(COL_PRODUCT, product);
            values.put(COL_MADEIN, madein);
            values.put(COL_NUMBER, number);
            values.put(COL_PRICE, price);

            db.insert(DB_TABLE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public Cursor getDB(String[] columns) {
        return db.query(DB_TABLE, columns, null, null, null, null, null);
    }

    public Cursor searchDB(String[] columns, String column, String[] name) {
        return db.query(DB_TABLE, columns, column + " like ?", name, null, null, null);
    }

    public void allDelete() {
        db.beginTransaction();

        try {
            db.delete(DB_TABLE, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public void selectDelete(String position) {
        db.beginTransaction();
        try {
            db.delete(DB_TABLE, COL_ID + "=?", new String[] {position});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }



    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTbl = "CREATE TABLE " + DB_TABLE + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_PRODUCT + " TEXT NOT NULL,"
                    + COL_MADEIN + " TEXT NOT NULL,"
                    + COL_NUMBER + " INTEGER NOT NULL,"
                    + COL_PRICE + " INTEGER NOT NULL"
                    + ");";

            db.execSQL(createTbl);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }



}

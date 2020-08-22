package com.example.bottomnav.notify;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TestOpenHelper2 extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データーベース名
    private static final String DATABASE_NAME = "TestDB2.db";
    private static final String TABLE_NAME = "testdb2";
    private static final String _ID = "_id2";
    private static final String COLUMN_NAME_TITLE0 = "date";
    private static final String COLUMN_NAME_TITLE = "food_name";
    private static final String COLUMN_NAME_MEAL = "meal_name";
    private static final String COLUMN_NAME_SUBTITLE2 = "food_cal";
    private static final String COLUMN_NAME_SUBTITLE3 = "food_gram";
    private static final String COLUMN_NAME_SUBTITLE4 = "detail";
    private static final String COLUMN_NAME_SUBTITLE5 = "food_car";
    private static final String COLUMN_NAME_SUBTITLE6 = "food_pro";
    private static final String COLUMN_NAME_SUBTITLE7 = "food_fat";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE0 + " TEXT," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_MEAL + " TEXT," +
                    COLUMN_NAME_SUBTITLE2 + " INTEGER," +
                    COLUMN_NAME_SUBTITLE3 + " TEXT," +
                    COLUMN_NAME_SUBTITLE4 + " TEXT," +
                    COLUMN_NAME_SUBTITLE5 + " INTEGER," +
                    COLUMN_NAME_SUBTITLE6 + " INTEGER," +
                    COLUMN_NAME_SUBTITLE7 + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public TestOpenHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        Log.d("debug", "onCreate(SQLiteDatabase db)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}

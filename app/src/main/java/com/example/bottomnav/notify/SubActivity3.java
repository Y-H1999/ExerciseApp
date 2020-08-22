package com.example.bottomnav.notify;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.bottomnav.R;
import com.example.bottomnav.others.Global;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import android.graphics.Bitmap;
//import android.graphics.Matrix;
//import java.io.FileNotFoundException;
//import java.io.IOException;


public class SubActivity3 extends AppCompatActivity {

    private final static int RESULT_CAMERA = 1001;
    private final static int REQUEST_PERMISSION = 1002;
    private Global global;
    private List<String> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();
    private List<String> list4 = new ArrayList<>();
    private List<String> image_list = new ArrayList<>();
    private TestOpenHelper2 helper2;
    private SQLiteDatabase db3;
    private ImageView imageView;
    private TestOpenHelper4 helper4;
    private Uri m_uri;
    private SQLiteDatabase db;
//    private Bitmap bitmapp;
    private NonScrollListView listView;
    private List<String> idlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);

        global = (Global) this.getApplication();
        TextView title = findViewById(R.id.titlemeal);
        ImageButton img1Button = findViewById(R.id.imageB1_1);
        ImageButton go_research_button = findViewById(R.id.goreButton);
        ImageButton myfoodButton = findViewById(R.id.button7);
        imageView = findViewById(R.id.image_view);

        Toolbar toolbar = findViewById(R.id.toolbar3);

        toolbar.inflateMenu(R.menu.menu_camera);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);  //インテント作成
                startActivity(intent);
            }

        });

        String text = global.getTestString() + "の" + global.getMealString();
        title.setText(text);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if((item.getItemId()) == R.id.menuGalleryBtn){
                    checkPermission();
                }
                return true;
            }
        });

        getImage();

        img1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentimgb1 = new Intent(getApplication(), ImgActivity1.class);  //インテント作成
                startActivity(intentimgb1);
            }
        });

        go_research_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentgore = new Intent(getApplication(), HistoryResearchActivity.class);  //インテント作成
                startActivity(intentgore);
            }
        });

        myfoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myfoodintent = new Intent(getApplication(), MenuRegisterActivity.class);  //インテント作成
                startActivity(myfoodintent);
            }
        });

        loadList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(helper2 == null){
                    helper2 = new TestOpenHelper2(SubActivity3.this);
                }//編集ボタン等の動作
                db = helper2.getWritableDatabase();
                if (view.getId() == R.id.button3) {
                    db.delete("testdb2", "_id2 = ?", new String[]{idlist.get(position)});
                    loadList();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == RESULT_CAMERA) {

            if(resultCode != RESULT_OK) {
                // キャンセル時
                return ;
            }

            Uri resultUri = (intent != null ? intent.getData() : m_uri);

            if(resultUri == null) {
                // 取得失敗
                return;
            }

            // ギャラリーへスキャンを促す
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/jpeg"},
                    null
            );

            // 画像を設定
//            try {
//                bitmapp = MediaStore.Images.Media.getBitmap(this.getContentResolver(),resultUri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            // 画像の横、縦サイズを取得
//            int imageWidth = bitmapp.getWidth();
//            int imageHeight = bitmapp.getHeight();
//
//            // Matrix インスタンス生成
//            Matrix matrix = new Matrix();
//
//            // 画像中心を基点に-90度回転
//            matrix.setRotate(-90, imageWidth/2, imageHeight/2);
//
//            // 90度回転したBitmap画像を生成
//            Bitmap bitmap2 = Bitmap.createBitmap(bitmapp, 0, 0,
//                    imageWidth, imageHeight, matrix, true);
//
//            imageView.setImageBitmap(bitmap2);
            imageView.setImageURI(resultUri);
            expansionPic(resultUri);
            String stringUri2;
            stringUri2 = resultUri.toString();
            addEntry(db3, global.getTestString(), global.getMealString(), stringUri2);

        }
    }

    // Runtime Permission check
    private void checkPermission(){
        // 既に許可している
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED){
            showGallery();
        }
        // 拒否していた場合
        else{
            requestPermission();
        }
    }

    // 許可を求める
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(SubActivity3.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);

        } else {
            Toast toast = Toast.makeText(this,
                    "許可されないとアプリが実行できません",
                    Toast.LENGTH_SHORT);
            toast.show();

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    REQUEST_PERMISSION);

        }
    }

    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        Log.d("debug","onRequestPermissionsResult()");

        if (requestCode == REQUEST_PERMISSION) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showGallery();

            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this,
                        "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void addEntry(SQLiteDatabase db3,  String date, String meal, String image) throws SQLiteException {
        if (helper4 == null) {
            helper4 = new TestOpenHelper4(getApplicationContext());
        }
        if (db3 == null) {
            db3 = helper4.getWritableDatabase();
        }
        ContentValues cv = new  ContentValues();
        cv.put("date", date);
        cv.put("meal", meal);
        cv.put("image_data", image);
        db3.insert("testdb4", null, cv );
    }

    public void getImage(){
        if(helper4 == null){
            helper4 = new TestOpenHelper4(SubActivity3.this);
        }

        try (SQLiteDatabase db3 = helper4.getWritableDatabase()){
            Cursor c = db3.rawQuery("select date, meal, image_data from testdb4", null);
            if(c.getCount() != 0) {
                boolean next = c.moveToFirst();
                while (next) {

                    if (global.getTestString().equals(c.getString(0)) && global.getMealString().equals(c.getString(1))) {
                        String image_data = c.getString(2);
                        image_list.add(image_data);
                    }
                    next = c.moveToNext();
                }
                if(image_list.size() != 0) {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),Uri.parse(image_list.get(image_list.size() - 1)));
//                    // 画像の横、縦サイズを取得
//                    int imageWidth = bitmap.getWidth();
//                    int imageHeight = bitmap.getHeight();
//
//                    // Matrix インスタンス生成
//                    Matrix matrix = new Matrix();
//
//                    // 画像中心を基点に-90度回転
//                    matrix.setRotate(-90, imageWidth/2, imageHeight/2);
//
//                    // 90度回転したBitmap画像を生成
//                    Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0,
//                            imageWidth, imageHeight, matrix, true);
//
//                    imageView.setImageBitmap(bitmap2);
                    imageView.setImageURI(Uri.parse(image_list.get(image_list.size() - 1)));
                }
            }
            c.close();
        }
        if(image_list.size() != 0) {
            expansionPic(Uri.parse(image_list.get(image_list.size() - 1)));
        }
    }

    private void showGallery() {

        //カメラの起動Intentの用意
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, photoName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        m_uri = getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

        // ギャラリー用のIntent作成
        Intent intentGallery;
        intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
        intentGallery.setType("image/jpeg");
        Intent intent = Intent.createChooser(intentCamera, "画像の選択");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {intentGallery});
        startActivityForResult(intent, RESULT_CAMERA);
    }

    private void loadList(){
        list.clear();
        list2.clear();
        list3.clear();
        list4.clear();
        idlist.clear();

        if(helper2 == null){
            helper2 = new TestOpenHelper2(SubActivity3.this);
        }

        try (SQLiteDatabase db2 = helper2.getWritableDatabase()){
            // rawQueryというSELECT専用メソッドを使用してデータを取得する
            Cursor c = db2.rawQuery("select _id2, date, food_name, meal_name, food_cal, food_gram, detail from testdb2", null);
            // Cursorの先頭行があるかどうか確認
            boolean next = c.moveToFirst();

            // 取得した全ての行を取得
            while (next) {
                // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                if(global.getMealString().equals(c.getString(3)) && global.getTestString().equals(c.getString(1))) {
                    String rowdata = c.getString(2);// nameを取得
                    String rowdata2 = c.getString(5);//gram
                    Double rowdata3 = c.getDouble(4);// calを取得
                    String rowdata4 = c.getString(6);
                    String id = c.getString(0);

                    list.add(rowdata);
                    list3.add(rowdata2);
                    list2.add(rowdata3 + "kcal");//listにdbから取得したデータを追加
                    list4.add(rowdata4);
                    idlist.add(id);
                }

                // 次の行が存在するか確認
                next = c.moveToNext();
            }

            c.close();

        }

        listView = findViewById(R.id.itemlists);
        CustomAdapter2 adapter = new CustomAdapter2(getApplicationContext(), R.layout.row_item2, (ArrayList<String>) list, (ArrayList<String>) list2 , (ArrayList<String>) list4, (ArrayList<String>) list3);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void expansionPic(final Uri uri1){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(SubActivity3.this);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(bitmap);
                // ディスプレイの幅を取得
                Display display =  getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                float width = size.x;

                assert bitmap != null;
                float factor =  width / bitmap.getWidth();
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                Dialog dialog = new Dialog(SubActivity3.this);

                Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(imageView);
                dialog.getWindow().setLayout((int)(bitmap.getWidth()*factor), (int)(bitmap.getHeight()*factor));
                dialog.show();
            }
        });
    }

}

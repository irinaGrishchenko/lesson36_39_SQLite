package com.example.irina.lesson36_39_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity1 extends AppCompatActivity {

    @BindViews({R.id.btnAll, R.id.btnFunc, R.id.btnGroup, R.id.btnHavig, R.id.btnPeople, R.id.btnSort})
    Button button;
    @BindViews({R.id.etFunc, R.id.etPeople, R.id.etRegionPeople})
    EditText editText;
    @BindView(R.id.rgSort)
    RadioGroup radioGroup;

    final String LOG_TAG = "myLogs";

    String name[] = {"Китай", "США", "Бразилия", "Россия", "Япония", "Германия", "Египет", "Италия", "Франция", "Канада"};
    int people[] = {1400, 311, 195, 142, 128, 80, 80, 60, 66, 35};
    String region[] = {"Азия", "Америка", "Америка", "Европа", "Азия", "Европа", "Африка", "Европа", "Европа", "Америка"};

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        ButterKnife.bind(this);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if(c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            for (int i = 0; i<10; i++) {
                cv.put("name", name[i]);
                cv.put("people", people[i]);
                cv.put("region", region[i]);
                Log.d(LOG_TAG, "id = " + db.insert("mytable", null, cv));
            }
        }
        c.close();
        dbHelper.close();

    }

    public void OnClick(View v){

        db = dbHelper.getWritableDatabase();

        String sFunc = etFunc.
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB1.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "--onCreate db--");
            db.execSQL("create table mytable ("
                        + "id integet primary key autoincrement, "
                        + "name text, "
                        + "people integer, "
                        + "region text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}

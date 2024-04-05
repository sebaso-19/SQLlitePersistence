package unipiloto.edu.sqlitetaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    EditText editTextTipoCarga, editTextPeso, editTextDelicado;
    Button button,button2;
    ListView listView;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTipoCarga = findViewById(R.id.editTextText3);
        editTextPeso = findViewById(R.id.editTextText4);
        editTextDelicado = findViewById(R.id.editTextText5);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        listView = findViewById(R.id.listView);
        myDb = new DatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayData();
            }
        });
    }

    public void insertData() {
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, editTextTipoCarga.getText().toString());
        contentValues.put(DatabaseHelper.COL_3, editTextPeso.getText().toString());
        contentValues.put(DatabaseHelper.COL_4, editTextDelicado.getText().toString());
        long result = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public void displayData() {
        SQLiteDatabase db = myDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID as _id, TIPO_CARGA, PESO, DELICADO FROM " + DatabaseHelper.TABLE_NAME, null);

        String[] fromColumns = {DatabaseHelper.COL_2, DatabaseHelper.COL_3, DatabaseHelper.COL_4};
        int[] toViews = {android.R.id.text1, android.R.id.text2, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                fromColumns,
                toViews,
                0);

        listView.setAdapter(adapter);
    }
}
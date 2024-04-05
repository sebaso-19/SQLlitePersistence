package unipiloto.edu.sqlitetaller;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHelper extends SQLiteOpenHelper {


        public static final String DATABASE_NAME = "cargas.db";


        public static final String TABLE_NAME = "cargas_table";


        public static final String COL_1 = "ID";
        public static final String COL_2 = "TIPO_CARGA";
        public static final String COL_3 = "PESO";
        public static final String COL_4 = "DELICADO";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TIPO_CARGA TEXT, PESO TEXT, DELICADO TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

package Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VentasDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_VENTAS = "CREATE TABLE " +
            DefineTable.Ventas.TABLE_NAME + " (" +
            DefineTable.Ventas.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            DefineTable.Ventas.COLUMN_NAME_NUM_BOMBA + INTEGER_TYPE + COMMA_SEP +
            DefineTable.Ventas.COLUMN_NAME_TIPO_GASOLINA + INTEGER_TYPE + COMMA_SEP +
            DefineTable.Ventas.COLUMN_NAME_PRECIO + REAL_TYPE + COMMA_SEP +
            DefineTable.Ventas.COLUMN_NAME_CANTIDAD + INTEGER_TYPE + ")";


    private static final String SQL_DELETE_VENTAS = "DROP TABLE IF EXISTS " +
            DefineTable.Ventas.TABLE_NAME;

    private static final String DATABASE_NAME = "db2";
    private static final int DATABASE_VERSION = 1;

    public VentasDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_VENTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_VENTAS);
        onCreate(sqLiteDatabase);
    }
}

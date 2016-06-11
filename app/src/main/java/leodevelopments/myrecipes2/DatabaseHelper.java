package leodevelopments.myrecipes2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
    // имя базы данных
    private static final String DATABASE_NAME ="myrecipes.db";
    //версия базы данных
    private static final int DATABASE_VERSION = 1;
    //имя таблицы
    private static final String DATABASE_TABLE = "recipes";
    //названия столбцов
    public static final String RECIPE_NAME_COLUMN = "recipe_name";
    public static final String INSTRUCTION_COLUMN = "instruction";
    public static final String PHOTO_COLUMN = "photo";
    public static final String INGREDIENTS_COLUMN = "ingredients";
    private static final String DATABASE_CREATE_SCRIPT = " create table "
            + DATABASE_TABLE + " ("+ BaseColumns._ID
            + " integer primary key autoincrement, " + RECIPE_NAME_COLUMN
            + " text to null, " + PHOTO_COLUMN + " text to null, " + INGREDIENTS_COLUMN
            + " text to null, " + INSTRUCTION_COLUMN + " text to null);";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        //удаляем старую таблицу и созадём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // создаём новую таблицу
        onCreate(db);
    }

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}

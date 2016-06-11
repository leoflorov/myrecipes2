package leodevelopments.myrecipes2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class AddRecipeActivity extends Activity implements View.OnClickListener {

    private DatabaseHelper mDataBaseHelper;
    private SQLiteDatabase mSQLiteDatabase;
    Button btnAdd;
    EditText etName, etIngredients, etInstructions, etPhoto;
    DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipe_layout);

        btnAdd = (Button) findViewById(R.id.saveRecipeButton);
        btnAdd.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.recipe_name);
        etIngredients = (EditText) findViewById(R.id.add_ingredients_txt);
        etInstructions = (EditText) findViewById(R.id.add_instructions_txt);

        dbHelper = new DatabaseHelper(this);


//        mDataBaseHelper = new DatabaseHelper(this, "myrecipes.db", null, 1);
//
//        mSQLiteDatabase = mDataBaseHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        //задание значения для каждого столбца
//        values.put(DatabaseHelper.RECIPE_NAME_COLUMN, "Оивье");
//        values.put(DatabaseHelper.PHOTO_COLUMN, "@drawable/salad.jpg");
//        values.put(DatabaseHelper.INGREDIENTS_COLUMN, "Колбаса, майонез, яйца и т.д.");
//        values.put(DatabaseHelper.INSTRUCTION_COLUMN, "ПРиготовить салат");

        String[] categoriesAutoComplete = {"Салаты", "Супы", "Вторые блюда", "Напитки", "Десерты", "Курица", "Рыба", "Мясо", "Праздники"};

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categoriesAutoComplete);
        textView.setAdapter(adapter);

    }
//            // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesSpinner);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//            Spinner spinner = (Spinner) findViewById(R.id.spinner);
//            spinner.setAdapter(adapter);
//        spinner.setPrompt("Категории рецептов");
//        spinner.setSelection(2);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(), "Position =" + position, Toast.LENGTH_SHORT).show();
//            }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String ingredients = etIngredients.getText().toString();
        String instructions = etInstructions.getText().toString();


        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.saveRecipeButton:
                contentValues.put(DatabaseHelper.RECIPE_NAME_COLUMN, name);
                contentValues.put(DatabaseHelper.INGREDIENTS_COLUMN, ingredients);
                contentValues.put(DatabaseHelper.INSTRUCTION_COLUMN, instructions);

                database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DatabaseHelper._ID);
                    int nameIndex = cursor.getColumnIndex(DatabaseHelper.RECIPE_NAME_COLUMN);
                    int ingredientsIndex = cursor.getColumnIndex(DatabaseHelper.INGREDIENTS_COLUMN);
                    int instructionsIndex = cursor.getColumnIndex(DatabaseHelper.INSTRUCTION_COLUMN);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", ingredients = " + cursor.getString(ingredientsIndex) +
                                ", instructions = " + cursor.getString(instructionsIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DatabaseHelper.DATABASE_TABLE, null, null);
                break;
        }
        dbHelper.close();
    }
//    public void onClick(View v) {
//        mDataBaseHelper = new DatabaseHelper(this, "myrecioes,db", null, 1);
//        SQLiteDatabase sdb;
//        sdb = mDataBaseHelper.getReadableDatabase();
}



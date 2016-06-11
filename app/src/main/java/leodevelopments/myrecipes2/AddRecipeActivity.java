package leodevelopments.myrecipes2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AddRecipeActivity extends AppCompatActivity {

    private DatabaseHelper mDataBaseHelper;
    private SQLiteDatabase mSQLiteDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipe_layout);


        mDataBaseHelper = new DatabaseHelper(this, "myrecipes.db", null, 1);

        mSQLiteDatabase = mDataBaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        //задание значения для каждого столбца
        values.put(DatabaseHelper.RECIPE_NAME_COLUMN, "Оивье");
        values.put(DatabaseHelper.PHOTO_COLUMN, "@drawable/salad.jpg");
        values.put(DatabaseHelper.INGREDIENTS_COLUMN, "Колбаса, майонез, яйца и т.д.");
        values.put(DatabaseHelper.INSTRUCTION_COLUMN, "ПРиготовить салат");

        String[] categoriesAutoComplete = {"Салаты", "Супы", "Вторые блюда", "Напитки", "Десерты", "Курица", "Рыба", "Мясо", "Праздники"};

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categoriesAutoComplete);
        textView.setAdapter(adapter);

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
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }

    public void onClick(View v) {
        mDataBaseHelper = new DatabaseHelper(this, "myrecioes,db", null, 1);
        SQLiteDatabase sdb;
        sdb = mDataBaseHelper.getReadableDatabase();
    }
}

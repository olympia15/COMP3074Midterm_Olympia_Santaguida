package com.example.comp3074midterm_olympia_santaguida;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText numberInput;
    private Button generateTableBtn;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> multiplicationList;
    public static ArrayList<Integer> numberHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initializing views
        numberInput = findViewById(R.id.numberInput);
        generateTableBtn = findViewById(R.id.generateBtn);
        listView = findViewById(R.id.listView);

        // initializing list and adapter
        multiplicationList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, multiplicationList);
        listView.setAdapter(adapter);

        // button click listener
        generateTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateTableBtn();
            }
        });

        // ListView click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
            }
        });
    }

    private void generateTable(){
        String input = numberInput.getText().toString().trim();

        if (input.isEmpty()){
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            int number = Integer.parseInt(input);

            // add to history if not already done so
            if (!numberHistory.contains(number)) {
                numberHistory.add(number);
            }

            // clear previous list and generate new table
            multiplicationList.clear();
            for (int i = 1; i <= 10; i++) {
                int result = number * i;
                String row = number + " × " + i + " = " + result;
                multiplicationList.add(row);
            }

            adapter.notifyDataSetChanged();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    

}
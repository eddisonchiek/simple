package sg.edu.sg.c346.id21034014.simpletodo;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText etTask;
    Button btnAdd;
    Button btnDlt;
    Button btnClr;
    Spinner spnTaskOrDlt;
    ListView lvTask;
    ArrayList<String> Task;
    ArrayAdapter<String> aTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTask = findViewById(R.id.Add);
        spnTaskOrDlt = findViewById(R.id.spin);
        btnAdd = findViewById(R.id.AddTask);
        btnDlt = findViewById(R.id.Remove);
        btnClr = findViewById(R.id.btnClearTask);
        lvTask = findViewById(R.id.lvTask);

        Task = new ArrayList<>();

        aTask = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Task);

        lvTask.setAdapter(aTask);

        spnTaskOrDlt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        etTask.setHint("Add new Task");
                        btnDlt.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;

                    case 1:
                        etTask.setHint("Type the index number of task in which you want to remove");
                        btnAdd.setEnabled(false);
                        btnDlt.setEnabled(true);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addTask = etTask.getText().toString();
                Task.add(addTask);
                aTask.notifyDataSetChanged();

            }
        });

        btnDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(etTask.getText().toString());
                pos = pos - 1;



                if (Task.isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "You don't have any task", Toast.LENGTH_LONG).show();
                } else if (pos > Task.size()) {
                    Toast.makeText(MainActivity.this, "Incorrect index number", Toast.LENGTH_LONG).show();
                } else {
                    Task.remove(pos);
                    aTask.notifyDataSetChanged();
                }

            }
        });

        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task.clear();
                aTask.notifyDataSetChanged();

            }
        });

    }
}

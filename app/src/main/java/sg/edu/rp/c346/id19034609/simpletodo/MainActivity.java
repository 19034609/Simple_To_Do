package sg.edu.rp.c346.id19034609.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spn;
    EditText etNewTask;
    Button btnAdd, btnDelete, btnClear;
    ListView lvTask;
    ArrayList<String> alTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn = findViewById(R.id.spinner);
        etNewTask = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAddTask);
        btnDelete = findViewById(R.id.buttonDeleteTask);
        btnClear = findViewById(R.id.buttonClearTask);
        lvTask = findViewById(R.id.listViewTask);

        alTask = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(adapter);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etNewTask.setHint(R.string.addTask);
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        btnClear.setEnabled(true);
                        break;
                    case 1:
                        etNewTask.setHint(R.string.removeTask);
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnClear.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNewTask.getText().toString().trim().length() != 0) {
                    String taskName = etNewTask.getText().toString();
                    alTask.add(taskName);
                    adapter.notifyDataSetChanged();
                    etNewTask.setText("");
                    etNewTask.setText("");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNewTask.getText().toString().trim().length() != 0) {
                    int pos = Integer.parseInt(etNewTask.getText().toString());
                    if (alTask.size() == 0) {
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    } else if (pos < alTask.size()) {
                        alTask.remove(pos);
                        adapter.notifyDataSetChanged();
                        etNewTask.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}

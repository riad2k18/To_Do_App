package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int Edit = 0;
    private Work_viewmodel work_viewmodel;
    private EditText editText, editText2, editText3;
    private Button button;
    public static final String id = "com.example.to_do_list.id";
    public static final String ed1 = "com.example.to_do_list.ed1";
    public static final String ed2 = "com.example.to_do_list.ed2";
    public static final String ed3 = "com.example.to_do_list.ed3";
    public static final String Extra_pri = "com.example.to_do_list.Extra_pri";
    private NumberPicker numberPicker;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteallmenu:
                work_viewmodel.deleteallworks();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        numberPicker = findViewById(R.id.numberpicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        button = findViewById(R.id.button4);
        final WorkAdapter adapter = new WorkAdapter();
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed0 = editText.getText().toString();
                String ed02 = editText2.getText().toString();
                String ed03 = editText3.getText().toString();
                int priority = numberPicker.getValue();

                Intent data = new Intent();
                data.putExtra(ed1, ed0);
                data.putExtra(ed2, ed02);
                data.putExtra(ed3, ed03);
                data.putExtra(Extra_pri, priority);
                Work note = new Work(ed0, ed02, ed03, priority);

                if (Edit != 0) {
                    Toast.makeText(MainActivity.this, " " + Edit, Toast.LENGTH_SHORT).show();
                    work_viewmodel.update(note);
                } else {



                    work_viewmodel.insert(note);
//                    Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, " " + Edit, Toast.LENGTH_SHORT).show();
                }

            }


        });

        work_viewmodel = ViewModelProviders.of(this).get(Work_viewmodel.class);
        work_viewmodel.getAllworks().observe(this, new Observer<List<Work>>() {
            @Override
            public void onChanged(List<Work> works) {
                adapter.setWorks(works);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                work_viewmodel.delete(adapter.getWorkAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new WorkAdapter.OnitemclickListener() {
            @Override
            public void onitemclick(Work work) {
//                Intent data = new Intent();
//
//                data.putExtra(MainActivity.id, work.getId());
//                data.putExtra(MainActivity.ed1, work.getEvent_name());
//                data.putExtra(MainActivity.ed2, work.getEvent_date());
//                data.putExtra(MainActivity.ed3, work.getEvent_duetime());
//                data.putExtra(MainActivity.Extra_pri, work.getPriority());
//                startActivityForResult(data,Edit);
////
//////
                editText.setText(work.getEvent_name());
                editText2.setText(work.getEvent_date());
                editText3.setText(work.getEvent_duetime());
                numberPicker.setValue(work.getPriority());
                Edit = 1;
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

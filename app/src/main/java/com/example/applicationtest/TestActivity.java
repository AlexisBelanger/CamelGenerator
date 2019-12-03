package com.example.applicationtest;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private Button addb ;
    private EditText et;
    private ListView lv;

    private ArrayList<String> values=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        this.addb = findViewById(R.id.buttonTodo);
        this.addb.setEnabled(false);
        this.et = findViewById(R.id.champ);
        this.lv = findViewById(R.id.liste);

        lv.setAdapter(new ArrayAdapter<String>(this,R.layout.todo_layout,R.id.title, values));
        et.setTextColor(Color.GRAY);

    }

    public void addTodo(View view){

        values.add(et.getText().toString());
        ((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
        addb.setEnabled(false);
        et.setText("enter todo");

    }

    public void enterText(View view){
        et.setTextColor(Color.BLACK);
        et.setText("");
        addb.setEnabled(true);
    }

    public void removeItem(View view){
        TextView t = (TextView)view;
        values.remove(t.getText());
        ((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();

    }


}

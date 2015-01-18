package com.unkani.placeholder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by default on 1/18/2015.
 */
public class HistoryActivity extends ActionBarActivity implements View.OnClickListener {

    TextView characterText;
    ListView mainListView;
    Button weekly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tell activity which XML layout to use
        setContentView(R.layout.activity_history);
        //Enable "up" button for navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*        characterText = (TextView) findViewById(R.id.characterText);
        String poop = this.getIntent().getExtras().getString("test", "1");
        Toast.makeText(this, poop, Toast.LENGTH_LONG).show();*/

        weekly = (Button) findViewById(R.id.Weekly);
        weekly.setOnClickListener(this);

    }

    public void onClick(View v) {
        Toast.makeText(this, "Clicked weekly!", Toast.LENGTH_LONG).show();

    }
}
package com.unkani.placeholder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;

public class CharacterActivity extends ActionBarActivity {

    TextView characterText;
    ListView mainListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tell activity which XML layout to use
        setContentView(R.layout.activity_character);
        //Enable "up" button for navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*        characterText = (TextView) findViewById(R.id.characterText);
        String poop = this.getIntent().getExtras().getString("test", "1");
        Toast.makeText(this, poop, Toast.LENGTH_LONG).show();*/



    }
/*    public void onClick(View v) {

    }*/
}

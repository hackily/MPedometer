package com.unkani.placeholder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by unkani on 1/17/2015.
 */
public class QuestsActivity extends ActionBarActivity implements View.OnClickListener {
    TextView questsTextView;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList questList;
    Button generator;
    Button selecter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tell activity which XML layout to use
        setContentView(R.layout.activity_quests);
        //Enable "up" button for navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        questsTextView = (TextView) findViewById(R.id.quests_textview);
        String poop = this.getIntent().getExtras().getString("test", "1");
        Toast.makeText(this, poop, Toast.LENGTH_LONG).show();
        selecter = (Button) findViewById(R.id.quest_selecter);
        selecter.setOnClickListener(this);
        generator= (Button) findViewById(R.id.quest_generator);
        generator.setOnClickListener(this);
        selecter.setEnabled(false);
        String aMonsters[]={"Bat","Troll","Ogre","Assassin"};


// get a branch kk




    }
    public void onClick(View v) {
        if (v==generator) {
        }
    else if (v==selecter) {

        }
    }


}

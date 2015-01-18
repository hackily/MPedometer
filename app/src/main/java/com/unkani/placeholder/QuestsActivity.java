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
import java.util.Random;

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
    Random picker;
    String [] aMonsters;



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
        aMonsters=new String[4];
        aMonsters[0]=new String("Bat");
        aMonsters[1]=new String("Troll");
        aMonsters[2]=new String("Ogre");
        aMonsters[3]=new String("Assassin");
        mainListView= (ListView) findViewById(R.id.quest_lister);
        mArrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, questList);
        mainListView.setAdapter(mArrayAdapter);
        picker= new Random();


    }
    public void onClick(View v) {
        if (v==generator) {
            questList.add(aMonsters[picker.nextInt(4)]);
            mArrayAdapter.notifyDataSetChanged();
        }
    else if (v==selecter) {
        }
    }


}

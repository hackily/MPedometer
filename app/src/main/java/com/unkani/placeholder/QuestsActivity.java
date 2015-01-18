package com.unkani.placeholder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class QuestsActivity extends ActionBarActivity implements View.OnClickListener {
    TextView questsTextView;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList questList;
    Button generator;
    Button selector;
    Random picker;
    String[] aMonsters = { "Bat", "Troll", "Ogre", "Assassin" };


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
        selector = (Button) findViewById(R.id.quest_selecter);
        selector.setOnClickListener(this);
        generator= (Button) findViewById(R.id.quest_generator);
        generator.setOnClickListener(this);
        selector.setEnabled(false);

        mainListView = (ListView) findViewById(R.id.quest_lister);

/*
        mainListView.setAdapter(mArrayAdapter);
*/

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        picker= new Random();


// get a branch kk




    }
    public void onClick(View v) {
        if (v==generator) {
/*
            questList.add(aMonsters[picker.nextInt(3)]);
*/
            for(int i = 0; i < 3; i++) {
                questList.add(aMonsters[i]);
            }

            mArrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, questList);

            mArrayAdapter.notifyDataSetChanged();
            selector.setEnabled(true);
        }
        else if (v==mainListView) {
        }
    }


}

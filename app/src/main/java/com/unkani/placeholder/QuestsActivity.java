package com.unkani.placeholder;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    ArrayList<String> questList = new ArrayList<String>(4);
    Button generator;
    Button selector;
    Random picker;
    String[] aMonsters = { "Lvl 3 Bat", "Lvl 10 Troll", "Lvl 15 Ogre", "Lvl 20 Assassin" };
    int position;


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
        mArrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, questList);

        mainListView.setAdapter(mArrayAdapter);


        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            position = pos;
            }
        });
        picker= new Random();

    }
    public void onClick(View v) {
        if (v==generator) {

            questList.add(aMonsters[picker.nextInt(4)]);



            mArrayAdapter.notifyDataSetChanged();
/*
            mArrayAdapter.add(questList);
*/
            selector.setEnabled(true);
        }
        else if (v==mainListView) {
        }
        else if (v == selector){

            AlertDialog alert = new AlertDialog.Builder(this)
                    .setTitle("Quest Dialogue")
                    .setMessage("Are you sure you want to fight a "+ questList.get(mainListView.getCheckedItemPosition()) + "?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // ok button
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // cancel button
                        }
                    }).create();
            alert.show();

        }
    }


}

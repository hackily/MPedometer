package com.unkani.placeholder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuestsActivity extends ActionBarActivity implements View.OnClickListener {
    TextView quests_textview;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList<String> questList = new ArrayList<String>(4);
    Button generator;
    Button selector;
    Random picker;
    String[] aMonsters = { "Lvl 3 Bat", "Lvl 10 Troll", "Lvl 15 Ogre", "Lvl 20 Assassin" };
    int position;


    private static final String QUESTPREFS = "prefs";
    private static final String COUNTDOWN = "0";
    SharedPreferences mSharedPreferences;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tell activity which XML layout to use
        setContentView(R.layout.activity_quests);
        //Enable "up" button for navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

  /*      questsTextView = (TextView) findViewById(R.id.quests_textview);

        String name = mSharedPreferences.getString(COUNTDOWN, "");
        if(Integer.toString(name)  0 )*/

        quests_textview = (TextView) findViewById(R.id.quests_textview);
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
                            String read = questList.get(mainListView.getCheckedItemPosition());
                            Pattern p = Pattern.compile("(\\\\d+)");
                            Matcher m = p.matcher(read);
                            quests_textview.setText(read);

                            int questGoal = 0;
                            if (m.find()) {
                                questGoal = Integer.valueOf(m.group(1))*500;
                                quests_textview.setText(questGoal);
                            }


                            mSharedPreferences = getSharedPreferences(QUESTPREFS, MODE_PRIVATE);
                            String monster = mSharedPreferences.getString(COUNTDOWN, "-1");
                            if (Integer.valueOf(monster) < 0) {
                                SharedPreferences.Editor e = mSharedPreferences.edit();
                                e.putString(COUNTDOWN, Integer.toString(questGoal));
                                e.commit();
                            }



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

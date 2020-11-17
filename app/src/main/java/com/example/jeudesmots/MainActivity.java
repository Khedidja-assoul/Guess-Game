package com.example.jeudesmots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DB_Manager db_manager;
    TextView name, level, score, category;
    EditText letter1, letter2, letter3, letter4, letter5, letter6, letter7;
    ImageView img, frame, frame_avatare, emoji;
    Button btn ;
    List<String> words = new ArrayList<>();
    List<EditText> editTextList = new ArrayList<>();
    String letter;
    String theName;
    Integer theScore;
    Integer theLevel;
    String theCategory = "";
    String categ ;
    String word ;
   // ImageManager imageManager = new ImageManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db_manager = new DB_Manager(getApplicationContext());

        theName = db_manager.getPlayer().getName();
        theScore = db_manager.getPlayer().getScore();
        //theLevel = db_manager.getPlayer().getLevel();


        name = findViewById(R.id.name);
        name.setText(theName);

        frame_avatare = findViewById(R.id.frame_avatare);
        frame_avatare.setImageResource(R.drawable.frame_avatare);



       /* category = findViewById(R.id.category);
        if(theLevel==1){
            theCategory = "Fruits";
            categ = "fruit";
        }
        else{
            theCategory = "Animaux";
            categ = "animal";
        }*/

        /*category.setText(theCategory);
        words.addAll(db_manager.getList(categ));

        level = findViewById(R.id.level);
        level.setText(theLevel.toString());
        */


        score = findViewById(R.id.score);
        score.setText(theScore.toString()+" / "+words.size());


        frame = findViewById(R.id.frame);
        frame.setImageResource(R.drawable.flower);

        //img = findViewById(R.id.img);
//        img.setImageResource(R.drawable.qst);

      //  emoji = findViewById(R.id.emoji);

        letter1 = findViewById(R.id.letter1);
        letter2 = findViewById(R.id.letter2);
        letter3 = findViewById(R.id.letter3);
        letter4 = findViewById(R.id.letter4);
        letter5 = findViewById(R.id.letter5);
        letter6 = findViewById(R.id.letter6);
        letter7 = findViewById(R.id.letter7);

        editTextList.add(letter1);
        editTextList.add(letter2);
        editTextList.add(letter3);
        editTextList.add(letter4);
        editTextList.add(letter5);
        editTextList.add(letter6);
        editTextList.add(letter7);

        Question(theScore);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reponse = "";

                for (EditText editText : editTextList){
                    if(editText.getText()!=null)
                        reponse =reponse+""+editText.getText().toString();
                }

                //emoji.setVisibility(View.VISIBLE);

                if(reponse.equals(words.get(theScore))){

                   // emoji.setImageResource(R.drawable.happy);

                    theScore++;
                    /*if(theScore==words.size() && theLevel==1){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Bravo!",Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this,"Vous etes dans le 2 eme niveau",Toast.LENGTH_LONG).show();
                                theLevel++;
                                theCategory = "Animaux";
                                words= new ArrayList<>();
                                words.addAll(db_manager.getList("animal"));
                                level.setText(theLevel.toString());
                                category.setText(theCategory);
                                theScore = 0 ;
                            }
                        }, 3000);

                    }*/
                    score.setText(theScore+" / "+words.size());
                   // img.setImageResource(imageManager.getImage(word));

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            emoji.setVisibility(View.INVISIBLE);
                            img.setImageResource(R.drawable.qst);
                            Question(theScore);
                        }
                    }, 3000);
                }
                else{
                   // emoji.setImageResource(R.drawable.sad);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // emoji.setVisibility(View.INVISIBLE);
                        }
                    }, 2000);
                }

            }

        };

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(onClickListener);

    }

    public void Question(int score){
        word = words.get(score);
        for(EditText editText : editTextList){
            editText.setVisibility(View.VISIBLE);
            editText.setText("");
        }
        for(int i = 0 ; i < word.length() ; i++){
            if(i%2 == 0){
                letter = String.valueOf(word.charAt(i));
                editTextList.get(i).setText(letter);
            }
        }
        for(int i = word.length() ; i < 7 ; i++){
            editTextList.get(i).setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db_manager.update(theScore,theLevel);

    }

}

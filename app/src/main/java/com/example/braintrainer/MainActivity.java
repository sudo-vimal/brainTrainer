package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button goButton, button0, button1, button2, button3, playAgainButton;


    ConstraintLayout secondConstraintLayout;
    int score=0, totalQuestionsAsked;

    TextView sumTextView,scoreTextView, timerTextView;
    int a,b;



    CountDownTimer countDownTimer;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    TextView resultTextView ;

    int locationOfCorrectAnswer;



    public void generateQuestion()
    {
        totalQuestionsAsked++;

        Random random = new Random();
        a= random.nextInt(31);
        b= random.nextInt(31);

        while(a==0)
            a= random.nextInt(31);
        while(b==0)
            b= random.nextInt(31);

        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        sumTextView.setVisibility(View.VISIBLE);


        locationOfCorrectAnswer = random.nextInt(4);
        int wrongAnswer=random.nextInt(61);

        for (int i=0; i<4;i++)
        {
            if (i!=locationOfCorrectAnswer) {




                while(wrongAnswer==a+b || answers.contains(wrongAnswer))
                {
                    wrongAnswer=random.nextInt(61);
                }





                answers.add(wrongAnswer);
            }



            else
            {
                answers.add(a+b);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));




    }

    public void start(View view)
    {
       goButton.setVisibility(View.INVISIBLE);




       generateQuestion();

       countDownTimer = new CountDownTimer(30500, 1000) {
           @Override
           public void onTick(long millisUntilFinished) {
               timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
           }

           @Override
           public void onFinish() {


               playAgainButton.setVisibility(View.VISIBLE);

               button0.setEnabled(false);
               button1.setEnabled(false);
               button2.setEnabled(false);
               button3.setEnabled(false);

               answers.clear();

               resultTextView.setVisibility(View.INVISIBLE);


           }
       }.start();

       secondConstraintLayout.setVisibility(View.VISIBLE);




    }

    public void chooseAnwer(View view)
    {

       if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
       {

           resultTextView.setText("Correct!");
           resultTextView.setVisibility(View.VISIBLE);
           score++;

           scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestionsAsked));

           answers.clear();


           generateQuestion();
       }
       else
       {
           resultTextView.setText("Incorrect!");
           resultTextView.setVisibility(View.VISIBLE);

           scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestionsAsked));

           answers.clear();
           generateQuestion();
       }


    }



    public void playAgain(View view)
    {

        playAgainButton.setVisibility(View.INVISIBLE);
        score=0;
        totalQuestionsAsked=0;
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        generateQuestion();
        countDownTimer.start();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        secondConstraintLayout= findViewById(R.id.secondConstraintLayout);
        secondConstraintLayout.setVisibility(View.INVISIBLE);

        playAgainButton=findViewById(R.id.playAgainButton);

        sumTextView = findViewById(R.id.sumTextView);

        button0 = findViewById(R.id.button0);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        timerTextView= findViewById(R.id.timerTextView);


        scoreTextView= findViewById(R.id.scoreTextView);

        resultTextView= findViewById(R.id.resultTextView);
        resultTextView.setVisibility(View.INVISIBLE);

        goButton=findViewById(R.id.goButton);

        //gridLayout = findViewById(R.id.gridLayout);







    }
}

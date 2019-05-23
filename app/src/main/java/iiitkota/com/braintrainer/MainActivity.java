package iiitkota.com.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button Button0;
    Button Button1;
    Button Button2;
    Button Button3;
    Button playAgainButton;
    TextView question;
    TextView rightOrWrong;
    TextView scoreTextView;
    TextView TimeRemaining;
    ConstraintLayout gameLayout;
    MediaPlayer mediaPlayer;
    MediaPlayer horn;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int LocationOfCorrectAnswer;
    int IncorrectAnswer;
    int score = 0;
    int totalQues = 0;

    public void generateQuestion()
    {
        Random rand = new Random();
        int num1 = rand.nextInt(30);
        int num2 = rand.nextInt(30);
        question.setText(Integer.toString(num1)+"+"+Integer.toString(num2));
        LocationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for(int i=0;i<4;i++)
        {
            if(i == LocationOfCorrectAnswer)
            {
                answers.add(num1+num2);
            }
            else
            {
                IncorrectAnswer = rand.nextInt(30);

                while(IncorrectAnswer == num1+num2)
                {
                    IncorrectAnswer = rand.nextInt(30);
                }
                answers.add(IncorrectAnswer);
            }
        }

        Button0.setText(Integer.toString(answers.get(0)));
        Button1.setText(Integer.toString(answers.get(1)));
        Button2.setText(Integer.toString(answers.get(2)));
        Button3.setText(Integer.toString(answers.get(3)));
    }

    public void hide(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        generateQuestion();

        new CountDownTimer(30000+100,1000)
        {
            @Override
            public void onFinish() {
                TimeRemaining.setText("0s");
                rightOrWrong.setText("Your Score is :"+ Integer.toString(score)+"/"+Integer.toString(totalQues));
                playAgainButton.setVisibility(View.VISIBLE);
                Button0.setEnabled(false);
                Button1.setEnabled(false);
                Button2.setEnabled(false);
                Button3.setEnabled(false);
                horn.start();

            }

            @Override
            public void onTick(long millisUntilFinished) {
                TimeRemaining.setText(Long.toString(millisUntilFinished/1000)+"s");
                mediaPlayer.start();
            }
        }.start();
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(LocationOfCorrectAnswer)))
        {
           score++;
           rightOrWrong.setText("Correct!");
        }
        else
        {
            rightOrWrong.setText("Wrong!");

        }
        totalQues++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQues));
        generateQuestion();
    }

    public void PlayAgain(View view)
    {
        score=0;
        totalQues=0;
        TimeRemaining.setText("0s");
        scoreTextView.setText("0/0");
        rightOrWrong.setText("");
        Button0.setEnabled(true);
        Button1.setEnabled(true);
        Button2.setEnabled(true);
        Button3.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30000+100,1000)
        {
            @Override
            public void onFinish() {
                TimeRemaining.setText("0s");
                rightOrWrong.setText("Your Score is :"+ Integer.toString(score)+"/"+Integer.toString(totalQues));
                Button0.setEnabled(false);
                Button1.setEnabled(false);
                Button2.setEnabled(false);
                Button3.setEnabled(false);
                playAgainButton.setVisibility(View.VISIBLE);
                horn.start();

            }

            @Override
            public void onTick(long millisUntilFinished) {
                TimeRemaining.setText(Long.toString(millisUntilFinished/1000)+"s");
                mediaPlayer.start();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        question = (TextView) findViewById(R.id.question);
        rightOrWrong = (TextView) findViewById(R.id.rightOrWrong);
        scoreTextView = (TextView) findViewById(R.id.score);
        TimeRemaining = findViewById(R.id.TimeRemaining);
        Button0 = (Button) findViewById(R.id.button0);
        Button1 = (Button) findViewById(R.id.button1);
        Button2 = (Button) findViewById(R.id.button2);
        Button3 = (Button) findViewById(R.id.button3);
        gameLayout = findViewById(R.id.gameLayout);
        playAgainButton = findViewById(R.id.playAgainButton);
        mediaPlayer = MediaPlayer.create(this,R.raw.tick);
        horn = MediaPlayer.create(this,R.raw.buzzer);


    }
}

package com.example.android.chickenmaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Initializes question variable to track which question the user is currently on
     * 1 = Grilled Chicken
     * 2 = Chicken Cordon Bleu
     * 3 = Chicken Tenders
     * 4 = Boneless Chicken Wings
     * 5 = Chicken Piccata
     * 6 = Congratulations Message
     */
    int questionNum = 1;

    /**
     * TODO: maintain correct answer when using "Prev" button
     * TODO: add "start over" button to congratulations screen
     * TODO: add sounds
     * TODO: other stuff
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setQuestion() {

        ImageView imagePic = (ImageView) findViewById(R.id.image);
        TextView title = (TextView) findViewById(R.id.title);
        TextView question = (TextView) findViewById(R.id.question);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
        RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
        RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);
        Button next = (Button) findViewById(R.id.next);
        Button prev = (Button) findViewById(R.id.prev);

        String title_string = "null";
        String question_string = "null";
        String answer1_string = "null";
        String answer2_string = "null";
        String answer3_string = "null";

        if (questionNum == 1) {
            imagePic.setImageResource(R.drawable.grilled);
            title_string = getString(R.string.title_grilled);
            question_string = getString(R.string.question_grilled);
            answer1_string = getString(R.string.answer1_grilled);
            answer2_string = getString(R.string.answer2_grilled);
            answer3_string = getString(R.string.answer3_grilled);

            /** Removes "Prev" button if user navigated back from question 2 */
            prev.setVisibility(Button.GONE);
        }

        if (questionNum == 2) {
            imagePic.setImageResource(R.drawable.bleu);
            title_string = getString(R.string.title_bleu);
            question_string = getString(R.string.question_bleu);
            answer1_string = getString(R.string.answer1_bleu);
            answer2_string = getString(R.string.answer2_bleu);
            answer3_string = getString(R.string.answer3_bleu);

            /** adds "Prev" button once first question is answered correctly */
            prev.setVisibility(Button.VISIBLE);
        }

        if (questionNum == 3) {
            imagePic.setImageResource(R.drawable.tenders);
            title_string = getString(R.string.title_tenders);
            question_string = getString(R.string.question_tenders);
            answer1_string = getString(R.string.answer1_tenders);
            answer2_string = getString(R.string.answer2_tenders);
            answer3_string = getString(R.string.answer3_tenders);
        }

        if (questionNum == 4) {
            imagePic.setImageResource(R.drawable.wings);
            title_string = getString(R.string.title_wings);
            question_string = getString(R.string.question_wings);
            answer1_string = getString(R.string.answer1_wings);
            answer2_string = getString(R.string.answer2_wings);
            answer3_string = getString(R.string.answer3_wings);
        }

        if (questionNum == 5) {
            imagePic.setImageResource(R.drawable.piccata);
            title_string = getString(R.string.title_piccata);
            question_string = getString(R.string.question_piccata);
            answer1_string = getString(R.string.answer1_piccata);
            answer2_string = getString(R.string.answer2_piccata);
            answer3_string = getString(R.string.answer3_piccata);

            /** adds Radio Group and "Next" button for case where user navigated back with "Prev" button */
            radioGroup.setVisibility(RadioGroup.VISIBLE);
            next.setVisibility(Button.VISIBLE);

        }

        if (questionNum == 6) {
            imagePic.setImageResource(R.drawable.contgratulations);
            title_string = getString(R.string.title_congrats);
            question_string = getString(R.string.question_congrats);

            /**Remove Radio Group and "Next" button on congratulations screen */
            radioGroup.setVisibility(RadioGroup.GONE);
            next.setVisibility(Button.GONE);
        }

        title.setText(title_string);
        question.setText(question_string);
        radio1.setText(answer1_string);
        radio2.setText(answer2_string);
        radio3.setText(answer3_string);
        radioGroup.clearCheck();
    }

    /**
     * Method called when "Next" button is clicked
     * checks whether currently selected answer is correct, advances to next question if so
     * if answer is incorrect, displays toast prompting to try again
     */
    public void clickNextButton(View view) {
        boolean answerCheck = checkAnswer();
        if (answerCheck) {
            questionNum += 1;
            setQuestion();
        }
    }

    /**
     * Helper method for clickNextButton
     * Checks to see if answer to current question is correct
     * Returns boolean "answerCheck" to indicate if correct answer was given
     */

    public boolean checkAnswer() {
        RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
        boolean isSelected1 = radio1.isChecked();

        RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
        boolean isSelected2 = radio2.isChecked();

        RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);
        boolean isSelected3 = radio3.isChecked();

        boolean isCorrect;
        if (((questionNum == 1) && (isSelected2)) ||
                ((questionNum == 2) && (isSelected1)) ||
                ((questionNum == 3) && (isSelected2)) ||
                ((questionNum == 4) && (isSelected3)) ||
                ((questionNum == 5) && (isSelected1))
                ) {
            Toast.makeText(this, "You got it!", Toast.LENGTH_SHORT).show();
            isCorrect = true;
        } else {
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
            isCorrect = false;
        }
        return isCorrect;
    }

    /**
     * Method called when "Prev" button is clicked
     * returns use to previous question
     */

    public void clickPrevButton(View view) {
        questionNum -= 1;
        setQuestion();
    }

    /**
    public void resetRadioButtons(RadioButton radio1, RadioButton radio2, RadioButton radio3){
        radio1.setChecked(false);
        radio2.setChecked(false);
        radio3.setChecked(false);
    } */
}

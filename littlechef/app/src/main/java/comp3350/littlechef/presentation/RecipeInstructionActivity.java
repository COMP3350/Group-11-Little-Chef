package comp3350.littlechef.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import comp3350.littlechef.R;
import comp3350.littlechef.objects.Recipe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class RecipeInstructionActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;
    private TextView timerText;
    private Button stopStartButton;
    private Button finishButton;
    private boolean timerStarted;

    private Timer timer;
    private TimerTask timerTask;
    int time; //total seconds of time passed once start button is pressed

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instruction);

        //getting timer variables up
        timerText  = (TextView) findViewById(R.id.timer_text);
        stopStartButton = (Button) findViewById(R.id.start_stop_timer);
        finishButton = (Button) findViewById(R.id.finish_cooking_button);

        timerStarted = false;
        time = 0;
        timer = new Timer();
        finishButton.setEnabled(false); //don't allow user to finish until at least starting to cook

        //get the selected recipe that was clicked from previous activity
        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity

        final ListView listView = (ListView) findViewById(R.id.instruction_list_view);

        ArrayAdapter<String[]> instructionsArrayAdapter = new ArrayAdapter<String[]>(this,android.R.layout.simple_list_item_1, selectedRecipe.getInstructions())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                String[] instruction = getItem(position);
                String number = (position + 1) + "."; //get the order number of the instruction
                SpannableString numberBold;
                SpannableString mainInstructionBold;

                if(convertView == null)
                {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.instruction_card,parent, false);
                }

                //make the number bold
                numberBold = new SpannableString(number);
                numberBold.setSpan(new StyleSpan(Typeface.BOLD), 0, numberBold.length(), 0);

                //make main instruction bold
                mainInstructionBold = new SpannableString(instruction[0]);
                mainInstructionBold.setSpan(new StyleSpan(Typeface.BOLD), 0, mainInstructionBold.length(), 0);

                TextView mainInstruction = (TextView) convertView.findViewById(R.id.instruction);
                TextView subInstruction = (TextView) convertView.findViewById(R.id.sub_instruction);
                TextView numberOfInstruction = (TextView) convertView.findViewById(R.id.instruction_number);

                mainInstruction.setText(mainInstructionBold);
                subInstruction.setText(instruction[1]);
                numberOfInstruction.setText(numberBold);

                return convertView;
            }
        };
        listView.setAdapter(instructionsArrayAdapter);
    }

    public void startStopClicked(View view)
    {
        if(!timerStarted)
        {
            timerStarted = true;
            changeStartButton(R.string.stop, R.color.red);

            startTimer();
        }

        else
        {
            timerStarted = false;
            changeStartButton(R.string.start, R.color.dark_green);
            finishButton.setEnabled(true); //user have stopped cooking, so user can press finish now and proceed to rating

            timerTask.cancel();
        }
    }



    public void resetClicked(View view)
    {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are you sure you want to reset the timer?");

        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(timerTask != null)
                {
                    timerTask.cancel();
                    time = 0;
                    timerStarted = false;
                    finishButton.setEnabled(false); //user has pressed reset, cannot finish until started and stopped cooking
                    timerText.setText(formatTime(0,0,0));
                    changeStartButton(R.string.start, R.color.dark_green);
                }
            }
        });

        resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //do nothing
            }
        });

        resetAlert.show();
    }

    public void finishClicked(View view)
    {

    }


    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }
        };
        //fixed rate goes by 1000 milliseconds which is 1 second, so for each run execution - passes 1 second of time
        timer.scheduleAtFixedRate(timerTask, 0 , 1000);
    }

    private String getTimerText()
    {
        //getting the seconds, minutes and hours from total seconds
        int seconds = ((time % 86400) % 3600) % 60;
        int minutes = ((time % 86400) % 3600) / 60;
        int hours = ((time % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        return String.format(Locale.CANADA,"%02d",hours) + ":" + String.format(Locale.CANADA,"%02d",minutes) + ":" + String.format(Locale.CANADA,"%02d",seconds);
    }

    private void changeStartButton(int textOnButton, int color)
    {
        stopStartButton.setText(textOnButton);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }
}

//TODO make finish button go to rate activity and save time into recipe once clicked
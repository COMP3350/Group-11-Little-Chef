package comp3350.littlechef.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.business.TimeRecipe;
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

import java.util.Timer;
import java.util.TimerTask;

// CLASS: RecipeInstructionActivity.java
// REMARKS: This class show the instructins for a recipe.
//-----------------------------------------
public class RecipeInstructionActivity extends AppCompatActivity
{
    private AccessRecipes accessRecipes;

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

        accessRecipes = new AccessRecipes();

        //getting timer variables up
        timerText  = findViewById(R.id.timer_text);
        stopStartButton = findViewById(R.id.start_stop_timer);
        finishButton = findViewById(R.id.finish_cooking_button);

        timerStarted = false;
        time = 0;
        timer = new Timer();
        finishButton.setEnabled(false); //don't allow user to finish until at least starting to cook

        //get the selected recipe that was clicked from previous activity
        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity

        final ListView listView = findViewById(R.id.instruction_list_view);

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

                TextView mainInstruction = convertView.findViewById(R.id.instruction);
                TextView subInstruction = convertView.findViewById(R.id.sub_instruction);
                TextView numberOfInstruction = convertView.findViewById(R.id.instruction_number);

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

        resetAlert.setPositiveButton("Reset", (dialog, which) -> {
            if(timerTask != null)
            {
                timerTask.cancel();
                time = 0;
                timerStarted = false;
                finishButton.setEnabled(false); //user has pressed reset, cannot finish until started and stopped cooking
                timerText.setText(TimeRecipe.timerTimeFormat(0,0,0));
                changeStartButton(R.string.start, R.color.dark_green);
            }
        });

        resetAlert.setNeutralButton("Cancel", (dialog, which) -> {
            //do nothing
        });

        resetAlert.show();
    }

    public void finishClicked(View view)
    {
        String result;
        selectedRecipe.addCookingTime(time);
        result = accessRecipes.updateRecipe(selectedRecipe);
        if (result == null)
        {
            Intent cookedMealCongrats = new Intent(this, CookedAnotherMealActivity.class);
            cookedMealCongrats.putExtra("id", selectedRecipe); //pass the object reference to another activity
            startActivity(cookedMealCongrats);
            finish();
        }
        else
        {
            Messages.fatalError(this, result);
        }
    }



    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(() -> {
                    time++;
                    timerText.setText(TimeRecipe.totalSecondsToString(time, false));
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000 , 1000);
    }

    private void changeStartButton(int textOnButton, int color)
    {
        stopStartButton.setText(textOnButton);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }
}
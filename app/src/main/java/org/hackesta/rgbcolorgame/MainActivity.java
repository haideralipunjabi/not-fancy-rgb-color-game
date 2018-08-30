package org.hackesta.rgbcolorgame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    Button btn_mode, btn_retry;
    ArrayList<Button> colorButtons = new ArrayList<>();
    ArrayList<Integer> colors = new ArrayList<>();
    TextView tv_title;
    LinearLayout layout_colors;

    int COLORCOUNT_EASY = 3;
    int COLORCOUNT_HARD = 6;
    int settings_colorcount = COLORCOUNT_HARD;
    int correct_color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        setupColors();
        setupColorButtons();
    }

    private void setup(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_mode = findViewById(R.id.btn_mode);
        btn_retry = findViewById(R.id.btn_retry);
        tv_title = findViewById(R.id.tv_title);
        layout_colors = findViewById(R.id.layout_colors);

        btn_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean becomeEasy = (btn_mode.getText().toString() == getString(R.string.btn_mode_text_easy));
                btn_mode.setText(becomeEasy ? R.string.btn_mode_text_hard: R.string.btn_mode_text_easy);
                settings_colorcount = becomeEasy ? 3 :6;
                setupColors();
                setupColorButtons();
            }
        });

        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupColors();
                setupColorButtons();
            }
        });
    }

    private void setupColors(){
        colors.clear();
        Random rnd = new Random();
        for(int i = 0; i < settings_colorcount; i++){
            colors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        }
        correct_color = rnd.nextInt(settings_colorcount);
        tv_title.setText(getString(R.string.title,getColorString(colors.get(correct_color))));
    }

    private void setupColorButtons(){
        layout_colors.removeAllViews();
        colorButtons.clear();
        for(int i = 0; i < settings_colorcount; i++){
            Button button = new Button(this);
            button.setBackgroundColor(colors.get(i));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1.0f / settings_colorcount;
            button.setLayoutParams(lp);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((ColorDrawable)v.getBackground()).getColor() == colors.get(correct_color)){
                        ((Button)v).setText(R.string.msg_correct);
                        v.setBackgroundColor(getResources().getColor(R.color.transparent));
                    }
                    else{
                        ((Button)v).setText(R.string.msg_wrong);
                        v.setBackgroundColor(getResources().getColor(R.color.transparent));
                        if(getWrongAttempts() > settings_colorcount / 2 || hasCorrectAttempt()){
                            setupColors();
                            setupColorButtons();
                        }
                    }
                }
            });
            layout_colors.addView(button);
            colorButtons.add(button);
        }
    }

    private String getColorString(int color){
        return "RGB(" +
                String.valueOf(Color.red(color))  + ", "
            +   String.valueOf(Color.green(color))+ ", "
            +   String.valueOf(Color.blue(color)) + ")";
    }
    private int getWrongAttempts(){
        int count = 0;
        for(Button btn : colorButtons){
            if(btn.getText().toString() == getString(R.string.msg_wrong)){
                count++;
            }
        }
        return  count;
    }

    private boolean hasCorrectAttempt(){
        for(Button btn : colorButtons){
            if(btn.getText().toString() == getString(R.string.msg_correct)){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            startActivity(new Intent(this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

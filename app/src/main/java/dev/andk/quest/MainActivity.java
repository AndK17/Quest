package dev.andk.quest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button[] choices = new Button[3];
    TextView console, resource;
    Story story;
    Player character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choices[0] = findViewById(R.id.button1);
        choices[1] = findViewById(R.id.button2);
        choices[2] = findViewById(R.id.button3);
        for(Button b: choices){
            b.setOnClickListener(this);
        }
        console = findViewById(R.id.text1);
        resource = findViewById(R.id.resourceText);
        character = new Player("qwerty");
        story = new Story(this);
        console.setText("============" + story.current_situation.subject + "============\n" + story.current_situation.text);
    }

    @Override
    public void onClick(View v) {
        int choice = 0;
        if(v.getId() == R.id.button1)
            choice = 1;
        if(v.getId() == R.id.button2)
            choice = 2;
        if(v.getId() == R.id.button3)
            choice = 3;
        character.F += story.current_situation.dF;
        character.M += story.current_situation.dM;
        character.R += story.current_situation.dR;
        resource.setText("Фанаты:" + character.F + "    Баланс:" + character.M + "$    Репутация:" + character.R);
        if (story.isEnd()) {
            console.setText("==========Конец игры==========");
            return;
        }
        story.go(choice);
        console.setText("============" + story.current_situation.subject + "============\n" + story.current_situation.text);
    }
}
package com.example.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    Button startGame;

    public static final String EXTRA_MESSAGE = "com.example.myfirstapplication";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame = (Button)findViewById(R.id.button);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.stats:
                Intent intent1 = new Intent(this, Instructions.class);
                this.startActivity(intent1);
                return true;
            case R.id.about:
                Intent intent2 = new Intent(this, About.class);
                this.startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void startGame()
    {
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }
}

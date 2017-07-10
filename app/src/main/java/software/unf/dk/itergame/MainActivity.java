package software.unf.dk.itergame;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GameCanvas gameCanvas;
    private List<GameObject> entities = Collections.synchronizedList(new ArrayList<GameObject>());
    private List<GameObject> toAdd = new ArrayList<>();
    private ArrayList<GameObject> toDelete = new ArrayList<>();
    private ArrayList<Integer> toDeleteIndex = new ArrayList<>();
    private GameMap current;
    private Button up, down, left, right;
    private GameStory gameStory;
    private GamePlayer gamePlayer;
    private ProgressBar healthBar;
    private TextView squattle, youDied;
    //Sæt denne til true hvis appen ikke skal virke
    private boolean glitch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.Theme_AppCompat_NoActionBar);

        gameCanvas = new GameCanvas(this, this);

        setContentView(R.layout.activity_main);
        RelativeLayout view = (RelativeLayout) findViewById(R.id.layout);
        view.addView(gameCanvas, 0);

        //Knapper
        up = (Button) findViewById(R.id.Up);
        down = (Button) findViewById(R.id.Down);
        left = (Button) findViewById(R.id.Left);
        right = (Button) findViewById(R.id.Right);
        createListeners();

        GameMap entrance = new GameMap(0, 0, this, 65, 118, MapName.entrance);
        current = entrance;
        entities.add(entrance);

        healthBar = (ProgressBar) findViewById(R.id.progressBar);
        healthBar.setMax(100);
        healthBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        squattle = (TextView) findViewById(R.id.squattle);
        youDied = (TextView) findViewById(R.id.youDied);
        youDied.setWillNotDraw(true);

        //Når appen starter, begynder man i mappet "entrance"
    }

    public List<GameObject> getEntities() {
        return entities;
    }

    public GameCanvas getGameCanvas() {
        return gameCanvas;
    }

    //TODO: Fix
    public GameMap getCurrentGameMap() {
        return current;
    }

    public void createListeners(){
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    gamePlayer.setVelocity(0, 1);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && gamePlayer.getVelX() == 0 && gamePlayer.getVelY() == 1) {

                    gamePlayer.setVelocity(0, 0);
                    return true;
                }
                return true;
            }
        });
        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    gamePlayer.setVelocity(0, -1);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && gamePlayer.getVelX() == 0 && gamePlayer.getVelY() == -1) {
                    gamePlayer.setVelocity(0, 0);
                    return true;
                }
                return true;
            }

        });
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    gamePlayer.setVelocity(-1, 0);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && gamePlayer.getVelX() == -1 && gamePlayer.getVelY() == 0) {
                    gamePlayer.setVelocity(0, 0);
                    return true;
                }
                return true;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    gamePlayer.setVelocity(1, 0);
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP && gamePlayer.getVelX() == 1 && gamePlayer.getVelY() == 0) {
                    gamePlayer.setVelocity(0, 0);
                    return true;
                }
                return true;
            }
        });
    }

    public void spawn(View view){
        gamePlayer.goToSpawn();
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setHealthText(int health){
        healthBar.setProgress(health);
    }

    public void attack(View view){
        gamePlayer.attack();
    }

    public void remove(GameObject object){

        toDelete.add(object);

        /*Iterator<GameObject> iter = entities.iterator();


        while (iter.hasNext()) {
            GameObject gameObject = iter.next();

            if (object == gameObject) {
                Log.i("Killed", "" + object);
                iter.remove();
            }
        }*/

    }

    public void removeSquattle(){
        squattle.setWillNotDraw(true);
    }

    public void enableDiedText(){
        youDied.setWillNotDraw(false);
    }

    public void disableDiedText(){
        youDied.setWillNotDraw(true);
    }

    public void addEntity(GameObject o){
        toAdd.add(o);
    }

    public void addFromQueue(){
        entities.addAll(toAdd);
        if(!glitch) {
            toAdd.clear();
        }
    }

    public void deleteQueue(){
        entities.removeAll(toDelete);
        toDelete.clear();
    }
}

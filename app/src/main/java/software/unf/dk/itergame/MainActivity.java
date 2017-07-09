package software.unf.dk.itergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GameCanvas gameCanvas;
    private ArrayList<GameObject> entities = new ArrayList<>();
    private GameMap current;
    private Button up, down, left, right;
    private GameStory gameStory;
    private GamePlayer gamePlayer;

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

        gamePlayer = new GamePlayer(entrance.getSpawnPointX(), entrance.getSpawnPointY(), this);
        gamePlayer.setGraphic(R.mipmap.ic_launcher);
        gamePlayer.setSpeed(3);
        entities.add(gamePlayer);
        gamePlayer.setLocation(getCurrentGameMap());

        //Når appen starter, begynder man i mappet "entrance"
    }

    public ArrayList<GameObject> getEntities() {
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
}

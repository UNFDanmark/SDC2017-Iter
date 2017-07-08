package software.unf.dk.itergame;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GameCanvas gameCanvas;
    private ArrayList<GameObject> entities = new ArrayList<>();
    private MapName current;
    private Button up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.Theme_AppCompat_NoActionBar);

        gameCanvas = new GameCanvas(this, this);

        setContentView(R.layout.activity_main);
        RelativeLayout view = (RelativeLayout) findViewById(R.id.layout);
        view.addView(gameCanvas);

        up = (Button) findViewById(R.id.Up);
        up.bringToFront();

        GameMap entrance = new GameMap(0, 0, this, 61, 113);

        //Når appen starter, begynder man i mappet "entrance"
        current = MapName.entrance;

        entities.add(new GamePlayer(100, 720 / 2, this));
    }

    public ArrayList<GameObject> getEntities() {
        return entities;
    }

    public GameCanvas getGameCanvas() {
        return gameCanvas;
    }

    //TODO: Fix
    public MapName getCurrentGameMap() {
        return current;
    }

    public void up(View view){
        System.out.println("CLICKED");
    }


}

package software.unf.dk.itergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GameCanvas gameCanvas;
    private ArrayList<GameObject> entities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.Theme_AppCompat_NoActionBar);

        gameCanvas = new GameCanvas(this, this);

        setContentView(gameCanvas);

        int i = 0;

        while(i < 20){
            int e = 0;
            while(e < 5) {
                entities.add(new GamePlayer(200 + (i * 40), 0+(e*50), this));
                e++;
            }


            i++;
        }

    }

    public ArrayList<GameObject> getEntities(){
        return entities;
    }

    public GameCanvas getGameCanvas(){
        return gameCanvas;
    }



}

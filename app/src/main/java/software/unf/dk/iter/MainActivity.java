package software.unf.dk.iter;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import software.unf.dk.iter.entities.player.Player;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

    private Thread gameEngineThread;
    private static GameScreen screen;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.Theme_AppCompat_NoActionBar);

        Log.i("MainActivity", "Creating screen");
        screen = new GameScreen(this, this);
        setContentView(screen);
        Log.i("MainActivity", "Screen created");


    }

    public void setup(GameEngine engine){
        Log.i("MainActivity", "Creating player");
        player = new Player(0,0, this);
        engine.addObject(player);
        Log.i("MainActivity", "Player created");

    }

}

package software.unf.dk.iter;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import software.unf.dk.iter.entities.player.Player;

public class MainActivity extends AppCompatActivity {

    private static GameEngine engine;
    private Thread gameEngineThread;
    private static GameScreen screen;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTheme(R.style.Theme_AppCompat_NoActionBar);
        screen = new GameScreen(this);
        setContentView(screen);

        engine = new GameEngine(screen);

        player = new Player(screen.getWidth()/2, screen.getHeight()/2, this);
        engine.addObject(player);

        //engine.setUp();
        //gameEngineThread = new Thread(engine);
        //gameEngineThread.run();

    }

    public static GameScreen getGameScreen(){
        return screen;
    }

    public static GameEngine getEngine(){
        return engine;
    }
}

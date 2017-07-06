package software.unf.dk.iter;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private Thread gameEngineThread;
    private static AssetManager assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new GameScreen(this));


        //gameEngine = new GameEngine();
        //gameEngineThread = new Thread(gameEngine);
        //gameEngineThread.run();

        //assets = getAssets();

    }

    public static AssetManager getTheAssets(){
        return assets;
    }
}

package software.unf.dk.iter;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private Thread gameEngineThread;
    private static AssetManager assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //gameEngine = new GameEngine();
        //gameEngineThread = new Thread(gameEngine);
        //gameEngineThread.run();

        assets = getAssets();

    }

    public static AssetManager getTheAssets(){
        return assets;
    }
}

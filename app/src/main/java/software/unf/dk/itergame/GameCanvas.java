package software.unf.dk.itergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by deltager on 07-07-17.
 */

public class GameCanvas extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private MainActivity mainActivity;

    public GameCanvas(Context context, MainActivity mainActivity) {
        super(context);

        this.mainActivity = mainActivity;

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        thread = new GameThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        boolean retry = true;

        while (true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    //Vinderen af bedste variabel navn her
    private boolean hasPlayerBeenPutOntoTheCanvasAtTheSpawnPointAtStartOfGameIfNotThenInitiateSpawnPuttingProcess = false;
    private boolean hasCreatedVeryImportantEntitiesThatAreNecessaryForTheFullExperience = false;

    public void update() {

        for (GameObject gameObject : mainActivity.getEntities()) {

            if(gameObject instanceof EnemyProjectile){
                gameObject.tick();
            } else if (!(gameObject instanceof GameMap) && !(gameObject instanceof GamePlayer)) {
                gameObject.tick();
            } else if (mainActivity.getCurrentGameMap().hasScaled() && gameObject instanceof GamePlayer && hasPlayerBeenPutOntoTheCanvasAtTheSpawnPointAtStartOfGameIfNotThenInitiateSpawnPuttingProcess == false) {
                GamePlayer player = (GamePlayer) gameObject;
                mainActivity.setGamePlayer(player);
                player.goToSpawn();
                hasPlayerBeenPutOntoTheCanvasAtTheSpawnPointAtStartOfGameIfNotThenInitiateSpawnPuttingProcess = true;
            } else if (gameObject instanceof GamePlayer) {
                gameObject.tick();
            }

        }
        mainActivity.deleteQueue();
        mainActivity.addFromQueue();
    }

    int bootUp = 120;
    int deathScreen = 0;

    public void runDiedScreen(int time){
        deathScreen = time;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);

        if(deathScreen > 0){
            Log.i("DEATHSCREEN", "death " + deathScreen);
            deathScreen--;

            mainActivity.getGamePlayer().setHealth(100);
            mainActivity.getGamePlayer().updateHealth();

            if(deathScreen == 5){
                mainActivity.disableDiedText();
            }

            return;

        } else if (bootUp > 0) {
            bootUp--;
            if (bootUp == 5) {
                mainActivity.removeSquattle();
            }
        } else {
            for (GameObject gameObject : mainActivity.getEntities()) {
                if (gameObject instanceof GameMap) {
                    gameObject.draw(canvas);
                }
            }

            //Entities skal tegne dem selv.
            for (GameObject gameObject : mainActivity.getEntities()) {
                if (!(gameObject instanceof GameMap) && !(gameObject instanceof EnemyProjectile)) {
                    gameObject.draw(canvas);
                }
            }

            for (GameObject gameObject : mainActivity.getEntities()) {
                if (gameObject instanceof EnemyProjectile) {
                    gameObject.draw(canvas);
                }
            }

            if (!hasCreatedVeryImportantEntitiesThatAreNecessaryForTheFullExperience) {
                GamePlayer gamePlayer = new GamePlayer(mainActivity.getCurrentGameMap().getSpawnPointX(), mainActivity.getCurrentGameMap().getSpawnPointY(), mainActivity);
                gamePlayer.setGraphic(R.mipmap.ic_launcher);
                gamePlayer.setSpeed(mainActivity.getCurrentGameMap().getScale());
                mainActivity.addEntity(gamePlayer);
                gamePlayer.setLocation(mainActivity.getCurrentGameMap());
                mainActivity.setGamePlayer(gamePlayer);

                GameMap gameMap = mainActivity.getCurrentGameMap();
                gameMap.spawnEnemy(gameMap.getGraphic().getWidth() / gameMap.getScale() / 2, gameMap.getGraphic().getHeight() / gameMap.getScale() / 2);
                gameMap.spawnEnemy(gameMap.getGraphic().getWidth() - gameMap.getGraphic().getWidth() / gameMap.getScale() / 2, gameMap.getGraphic().getHeight() / gameMap.getScale() / 2);
                gameMap.spawnEnemy(gameMap.getGraphic().getWidth() / gameMap.getScale() / 2, gameMap.getGraphic().getHeight() - gameMap.getGraphic().getHeight() / gameMap.getScale() / 2);
                gameMap.spawnEnemy(gameMap.getGraphic().getWidth() - gameMap.getGraphic().getWidth() / gameMap.getScale() / 2, gameMap.getGraphic().getHeight() - gameMap.getGraphic().getHeight() / gameMap.getScale() / 2);


                hasCreatedVeryImportantEntitiesThatAreNecessaryForTheFullExperience = true;

            }
        }
    }
}

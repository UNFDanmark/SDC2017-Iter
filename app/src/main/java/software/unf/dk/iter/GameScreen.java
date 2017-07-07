package software.unf.dk.iter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by deltager on 06-07-17.
 */

public class GameScreen extends SurfaceView implements SurfaceHolder.Callback{

    Paint background;
    GameEngine gameEngine;
    private Thread gameEngineThread;
    private MainActivity mainActivity;
    private Canvas canvas;


    public GameScreen(Context context, MainActivity mainActivity) {
        super(context);
        this.mainActivity = mainActivity;

        Log.i("GameScreen", "Constructing GameScreen");

        canvas = new Canvas();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawText("TEST", 0, 0, paint);

        invalidate();
    }

    //public GameScreen(Context context, @Nullable AttributeSet attrs) {
    //    super(context, attrs);
    //    setup();
    //}

    public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        Log.i("GameScreen", "Creating Surface");

        gameEngine = new GameEngine(this, getHolder());
        gameEngine.setUp();
        gameEngineThread = new Thread(gameEngine);
        gameEngineThread.run();

        setWillNotDraw(false);
        getHolder().addCallback(this);

        setup();

        Log.i("GameScreen", "Surface Created!");


    }

    public void setup(){
        Log.i("GameScreen", "Setting up GameScreen");
        background = new Paint();
        background.setColor(Color.BLACK);

        mainActivity.setup(gameEngine);
        Log.i("GameScreen", "GameScreen has been set up");




        //Timing timer = new Timing();
        //timer.run();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        canvas.drawText("TEST", 0, 0, p);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Log.i("Canvas", "Drawing...");

        Paint p = new Paint();
        p.setColor(Color.BLACK);
        canvas.drawText("TEST", 0, 0, p);

    }








    public class Timing extends Thread {
        @Override
        public void run() {

            //TODO: Add ny timer, kopi af den anden

                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //Gør ingenting
                }

            System.out.println("Opdaterer");

                postInvalidate();

        /*    long lastTime = System.nanoTime();
            double amountOfTicks = 60;
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            long timer = System.currentTimeMillis();
            int frames = 0;
            while (true) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    postInvalidate();
                    delta--;
                    Log.i("Timer", "Rendered");
                }
                /*if (running){
                    render();
                }
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    frames = 0;
                }*/
            }
        }
    }


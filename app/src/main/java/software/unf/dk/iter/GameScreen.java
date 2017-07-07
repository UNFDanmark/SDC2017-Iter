package software.unf.dk.iter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by deltager on 06-07-17.
 */

public class GameScreen extends View implements Drawable.Callback {

    float height, width;
    Paint background;

    public GameScreen(Context context) {
        super(context);
        setup();
        setWillNotDraw(false);
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup(){
        background = new Paint();
        background.setColor(Color.BLACK);
        Timing timer = new Timing();
        timer.run();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("Canvas", "drew canvas");
        height = canvas.getHeight();
        width = canvas.getWidth();
        canvas.drawRect(0, 0, width, height, background);

        for(GameObject o : MainActivity.getEngine().getObjects()){
            canvas.drawBitmap(o.graphic, o.getX(), o.getY(), null);
            Log.i("The value!", o.getX()+"");
            Log.i("And the y", o.getY()+"");
        }

        Paint textColor = new Paint();
        textColor.setColor(Color.RED);


        canvas.drawText("sup " + System.currentTimeMillis(), 300f, 300f, textColor);

    }



    public int getTheHeight(){
        return getHeight();
    }

    public int getTheWidth(){
        return getWidth();
    }

    public class Timing extends Thread {
        @Override
        public void run() {

            //TODO: Add ny timer, kopi af den anden

            //Det er helt op til jer selv om I vil have den til at loope eller om det bare skal være et enkelt delay.
            //I kan kode lige så mange forskellige timer threads som I har brug for men pas på med at køre for mange samtidigt.
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //Gør ingenting
                }

            System.out.println("Opdaterer");

                //Gør ting efter et delay
                //I kan IKKE direkte ændre i jeres UI herfra (Sætte tekst i knapper, ændre billeder)
                //Det I kan gøre er at ændre nogle felt variabler og så kalde postInvalidate() og så have at onDraw() reagerer på de felt variabler.
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


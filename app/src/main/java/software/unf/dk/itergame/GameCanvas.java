package software.unf.dk.itergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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

        while(true){
            try{
                thread.setRunning(false);
                thread.join();
            }catch(Exception e){
                e.printStackTrace();
            }
            retry = false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void update(){
        for(GameObject gameObject: mainActivity.getEntities()){
            gameObject.tick();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint(Color.WHITE);

        canvas.drawColor(Color.WHITE );

        //Entities skal tegne dem selv.
        for(GameObject gameObject : mainActivity.getEntities()){
            gameObject.draw(canvas);
        }
    }
}

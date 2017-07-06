package software.unf.dk.iter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

import static android.R.attr.bitmap;

/**
 * Created by deltager on 06-07-17.
 */

public class GameView extends SurfaceView implements Runnable {

    Thread gameThread;

    SurfaceHolder holder;

    volatile boolean playing;

    Canvas canvas;
    Paint paint;

    long fps;

    private long timeThisFrame;

    Bitmap icon;

    public GameView(Context context) {
        super(context);

        //Indsætter billede i icon

    }

    public void run(){

    }

    public void setIcon(String fileName){
        InputStream bitmap = null;
        holder = getHolder();
        paint = new Paint();
        try {
            bitmap = MainActivity.getTheAssets().open(fileName);
            icon = BitmapFactory.decodeStream(bitmap);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (bitmap != null) {
                    bitmap.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}

package software.unf.dk.iter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by deltager on 06-07-17.
 */

public class GameScreen extends View {

    float height, width;
    Paint background;

    public GameScreen(Context context) {
        super(context);
        setup();
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        height = canvas.getHeight();
        width = canvas.getWidth();

        canvas.drawRect(0, 0, width, height, background);
    }
}

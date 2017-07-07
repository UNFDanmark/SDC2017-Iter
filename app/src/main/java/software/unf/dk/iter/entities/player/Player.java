package software.unf.dk.iter.entities.player;

import software.unf.dk.iter.GameObject;
import software.unf.dk.iter.MainActivity;
import software.unf.dk.iter.R;

/**
 * Created by deltager on 06-07-17.
 */

public class Player extends GameObject{

    //TODO: Controls

    int xVel,yVel;

    public Player(int x, int y, MainActivity mainActivity) {
        super(x, y, mainActivity);
        setGraphic(R.mipmap.check);
        xVel = 1;
        yVel = 0;
    }

    @Override
    public void tick() {
        super.tick();
        x += xVel;
        y += yVel;
    }
}

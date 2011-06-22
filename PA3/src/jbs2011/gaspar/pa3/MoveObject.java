package jbs2011.gaspar.pa3;

import android.graphics.Canvas;

/**
 * Thread class to perform the so called "game loop".
 * 
 * @author gaspar obimba
 */
class MoveObject extends Thread {
    private GameDynamics _panel;
    private boolean _run = false;
    
    /**
     * Constructor.
     * 
     * @param panel View class on which we trigger the drawing.
     */
    public MoveObject(GameDynamics panel) {
        _panel = panel;
    }
    
    /**
     * @param run Should the game loop keep running? 
     */
    public void setRunning(boolean run) {
        _run = run;
    }
    
    /**
     * @return If the game loop is running or not.
     */
    public boolean isRunning() {
        return _run;
    }
    
    /**
     * Perform the game loop.
     * Order of performing:
     * 1. update physics
     * 2. check for winners
     * 3. draw everything
     */
    @Override
    public void run() {
        Canvas c;
        while (_run) {
            c = null;
            try {
                c = _panel.getHolder().lockCanvas(null);
                synchronized (_panel.getHolder()) {
                    _panel.updatePhysics();
                    _panel.checkForWinners();
                    _panel.onDraw(c);
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    _panel.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
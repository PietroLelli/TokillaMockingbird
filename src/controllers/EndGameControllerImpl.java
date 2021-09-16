package controllers;

import model.player.PlayerMovementImpl;
import view.EndGameViewImpl;
import view.View;

public class EndGameControllerImpl implements EndGameController {

    private final View view;
    private final PlayerMovementImpl player;
    
    public EndGameControllerImpl(final PlayerMovementImpl player) {
        this.view = new EndGameViewImpl(this);
        this.view.exit();
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restart() {
        this.view.exit();
        final MainMenuController menuC = new MainMenuControllerImpl(this.player);
        menuC.setup();
        this.player.setCoinsx2(false);
        this.player.setLuck(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
        this.view.setup();
    }

}

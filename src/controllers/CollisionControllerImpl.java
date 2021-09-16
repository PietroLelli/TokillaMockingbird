package controllers;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import model.enemy.Vehicle;
import model.map.Box;
import model.player.Directions;
import model.player.Player;
import model.score.Coin;

public class CollisionControllerImpl implements CollisionController {

    private final Player player;
    private final Map<Directions, Boolean> enabledDir = new HashMap<>();

    private static final int ERROR = 20;
    private static final double MAXHEIGH = 40.0;
    private static final double LEFTBORDERLIMIT = 0.0;
    private static final double RIGHTBORDERLIMIT = 700.0;
    private static final double MINHEIGH = 750.0;

    public CollisionControllerImpl(final Player player2) {
        this.player = player2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
        this.unBlockAll();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean collideWithVehicles(final Vehicle v) {
        final Rectangle borderVehicle = new Rectangle((int) v.getXLoc() - ERROR, (int) v.getYLoc() - ERROR, v.getWidth() - ERROR, 1);
        final Rectangle borderPlayer = new Rectangle((int) player.getXLoc() - ERROR, (int) player.getYLoc() - ERROR, 
                player.getWidth() - ERROR, player.getHeight() - ERROR);
        return borderPlayer.intersects(borderVehicle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean collideWithCoins(final Coin c) {
        final Rectangle borderCoin = new Rectangle((int) c.getXLoc() - ERROR, (int) c.getYLoc() - ERROR,
                c.getWidth() - ERROR, c.getHeight() - ERROR);
        final Rectangle borderPlayer = new Rectangle((int) player.getXLoc() - ERROR, (int) player.getYLoc() - ERROR,
                player.getWidth() - ERROR, player.getHeight() - ERROR);
        return borderPlayer.intersects(borderCoin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkTrees(final Box tree) {
        final Rectangle borderTree = new Rectangle((int) tree.getXLoc() - ERROR, (int) tree.getYLoc() - ERROR, 
                tree.getWidth() - ERROR, tree.getHeight() - ERROR);

        if (borderTree.intersects(new Rectangle((int) player.getXLoc() + 100 - ERROR, (int) player.getYLoc() - ERROR,
                player.getWidth() - ERROR, player.getHeight() - ERROR))) {
            enabledDir.put(Directions.RIGHT, false);
        }
        if (borderTree.intersects(new Rectangle((int) player.getXLoc() - 100 - ERROR, (int) player.getYLoc() - ERROR,
                player.getWidth() - ERROR, player.getHeight() - ERROR))) {
            enabledDir.put(Directions.LEFT, false);
        }
        if (borderTree.intersects(new Rectangle((int) player.getXLoc() - ERROR, (int) player.getYLoc() + 100 - ERROR,
                player.getWidth() - ERROR, player.getHeight() - ERROR))) {
            enabledDir.put(Directions.DOWN, false);
        }
        if (borderTree.intersects(new Rectangle((int) player.getXLoc() - ERROR, (int) player.getYLoc() - 100 - ERROR, 
                player.getWidth() - ERROR, player.getHeight() - ERROR))) {
            enabledDir.put(Directions.UP, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkBorders() {
        if (player.getXLoc() == RIGHTBORDERLIMIT) {
            enabledDir.put(Directions.RIGHT, false);
        }
        if (player.getXLoc() == LEFTBORDERLIMIT) {
            enabledDir.put(Directions.LEFT, false);
        }
        if (player.getYLoc() <= MAXHEIGH) {
            enabledDir.put(Directions.UP, false);
        }
        return player.getYLoc() >= MINHEIGH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void block(final Directions dir) {
        enabledDir.put(dir, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unBlockAll() {
        enabledDir.put(Directions.LEFT, true);
        enabledDir.put(Directions.RIGHT, true);
        enabledDir.put(Directions.UP, true);
        enabledDir.put(Directions.DOWN, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkDir(final Directions dir) {
        return enabledDir.get(dir);
    }
}

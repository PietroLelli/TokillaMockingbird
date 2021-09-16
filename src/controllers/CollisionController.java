package controllers;

import model.enemy.Vehicle;
import model.map.Box;
import model.player.Directions;
import model.score.Coin;

public interface CollisionController extends Controller {

    /**
     * method that checks the collision with the vehicle v.
     * @param v      a vehicle
     * @return true if colliding, false otherwise.
     */
    boolean collideWithVehicles(Vehicle v);

    /**
     * method that checks the collision with coins.
     * @param c      a coin
     * @return true if colliding, false if not.
     */
    boolean collideWithCoins(Coin c);

    /**
     * Check if the player is too close to trees and blocks input to prevent overlapping.
     * @param tree   a box containing a tree
     */
    void checkTrees(Box tree);

    /**
     * Check if the player is too close to borders.
     * @return true if colliding with the bottom border, false otherwise.
     */
    boolean checkBorders();

    /**
     * blocks a certain direction.
     * @param dir     the direction i want to block.
     */
    void block(Directions dir);

    /**
     * blocks all directions.
     */
    void unBlockAll();

    /**
     * check if the player can move in a certain direction.
     * @param dir     the direction i want to check.
     * @return true if the direction is blocked, false otherwise.
     */
    boolean checkDir(Directions dir);
}

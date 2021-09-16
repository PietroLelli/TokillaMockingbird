package model.enemy;

import java.util.Random;

import controllers.GameController;
import controllers.GameControllerImpl;
import model.map.BoxImpl;

/**
 * A implementation of the Interface Vehicle.
 * 
 * It contains methods for create different type of vehicles with different
 * speeds and different images.
 */
public class VehicleImpl extends BoxImpl implements Vehicle {

    /**
     * constant.
     */
    private static final int MAP_SCROLL = 1;
    private static final int HIGHER_LIMIT = 900;
    private static final int INFERIOR_LIMIT = -100;
    //private static final int CAR_SPEED = 2;
    //private static final int TRUCK_SPEED = 1;
    private static final int TRAIN_SPEED = 10;
    private static final int RANDOM_SPEED_ADDITION = 10;
    private static final int RANDOM_XLOCSPAWN_ADDITION = 1500;

    /**
     * local variables.
     */
	private VehicleType type;
    private final Random rand = new Random();
    private int vehicleSpeed;
    private int vehicleXLocSpawn;
	private final GameController gameC;
	private int carSpeed = 2;
	private int truckSpeed = 1;
	
	public VehicleImpl(final GameControllerImpl gameControllerImpl) {
		gameC = gameControllerImpl;
	}


    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle initializeCar(final double stripYLoc) {
		
    	this.setYDir(MAP_SCROLL);
    	this.setYLoc(stripYLoc);
    	if(this.gameC.getScore() >= 25 && this.gameC.getScore() < 45 ) {
    		carSpeed = 8;
    		truckSpeed = 7;
    	}
    	else if(this.gameC.getScore() >= 45) {
    		carSpeed = 16;
    		truckSpeed = 15;
    	}
    	if (rand.nextInt(2) == 1) {
    		this.setRndDir(carSpeed, "Car_Left.png", "Car_Right.png");
    		this.type = VehicleType.CAR;
    	}
    	else {
    		this.setRndDir(truckSpeed, "Truck_L.png", "Truck_R.png");
    		this.type = VehicleType.TRUCK;
    	}
    	return this;
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public Vehicle initializeTrain(final double stripYLoc) {

        this.setYDir(MAP_SCROLL);
        this.setYLoc(stripYLoc);
        this.setRndDir(TRAIN_SPEED, "Train.png", "Train.png");
        this.type = VehicleType.TRAIN;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRndDir(final int speed, final String imgR, final String imgL) {
    	
        vehicleSpeed = rand.nextInt(RANDOM_SPEED_ADDITION) + speed;
        vehicleXLocSpawn = rand.nextInt(RANDOM_XLOCSPAWN_ADDITION) * speed;

        /**
         * Sets the direction from right to left.
         */
        if (rand.nextInt(2) == 1) {
            this.setXLoc(vehicleXLocSpawn + HIGHER_LIMIT);
            this.setXDir(-(vehicleSpeed));
            this.setImage(imgR);

            /**
             * Sets the direction from left to right.
             */
        } else {
            this.setXLoc(-vehicleXLocSpawn - INFERIOR_LIMIT);
            this.setXDir(vehicleSpeed);
            this.setImage(imgL);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle getVehicle() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleType getVehicleType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVehicleSpeed() {
        return this.vehicleSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVehicleXLocSpawn() {
        return this.vehicleXLocSpawn;
    }
}

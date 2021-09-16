package model.player;

import java.util.HashMap;
import java.util.Map;

import model.map.BoxImpl;
import model.shop.Characters;
import model.shop.Upgrades;

public class PlayerImpl extends BoxImpl implements Player {

    private static final double MAP_SCROLL = 1;
    private int collectedCoins, lives;
    private Characters name;
    private final Map<Characters, Integer> mapPrice = new HashMap<>();
    private final Map<Upgrades, Integer> mapPriceUpgrades = new HashMap<>();

    private boolean coinsx2, luck;
	private static final int PRICE_YELLOWBIRD = 0;
	private static final int PRICE_BLACKBIRD = 5;
	private static final int PRICE_BLUEBIRD = 8;
	private static final int PRICE_PINKBIRD = 12;
	private static final int PRICE_REDBIRD = 15;
	
	private static final int PRICE_LUCK = 5;
	private static final int PRICE_COINSX2 = 10;
	private static final int PRICE_LIFE = 15;

    
    public PlayerImpl(final String filename, final double xPos, final double yPos, final Characters c, final int coins, final int lives) {
        setImage(filename);
        this.setXLoc(xPos);
        this.setYLoc(yPos);
        this.setYDir(MAP_SCROLL);
        this.collectedCoins = coins;
        this.name = c;
        this.lives = lives;
        
        mapPrice.put(Characters.YELLOWBIRD, PRICE_YELLOWBIRD);
        mapPrice.put(Characters.BLACKBIRD, PRICE_BLACKBIRD);
        mapPrice.put(Characters.PINKBIRD, PRICE_PINKBIRD);
        mapPrice.put(Characters.REDBIRD, PRICE_REDBIRD);
        mapPrice.put(Characters.BLUEBIRD, PRICE_BLUEBIRD);

        mapPriceUpgrades.put(Upgrades.LUCK, PRICE_LUCK);
        mapPriceUpgrades.put(Upgrades.COINSX2, PRICE_COINSX2);
        mapPriceUpgrades.put(Upgrades.LIFE, PRICE_LIFE);
    }

    public int getLives() {
		return lives;
	}

	public void increaseLives() {
		this.lives++;
	}
	
	public void decreaseLives() {
		this.lives--;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        return this.collectedCoins;
    }

    public boolean isCoinsx2() {
		return coinsx2;
	}

	public void setCoinsx2(final boolean coinsx2) {
		this.coinsx2 = coinsx2;
	}

	public boolean isLuck() {
		return luck;
	}

	public void setLuck(final boolean luck) {
		this.luck = luck;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void setCoins(final int numberOfCoins) {
        this.collectedCoins = numberOfCoins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseCoins() {
    	if(this.coinsx2) {
    		this.collectedCoins++;
    	}
        this.collectedCoins++;
    }
    
    public Characters getNameCharacter() {
		return this.name;
	}
    
	@Override
	public int getCostCharacter(final Characters c) {
		return this.mapPrice.get(c);
	}

	@Override
	public void setCostCharacter(final int cost) {
		this.mapPrice.replace(this.getNameCharacter(), cost);
	}
    
	@Override
	public int getCostUpgrade(final Upgrades u) {
		return this.mapPriceUpgrades.get(u);
	}

	@Override
	public void setCostUpgrade(final Upgrades u, final int cost) {
		this.mapPriceUpgrades.replace(u, cost);
	}
	
    public void changePlayer(final String fileName, final Characters character) {
    	this.setImage(fileName);
    	this.name = character;
    }
    

}

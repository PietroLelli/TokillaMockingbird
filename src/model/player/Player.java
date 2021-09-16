package model.player;

import model.map.Box;
import model.shop.Characters;
import model.shop.Upgrades;

public interface Player extends Box {

    /**
     * 
     * @return the number of coins
     */
    int getCoins();

    /**
     * set the new number of coins.
     * @param numberOfCoins
     */
    void setCoins(int numberOfCoins);

    /**
     * increase the number of coins.
     */
    void increaseCoins();
    
    /**
     * change the player
     */
    void changePlayer(String fileName, Characters characters);
    
    Characters getNameCharacter();

	void setCoinsx2(boolean b);

	void setLuck(boolean b);

	int getCostCharacter(Characters c);

	void setCostCharacter(int cost);

	int getCostUpgrade(Upgrades u);

	void setCostUpgrade(Upgrades u, int cost);

	boolean isLuck();

	int getLives();

	void increaseLives();
	
	void decreaseLives();

}

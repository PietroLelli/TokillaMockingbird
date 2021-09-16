package model.shop;

import javax.swing.JOptionPane;

import model.player.Player;

public class ShopImpl implements Shop {

	private static final String MSGNOMONEY = "YOU DON'T HAVE ENOUGH COINS";
	private final Player player;

	public ShopImpl(final Player player) {
		this.player = player;
	}

	@Override
	public void checkCharacter(final Characters c) {
		if(this.player.getCostCharacter(c) <= player.getCoins()) {
			switch (c) {
			case BLACKBIRD:
				this.player.changePlayer("blackbird.png", Characters.BLACKBIRD);
				JOptionPane.showMessageDialog(null, "BlackBird PURCHASED");
				break;
			case BLUEBIRD:
				this.player.changePlayer("bluebird.png", Characters.BLUEBIRD);
				JOptionPane.showMessageDialog(null, "BlueBird PURCHASED");
				break;
			case PINKBIRD:
				this.player.changePlayer("pinkbird.png", Characters.PINKBIRD);
				JOptionPane.showMessageDialog(null, "PinkBird PURCHASED");
				break;
			case YELLOWBIRD:
				this.player.changePlayer("yellowbird.png", Characters.YELLOWBIRD);
				JOptionPane.showMessageDialog(null, "YellowBird PURCHASED");
				break;
			case REDBIRD:
				this.player.changePlayer("redbird.png", Characters.REDBIRD);
				JOptionPane.showMessageDialog(null, "RedBird PURCHASED");
				break;
			default:
				break;
			}
			this.player.setCoins(this.player.getCoins() - this.player.getCostCharacter(c));
			this.player.setCostCharacter(0);
		}
		else {
			JOptionPane.showMessageDialog(null, MSGNOMONEY);
		}
	}
	
	public void addLuck() {
		if(this.player.getCostUpgrade(Upgrades.LUCK) <= player.getCoins()) {
			this.player.setLuck(true);
			JOptionPane.showMessageDialog(null, "ADD LUCK PURCHASED");
			this.player.setCoins(this.player.getCoins() - this.player.getCostUpgrade(Upgrades.LUCK));
		}
		else {
			JOptionPane.showMessageDialog(null, MSGNOMONEY);
		}
	}
	
	public void coinsx2() {
		if(this.player.getCostUpgrade(Upgrades.COINSX2) <= player.getCoins()) {
			this.player.setCoinsx2(true);
			JOptionPane.showMessageDialog(null, "COINS x2 PURCHASED");
			this.player.setCoins(this.player.getCoins() - this.player.getCostUpgrade(Upgrades.COINSX2));

		}
		else {
			JOptionPane.showMessageDialog(null, MSGNOMONEY);
		}
	}

	public void addLife() {
		if(this.player.getCostUpgrade(Upgrades.LIFE) <= player.getCoins()) {
			JOptionPane.showMessageDialog(null, "ADD LIFE PURCHASED");
			this.player.setCoins(this.player.getCoins() - this.player.getCostUpgrade(Upgrades.LIFE));
			this.player.increaseLives();
		}
		else {
			JOptionPane.showMessageDialog(null, MSGNOMONEY);
		}
	}
}

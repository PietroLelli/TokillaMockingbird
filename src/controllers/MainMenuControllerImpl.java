package controllers;

import model.player.PlayerMovementImpl;
import model.shop.Characters;
import model.shop.ShopImpl;
import view.GameView;
import view.MainMenuViewImpl;

public class MainMenuControllerImpl implements MainMenuController {

    private static final int X_SPAWN_PLAYER = 400;
    private static final int Y_SPAWN_PLAYER = 600;
    
    private final MainMenuViewImpl view;
    private final GameView gameV;
	private final ShopImpl shopModel;
    private PlayerMovementImpl player;
    	
    public MainMenuControllerImpl(final PlayerMovementImpl player2) {
        
        gameV = new GameView();
        if(player2 == null)
        	player = new PlayerMovementImpl("yellowbird.png", X_SPAWN_PLAYER, Y_SPAWN_PLAYER, Characters.YELLOWBIRD, 50, 1);
        else {
        	this.player = player2;
        	this.player.setXLoc(X_SPAWN_PLAYER);
        	this.player.setYLoc(Y_SPAWN_PLAYER);
        }
        this.view = new MainMenuViewImpl(this);
        shopModel = new ShopImpl(this.player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
        this.view.setup(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newGame() {
        this.view.exit();
        gameV.setup(player);
    }
    
    @Override
	public void showShop() {
    	this.view.setupShop();
    }

	@Override
    public void exitShop() {
    	this.view.exitShop(player);
    }
    
	@Override
	public void changeCharacter(final Characters character) {
		shopModel.checkCharacter(character);
	}
	
	@Override
	public void coinsx2() {
		shopModel.coinsx2();
	}
	
	public PlayerMovementImpl getPlayer() {
		return this.player;
	}

	@Override
	public void addLuck() {
		shopModel.addLuck();
	}
	
	public void addLife() {
		shopModel.addLife();
	}
}

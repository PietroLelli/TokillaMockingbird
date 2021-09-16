package controllers;

import model.player.PlayerMovementImpl;
import model.shop.Characters;

public interface MainMenuController  extends Controller {

    void newGame();

	void showShop();

	void changeCharacter(Characters character);

	void exitShop();

	PlayerMovementImpl getPlayer();

	void coinsx2();

	void addLuck();

	void addLife();
}

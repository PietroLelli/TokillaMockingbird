package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import controllers.MainMenuController;
import model.player.Player;
import model.player.PlayerMovementImpl;
import model.shop.Characters;
import model.shop.Upgrades;

public class MainMenuViewImpl {

	private static final int MENU_WIDTH = 600;
	private static final int MENU_HEIGHT = 700;
	private static final int HALF_MENU_WIDTH = MENU_WIDTH / 2 - 80;
	private static final int IMAGE_WIDTH = 150;
	private static final int IMAGE_HEIGHT = 70;
	private static final int FIRST_IMAGE_Y= 450;
	private static final Color BACKGROUND_COLOR = new Color(60, 179, 113);

	private static final int SHOP_WIDTH = 600;
	private static final int SHOP_HEIGHT = 700;
	private static final int IMAGE_SHOP_WIDTH = 100;
	private static final int IMAGE_SHOP_HEIGHT = 100;
	private static final int FIRST_IMAGE_SHOP_Y = 120;
	private static final int FIRST_IMAGE_SHOP_X = 50;
    
	private final MainMenuController controller;
	private final PlayerMovementImpl player;
	
	private final JFrame frame = new JFrame();
	private JLabel lblBackground;
	private final JLabel lblCoins = new JLabel(), lblLives = new JLabel();
	private JButton startButton, shopButton, controlsButton;
	private Rectangle rStartButton, rShopButton, rControlsButton, rLblBackground, rLblCoins, rLblLives;
		
	private final JFrame frameShop = new JFrame();
	private JButton yellowBirdButton, blackBirdButton, pinkBirdButton, redBirdButton, blueBirdButton, returnButton, luckButton, luckButtonI, coinButton, coinButtonI, lifeButton, lifeButtonI;
	private Rectangle rYellowBirdButton, rBlackBirdButton, rPinkBirdButton, rRedBirdButton, rBlueBirdButton, rReturnButton, rLblYellowCost, rLblBlackCost, rLblPinkCost, rLblRedCost, rLblBlueCost, rLuckButton, rCoinButton, rLblLuckCost, rLblCoinCost,rLuckButtonI, rCoinButtonI, rLifeButton, rLifeButtonI, rLblLifeCost, rLblUpgrade, rLblCoinsShop;
	private final JLabel lblYellowCost = new JLabel(), lblBlackCost = new JLabel(), lblPinkCost = new JLabel(), lblRedCost = new JLabel(), lblBlueCost = new JLabel(), lblLuckCost = new JLabel(), lblCoinCost = new JLabel(), lblLifeCost = new JLabel(), lblUpgrade = new JLabel(), lblCoinsShop = new JLabel();
	
	public MainMenuViewImpl(final MainMenuController controller) {
		final panelMenu menuPanel = new panelMenu(controller.getPlayer());
		this.controller = controller;
		this.player = controller.getPlayer();
		this.frame.setTitle("To Kill a Mockingbird");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setLocation(350, 10);
		this.frame.setSize(MENU_WIDTH, MENU_HEIGHT);
		this.frame.getContentPane().add(menuPanel);
		this.frame.setBackground(BACKGROUND_COLOR);
		this.frame.setVisible(true);
		//Set layout to absolute for buttons.
		this.frame.setLayout(null);
	}
	
	class panelMenu extends JLayeredPane {

		private static final long serialVersionUID = 1L;

		public panelMenu(final Player player) {

			rLblBackground = new Rectangle(0, 0, MENU_WIDTH, MENU_HEIGHT);
			final ImageIcon background = new ImageIcon(new ImageIcon(this.getClass().getResource("/MainMenu.png")).getImage().getScaledInstance(MENU_WIDTH, MENU_HEIGHT, Image.SCALE_SMOOTH));
			lblBackground = new JLabel(background);
			lblBackground.setBounds(rLblBackground);
			add(lblBackground, DEFAULT_LAYER);
			lblCoins.setText("Coins: 0");
			
			if(player == null) {
				this.add(lblCoins);
	            lblCoins.setText("Coins: 0");   
			}
			else {
				lblCoins.setText("Coins: " + player.getCoins());
			}
			
			lblCoins.setForeground(Color.white);
            lblCoins.setFont(new Font("Helvetica", Font.ITALIC, 30));
            rLblCoins = new Rectangle(50, 360, 300, 300);
            lblCoins.setBounds(rLblCoins);
            lblBackground.add(lblCoins, POPUP_LAYER);
            
            if(player == null) {
				this.add(lblLives);
	            lblLives.setText("Life: 1");   
			}
			else {
				lblLives.setText("Lives: " + player.getLives());
			}
            
            lblLives.setForeground(Color.white);
            lblLives.setFont(new Font("Helvetica", Font.ITALIC, 30));
            rLblLives = new Rectangle(50, 410, 300, 300);
            lblLives.setBounds(rLblLives);
            lblBackground.add(lblLives, POPUP_LAYER);
            
			final ImageIcon startImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/startButton.png")).getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
			final ImageIcon shopImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/shopButton.png")).getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
			final ImageIcon controlsImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/controlsButton2.png")).getImage().getScaledInstance(100, 30, Image.SCALE_SMOOTH));
			rStartButton = new Rectangle(HALF_MENU_WIDTH, FIRST_IMAGE_Y, IMAGE_WIDTH, IMAGE_HEIGHT);
			rShopButton = new Rectangle(HALF_MENU_WIDTH, FIRST_IMAGE_Y + 100, IMAGE_WIDTH, IMAGE_HEIGHT);
			rControlsButton = new Rectangle(460, 610, 100, 30);
			//Create button component, set image, remove borders.
			startButton = new JButton("", startImage);
			startButton.setBounds(rStartButton);
			startButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(startButton);
			
			shopButton = new JButton("", shopImage);
			shopButton.setBorder(BorderFactory.createEmptyBorder());
			shopButton.setBounds(rShopButton);
			lblBackground.add(shopButton);

			controlsButton = new JButton("", controlsImage);
			controlsButton.setBorder(BorderFactory.createEmptyBorder());
			controlsButton.setBounds(rControlsButton);
			lblBackground.add(controlsButton);

			
			startButton.addActionListener(e -> {
				controller.newGame();
			});

			shopButton.addActionListener(e -> {
				controller.showShop();
			});
			
			controlsButton.addActionListener(e -> {
				JOptionPane.showMessageDialog(null, "Arrow Keys:  Move the character." 
						+ "\nEsc:  Pause / Resume the game.");
			});
		}
	}
    
	class ShopPanel extends JLayeredPane {

		private static final long serialVersionUID = 1L;

		public ShopPanel(final Player player) {

			rLblBackground = new Rectangle(0, 0, SHOP_WIDTH, SHOP_HEIGHT);
			final ImageIcon background = new ImageIcon(new ImageIcon(this.getClass().getResource("/Shop.png")).getImage().getScaledInstance(SHOP_WIDTH, SHOP_HEIGHT, Image.SCALE_SMOOTH));

			lblBackground = new JLabel(background);
			lblBackground.setBounds(rLblBackground);
			add(lblBackground, DEFAULT_LAYER);

			lblCoinsShop.setText("Coins: " + player.getCoins());
			lblCoinsShop.setForeground(Color.yellow);
			lblCoinsShop.setFont(new Font("Helvetica", Font.ITALIC, 35));
			rLblCoinsShop = new Rectangle(30, 15, 200, 100);
			lblCoinsShop.setBounds(rLblCoinsShop);			
			lblBackground.add(lblCoinsShop, POPUP_LAYER);
			
			lblYellowCost.setText("Cost: " + player.getCostCharacter(Characters.YELLOWBIRD));
			lblYellowCost.setForeground(Color.white);
            lblYellowCost.setFont(new Font("Helvetica", Font.ITALIC, 30));
            rLblYellowCost = new Rectangle(50, 100, 300, 300);
            lblYellowCost.setBounds(rLblYellowCost);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblYellowCost, POPUP_LAYER);
            
            lblBlackCost.setText("Cost: " + player.getCostCharacter(Characters.BLACKBIRD));
			lblBlackCost.setForeground(Color.white);
            lblBlackCost.setFont(new Font("Helvetica", Font.ITALIC, 30));
            rLblBlackCost = new Rectangle(250, 100, 300, 300);
            lblBlackCost.setBounds(rLblBlackCost);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblBlackCost, POPUP_LAYER);
            
            lblBlueCost.setText("Cost: " + player.getCostCharacter(Characters.BLUEBIRD));
			lblBlueCost.setForeground(Color.white);
            lblBlueCost.setFont(new Font("Helvetica", Font.ITALIC, 30));
            rLblBlueCost = new Rectangle(450, 100, 300, 300);
            lblBlueCost.setBounds(rLblBlueCost);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblBlueCost, POPUP_LAYER);
            
            lblPinkCost.setText("Cost: " + player.getCostCharacter(Characters.PINKBIRD));
			lblPinkCost.setForeground(Color.white);
            lblPinkCost.setFont(new Font("Helvetica", Font.ITALIC, 30));
            rLblPinkCost = new Rectangle(150, 270, 300, 300);
            lblPinkCost.setBounds(rLblPinkCost);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblPinkCost, POPUP_LAYER);
            
            lblRedCost.setText("Cost: " + player.getCostCharacter(Characters.REDBIRD));
			lblRedCost.setForeground(Color.white);
            lblRedCost.setFont(new Font("Helvetica", Font.ITALIC, 30));
            rLblRedCost = new Rectangle(350, 270, 300, 300);
            lblRedCost.setBounds(rLblRedCost);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblRedCost, POPUP_LAYER);
            
            lblLuckCost.setText("Cost: " + player.getCostUpgrade(Upgrades.LUCK));
			lblLuckCost.setForeground(Color.white);
            lblLuckCost.setFont(new Font("Helvetica", Font.ITALIC, 25));
            rLblLuckCost = new Rectangle(30, 475, 300, 300);
            lblLuckCost.setBounds(rLblLuckCost);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblLuckCost, POPUP_LAYER);
            
            lblCoinCost.setText("Cost: " + player.getCostUpgrade(Upgrades.COINSX2));
			lblCoinCost.setForeground(Color.white);
            lblCoinCost.setFont(new Font("Helvetica", Font.ITALIC, 25));
            rLblCoinCost = new Rectangle(155, 475, 300, 300);
            lblCoinCost.setBounds(rLblCoinCost);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblCoinCost, POPUP_LAYER);
            
            lblLifeCost.setText("Cost: " + player.getCostUpgrade(Upgrades.LIFE));
			lblLifeCost.setForeground(Color.white);
            lblLifeCost.setFont(new Font("Helvetica", Font.ITALIC, 25));
            rLblLifeCost = new Rectangle(275, 475, 300, 300);
            lblLifeCost.setBounds(rLblLifeCost);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblLifeCost, POPUP_LAYER);
            
            lblUpgrade.setText("Upgrade: ");
			lblUpgrade.setForeground(Color.white);
            lblUpgrade.setFont(new Font("Helvetica", Font.ITALIC, 30));
            rLblUpgrade = new Rectangle(30, 310, 300, 300);
            lblUpgrade.setBounds(rLblUpgrade);
			//lblBackground.add(lblCoins);
            lblBackground.add(lblUpgrade, POPUP_LAYER);
            
            
			final ImageIcon yellowbirdImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/yellowbirdShop.png")).getImage().getScaledInstance(IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT, Image.SCALE_SMOOTH));
			final ImageIcon blackbirdImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/blackbirdShop.png")).getImage().getScaledInstance(IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT, Image.SCALE_SMOOTH));
			final ImageIcon bluebirdImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/bluebirdShop.png")).getImage().getScaledInstance(IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT, Image.SCALE_SMOOTH));
			final ImageIcon pinkbirdImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/pinkbirdShop.png")).getImage().getScaledInstance(IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT, Image.SCALE_SMOOTH));
			final ImageIcon redbirdImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/redbirdShop.png")).getImage().getScaledInstance(IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT, Image.SCALE_SMOOTH));
			final ImageIcon returnImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/exitButton2.png")).getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH));
			final ImageIcon luckImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/quadrifoglio.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
			final ImageIcon coinShopImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/coinShop.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
			final ImageIcon heartShopImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/cuoreShop.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
			final ImageIcon infoImage = new ImageIcon(new ImageIcon(this.getClass().getResource("/info.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

			
			rYellowBirdButton = new Rectangle(FIRST_IMAGE_SHOP_X, FIRST_IMAGE_SHOP_Y, IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT);
			rBlackBirdButton = new Rectangle(FIRST_IMAGE_SHOP_X + 200, FIRST_IMAGE_SHOP_Y, IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT);
			rBlueBirdButton = new Rectangle(FIRST_IMAGE_SHOP_X + 400, FIRST_IMAGE_SHOP_Y, IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT);
			rPinkBirdButton = new Rectangle(FIRST_IMAGE_SHOP_X + 100, FIRST_IMAGE_SHOP_Y + 170, IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT);
			rRedBirdButton = new Rectangle(FIRST_IMAGE_SHOP_X + 300, FIRST_IMAGE_SHOP_Y + 170, IMAGE_SHOP_WIDTH, IMAGE_SHOP_HEIGHT);
			rReturnButton = new Rectangle(MENU_WIDTH - 200, MENU_HEIGHT - 100, 150, 30);
			rLuckButton = new Rectangle(40, 520, 85, 85);
			rCoinButton = new Rectangle(160, 520, 85, 85);
			rLuckButtonI = new Rectangle(73, 490, 20, 20);
			rCoinButtonI = new Rectangle(193, 490, 20, 20);
			rLifeButton= new Rectangle(280, 520, 85, 85);
			rLifeButtonI = new Rectangle(313, 490, 20, 20);
			
			luckButton = new JButton("", luckImage);
			luckButton.setBounds(rLuckButton);
			luckButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(luckButton);
			
			luckButtonI = new JButton("", infoImage);
			luckButtonI.setBounds(rLuckButtonI);
			luckButtonI.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(luckButtonI);
			
			lifeButton = new JButton("", heartShopImage);
			lifeButton.setBounds(rLifeButton);
			lifeButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(lifeButton);
			
			lifeButtonI = new JButton("", infoImage);
			lifeButtonI.setBounds(rLifeButtonI);
			lifeButtonI.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(lifeButtonI);
			
			coinButton = new JButton("", coinShopImage);
			coinButton.setBounds(rCoinButton);
			coinButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(coinButton);
			
			coinButtonI = new JButton("", infoImage);
			coinButtonI.setBounds(rCoinButtonI);
			coinButtonI.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(coinButtonI);
			
			//Create button component, set image, remove borders.
			yellowBirdButton = new JButton("", yellowbirdImage);
			yellowBirdButton.setBounds(rYellowBirdButton);
			yellowBirdButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(yellowBirdButton);
			
			blackBirdButton = new JButton("", blackbirdImage);
			blackBirdButton.setBounds(rBlackBirdButton);
			blackBirdButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(blackBirdButton);

			blueBirdButton = new JButton("", bluebirdImage);
			blueBirdButton.setBounds(rBlueBirdButton);
			blueBirdButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(blueBirdButton);
			
			pinkBirdButton = new JButton("", pinkbirdImage);
			pinkBirdButton.setBounds(rPinkBirdButton);
			pinkBirdButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(pinkBirdButton);
			
			redBirdButton = new JButton("", redbirdImage);
			redBirdButton.setBounds(rRedBirdButton);
			redBirdButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(redBirdButton);
			
			returnButton = new JButton("", returnImage);
			returnButton.setBounds(rReturnButton);
			returnButton.setBorder(BorderFactory.createEmptyBorder());
			lblBackground.add(returnButton);
			
			yellowBirdButton.addActionListener(e -> {
				controller.changeCharacter(Characters.YELLOWBIRD);
				lblCoinsShop.setText("Coins: " + player.getCoins());
				lblYellowCost.setText("Cost: " + player.getCostCharacter(Characters.YELLOWBIRD));
			});
			
			blackBirdButton.addActionListener(e -> {
				controller.changeCharacter(Characters.BLACKBIRD);
				lblCoinsShop.setText("Coins: " + player.getCoins());
				lblBlackCost.setText("Cost: " + player.getCostCharacter(Characters.BLACKBIRD));
			});

			blueBirdButton.addActionListener(e -> {
				controller.changeCharacter(Characters.BLUEBIRD);
				lblCoinsShop.setText("Coins: " + player.getCoins());
				lblBlueCost.setText("Cost: " + player.getCostCharacter(Characters.BLUEBIRD));
			});
			
			pinkBirdButton.addActionListener(e -> {
				controller.changeCharacter(Characters.PINKBIRD);
				lblCoinsShop.setText("Coins: " + player.getCoins());
				lblPinkCost.setText("Cost: " + player.getCostCharacter(Characters.PINKBIRD));
			});
			
			redBirdButton.addActionListener(e -> {
				controller.changeCharacter(Characters.REDBIRD);
				lblCoinsShop.setText("Coins: " + player.getCoins());
				lblRedCost.setText("Cost: " + player.getCostCharacter(Characters.REDBIRD));
			});
			
			returnButton.addActionListener(e -> {
				controller.exitShop();
			});
			
			luckButton.addActionListener(e -> {
				controller.addLuck();
				lblCoinsShop.setText("Coins: " + player.getCoins());
			});
			
			coinButton.addActionListener(e -> {
				controller.coinsx2();
				lblCoinsShop.setText("Coins: " + player.getCoins());
			});
			
			luckButtonI.addActionListener(e -> {
				JOptionPane.showMessageDialog(null, "Better chances of gaining coins for one game.");
			});
			
			coinButtonI.addActionListener(e -> {
				JOptionPane.showMessageDialog(null, "Double your coins for one game."); 
			});
			
			lifeButton.addActionListener(e -> {
				controller.addLife();
				lblCoinsShop.setText("Coins: " + player.getCoins());
			});
			
			lifeButtonI.addActionListener(e -> {
				JOptionPane.showMessageDialog(null, "Start next game with an extra life. \n"
						+ "If you lose one life you will make a step forward,\nWatch out where you’ll stand.");
			});
		}
	}
	
	public void exit() {
		this.frame.dispose();
	}

	public void setup(final Player player2) {
		lblCoins.setText("Coins: " + player2.getCoins());
		lblLives.setText("Lives: " + player2.getLives());
		this.frame.setVisible(true);
	}
	
	public void setupShop() {
		
		final ShopPanel shopPanel = new ShopPanel(this.player);
		this.frameShop.setTitle("To Kill a Mockingbird");
		this.frameShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frameShop.setResizable(false);
		this.frameShop.setLocation(350, 10);
		this.frameShop.setSize(MENU_WIDTH, MENU_HEIGHT);
		this.frameShop.getContentPane().add(shopPanel);
		this.frameShop.setBackground(BACKGROUND_COLOR);
		this.frameShop.setVisible(true);
		//Set layout to absolute for buttons.
		this.frameShop.setLayout(null);
		this.frame.setVisible(false);
	}

	public void exitShop(final Player player2) {
		this.frameShop.dispose();
		this.setup(player2);
	}
}

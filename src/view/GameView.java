package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import controllers.GameControllerImpl;
import model.enemy.Vehicle;
import model.enemy.VehicleImpl;
import model.map.Box;
import model.player.PlayerMovementImpl;
import model.score.Coin;

/**
 * 
 *
 */
public class GameView implements KeyListener{

    /**
     * constants.
     */
    private static final int SIZE = 800;

    /**
     * local variables.
     */
    private JFrame frame;
    private PanelGame panelGame;
    private final GameView gv = this;
    /**
     * Create the whole frame.
     */
    public void setup(final PlayerMovementImpl player) {

        this.panelGame = new PanelGame(player);
        this.frame = new JFrame();
        this.frame.addKeyListener(this);
        this.frame.getContentPane().add(panelGame);
        this.frame.setTitle("Mockingbird");
        this.frame.setSize(SIZE, SIZE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

    }

    public class PanelGame extends JPanel implements ActionListener {

        private static final long serialVersionUID = 1L;

        /**
         * constants.
         */
        private static final int NSTRIP_TO_GENERATE = 11;
        private static final int BOXFORSTRIP = 8;
        private static final int TIMER_DELAY = 10;
        private static final int FONT_SIZE = 35;

        /**
         * local variables.
         */
        private final JLabel lblLives = new JLabel(), lblScore = new JLabel(), lblCoins = new JLabel(), lblLevel;
        private final List<ArrayList<Box>> allStrip = new ArrayList<ArrayList<Box>>();
        private final List<Vehicle> vehiclesOnRoad = new ArrayList<>();
        private final List<Vehicle> trains = new ArrayList<>();
        private final ArrayList<Coin> coins = new ArrayList<>();
        private final GameControllerImpl gameController = new GameControllerImpl(gv);
        private final Vehicle vehicleManager = new VehicleImpl(gameController);
        private final Timer timer;

        public PanelGame(final PlayerMovementImpl player) {

            gameController.setup(player);

            this.timer = new Timer(TIMER_DELAY, this);

            gameController.setInitialPosition(allStrip);

            this.repaint();

            lblLevel = new JLabel();
            lblLevel.setForeground(Color.ORANGE);
            lblLevel.setFont(new Font("Helvetica", Font.ITALIC, 60));
            this.add(lblLevel);
            
            this.add(lblLives);
            lblLives.setForeground(Color.white);
            lblLives.setFont(new Font("Helvetica", Font.ITALIC, FONT_SIZE));
            
            this.add(lblScore);
            lblScore.setForeground(Color.white);
            lblScore.setFont(new Font("Helvetica", Font.ITALIC, FONT_SIZE + 15));
            
            this.add(lblCoins);
            lblCoins.setForeground(Color.white);
            lblCoins.setFont(new Font("Helvetica", Font.ITALIC, FONT_SIZE));
            
            this.timer.start();
        }

        /**
         * @param g
         * 
         */
        public void paintComponent(final Graphics g) {

            /**
             * Erases the previous screen.
             */
            super.paintComponent(g);

            /**
             * Draws strips.
             */
            for (int i = 0; i < NSTRIP_TO_GENERATE; i++) {
                for (int x = 0; x < BOXFORSTRIP; x++) {
                    this.allStrip.get(i).get(x).paint(g, this);
                }
            }

            /**
             * Draws vehicles.
             */
            this.coins.forEach(v -> v.paint(g, this));
            this.vehiclesOnRoad.forEach(v -> v.paint(g, this));
            this.trains.forEach(v -> v.paint(g, this));

            gameController.getPlayer().paint(g, this);
            lblScore.setText("     Score: " + gameController.getScore());
            lblCoins.setText("     Coins: " + gameController.getPlayer().getCoins());
            lblLives.setText("Lives: " + gameController.getPlayer().getLives());
            if(gameController.getScore() == 30) {
            	lblLevel.setText("               LEVEL UP              ");
            }
            if(gameController.getScore() == 33) {
            	lblLevel.setText("");
            }
            
            if(gameController.getScore() == 50) {
            	lblLevel.setForeground(Color.red);
            	lblLevel.setText("               LEVEL UP              ");
            }
            if(gameController.getScore() == 53) {
            	lblLevel.setText("");
            }
        }

        
        /**
         * @param e Repaints all elements and calls gameController to perform a game
         *          cycle
         */
        @Override
        public void actionPerformed(final ActionEvent e) {

            this.repaint();
            gameController.generateMap(allStrip, vehiclesOnRoad, trains, coins);
            gameController.actionPerformed(this.allStrip, this.vehicleManager, this.vehiclesOnRoad, this.coins,
                    this.trains);
        }

        /**
         * @return gameController
         */
        private GameControllerImpl getGameController() {
            return gameController;
        }
    }

    /**
     * @param e
     * 
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        panelGame.getGameController().keyCatch(e);
    }

    @Override
    public void keyReleased(final KeyEvent arg0) {
    }

    @Override
    public void keyTyped(final KeyEvent arg0) {
    }

    /**
     * 
     */
    public void exit() {
        this.frame.dispose();
    }
    
	public void showMessage() {
		this.panelGame.lblLevel.setText("               YOU LOST A LIFE.              ");
		final Timer timer = new Timer(3000, e -> this.panelGame.lblLevel.setText(""));
		timer.setRepeats(false);
		timer.start();
	}
}

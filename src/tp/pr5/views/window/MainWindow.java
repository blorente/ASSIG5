package tp.pr5.views.window;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.WindowController;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameObserver;
import tp.pr5.logic.Observable;
import tp.pr5.logic.ReadOnlyBoard;

public class MainWindow extends JFrame implements GameObserver {
	
	public static final String ICONS_FILEPATH = "src/tp/pr5/icons/";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WindowController controller;
	private Observable<GameObserver> game;
	private BoardPanel brdPanel;
	private CtrlPanel ctrlPanel;
	private JPanel quitPanel;
	
	public MainWindow(Observable<GameObserver> g, WindowController c) {	
		super("[=] Assignment 5 [=]");
        this.setSize(700, 500);
		this.controller = c;
		this.game = g;
		initGUI();
		g.addObserver(this);
	}
	
	public void initGUI () {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		this.brdPanel = new BoardPanel(this.controller, this.game);//Nested Class
		this.ctrlPanel = new CtrlPanel(game, controller, controller.getRules()); // Nested Class
		this.quitPanel = createQuitPanel();
		
		contentPanel.add(this.brdPanel);
		contentPanel.add(this.ctrlPanel);
		contentPanel.setVisible(true);
		
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(this.quitPanel, BorderLayout.PAGE_END);		
		
		mainPanel.setVisible(true);
		this.setVisible(true);
        this.repaint();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel createQuitPanel() {
		JPanel quitPanel = new JPanel();
		JButton quit = new JButton("");
		quit.setIcon(new ImageIcon(MainWindow.ICONS_FILEPATH + "exit.png"));
		quit.setToolTipText("Exit");
		quit.addActionListener(new ActionListener() {
			String[] options = {"Yes", "No"};
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int n = JOptionPane.showOptionDialog(new JFrame(),"Are sure you want to quit?",
													"Quit", JOptionPane.DEFAULT_OPTION, 
													JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
				if(n == 0) {
					System.exit(0);
				}
			}
		});
		quitPanel.add(quit);
		return quitPanel;
	}
	
	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {

	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {

	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttachToObserved(ReadOnlyBoard board, Counter turn) {
		
	}	
		
	
}

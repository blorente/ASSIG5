package tp.pr5.views.window;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.control.WindowController;
import tp.pr5.logic.ComplicaRules;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameObserver;
import tp.pr5.logic.Observable;
import tp.pr5.logic.ReadOnlyBoard;


@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements GameObserver{
	
	private WindowController ctrl;
	private JButton[][] buttons;
	private GridBagConstraints c;
	private boolean active;
	
	public BoardPanel(WindowController ctrl, Observable<GameObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new GridBagLayout());
		this.c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
	}

	private JButton createButton (final int col, final int row, final Counter player, Counter colour) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(40, 40));
		switch(colour) {
		case BLACK:
			button.setIcon(new ImageIcon("src/tp/pr5/icons/black.png"));
			break;
		case WHITE:
			button.setIcon(new ImageIcon("src/tp/pr5/icons/white.png"));
			break;
		default:
			
			break;
		}
		if (this.ctrl.getRules().getClass() == ComplicaRules.class || colour == Counter.EMPTY) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (active) {
						ctrl.makeMove(col + 1, row + 1, player);
					}	
				}
			});
		}
		return button;		
	}

	public void UpdateView(Counter player, ReadOnlyBoard board) {
		this.buttons = new JButton[board.getWidth()][board.getHeight()];
		this.removeAll();
		
		for (int i = 0; i < board.getWidth(); i++) {
			this.c.gridx = i;
			for (int j = 0; j < board.getHeight(); j++) {
				this.buttons[i][j] = createButton(i, j,  player, board.getPosition(i + 1, j + 1));
				//setButtonDisabled(i,j, board.getPosition(i, j));
				this.c.gridy = j;
				this.add(buttons[i][j], c);
			}
		}
        this.active = true;
	}
	
	
	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		this.active = false;
		this.UpdateView(winner, board);
		this.revalidate();
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		this.UpdateView(nextPlayer, board);
	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		this.UpdateView(nextPlayer, board);
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAttachToObserved(ReadOnlyBoard board, Counter turn) {
		this.UpdateView(turn, board);	
	}

	@Override
	public void onRandomMoveBegin(ReadOnlyBoard board, Counter turn) {
			/*for (int i = 0; i < board.getWidth(); i++) {
				for(int j = 0; j < board.getHeight(); j++) {
					if (this.ctrl.getRules().getClass() == ComplicaRules.class || board.getPosition(i + 1, j + 1) == Counter.EMPTY) {
					buttons[i][j].removeActionListener(buttons[i][j].getActionListeners()[0]);
					}
				}
			
			}*/
	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		this.UpdateView(player, board);	
		this.revalidate();
	}
}



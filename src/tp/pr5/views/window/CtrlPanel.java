package tp.pr5.views.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tp.pr5.control.Instruction;
import tp.pr5.control.WindowController;
import tp.pr5.logic.ComplicaRules;
import tp.pr5.logic.Connect4Rules;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameObserver;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.GravityRules;
import tp.pr5.logic.Observable;
import tp.pr5.logic.ReadOnlyBoard;
import tp.pr5.logic.ReversiRules;

public class CtrlPanel extends JPanel implements GameObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WindowController controller;
	private Instruction inst;
	private int col, row;
	private GameRules rules;

    public CtrlPanel (Observable<GameObserver> g, WindowController c, GameRules rules) {
		this.controller = c;
        this.rules = rules;
		inst = Instruction.PLAY_C4;
		setLayout(new BorderLayout(5, 5));
		g.addObserver(this);
	}

	private void initGUI(final Counter player) {
		this.removeAll();
		JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));

        //Crete Reset button
		JButton reset = new JButton("");
		reset.setIcon(new ImageIcon("src/tp/pr5/icons/reset.png"));
		reset.setToolTipText("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				switch(inst) {
				case PLAY_C4:
					rules = new Connect4Rules();
					break;
				case PLAY_CO:
					rules = new ComplicaRules();
					break;
				case PLAY_G:
					rules = new GravityRules(col, row);
					break;
				case PLAY_RV:
					rules = new ReversiRules();
					break;
				default:
					rules = new Connect4Rules();
					break;
				}
				controller.reset(rules);
			}		
		});
        //Create Undo button
        JButton undo = new JButton("");
        undo.setIcon(new ImageIcon("src/tp/pr5/icons/undo.png"));
        undo.setToolTipText("Undo the move");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.undo();
                }
                catch (Exception ex) {
                    onUndoNotPossible();
                }
            }
        });
		//Create random move Button
		JButton random = new JButton("");
		random.setIcon(new ImageIcon("src/tp/pr5/icons/random.png"));
		random.setToolTipText("Random Move");
		random.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.randomMove();
			}		
		});
        
        //Create horizontal subpanel for Undo, Reset and Random buttons
        JPanel cntrlButtonsPanel = new JPanel();
        cntrlButtonsPanel.setLayout(new BoxLayout(cntrlButtonsPanel, BoxLayout.X_AXIS));
        cntrlButtonsPanel.setSize(this.getWidth(), 200);
        cntrlButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cntrlButtonsPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        cntrlButtonsPanel.add(reset);
        cntrlButtonsPanel.add(undo);
        cntrlButtonsPanel.add(random);

        //Create horizontal subpanel for player selection
        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
        playersPanel.setSize(this.getWidth(), 200);        
        playersPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playersPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        JPanel playerAndControlPanel  = new JPanel();
        playerAndControlPanel.setLayout(new BoxLayout( playerAndControlPanel, BoxLayout.Y_AXIS));
        playerAndControlPanel.add(cntrlButtonsPanel);
        playerAndControlPanel.add(playersPanel);
        
        //Create The current player text
        JTextField turn = new JTextField();
        turn.setEnabled(false);
        turn.setText(player + " plays");
        //Mount the text panel
        firstPanel.add(turn);

        //Create panel to change game
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));
		String[] instructions = {inst.toString(Instruction.PLAY_C4), 
									inst.toString(Instruction.PLAY_CO), 
									inst.toString(Instruction.PLAY_G),
									inst.toString(Instruction.PLAY_RV)};
		
		final JTextArea height = new JTextArea(1, 1);
		height.setText("Height");
		height.setEditable(true);
		height.setEnabled(false);
		height.setVisible(false);
		height.setPreferredSize(new Dimension(0, 10));
		height.addFocusListener(new BoardMeasureHintFocusListener(height.getText(), height));
		
		final JTextArea width = new JTextArea(1, 1);
		width.setText("Width");
		width.setEditable(true);
		width.setEnabled(false);
		width.setVisible(false);
		height.setPreferredSize(new Dimension(0, 10));
		width.addFocusListener(new BoardMeasureHintFocusListener(width.getText(), width));
		
		final JComboBox<String> list = new JComboBox<String>(instructions);
		list.setPreferredSize(new Dimension(30, 30));
		list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String instruction = (String)list.getSelectedItem();
				if (inst.StringToInstruction(instruction) == Instruction.PLAY_G){
					width.setVisible(true);
					width.setEnabled(true);
					height.setVisible(true);
					height.setEnabled(true);
				}
				else {
					width.setVisible(false);
					width.setEnabled(false);
					height.setVisible(false);
					height.setEnabled(false);
				}
			}
		});
		
		JButton change = new JButton("Change");
        change.setPreferredSize(new Dimension (30, 30));
        change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inst = inst.StringToInstruction((String)list.getSelectedItem());
				if (inst == Instruction.PLAY_G) {
					try {
						col = Integer.parseInt(width.getText());
						row = Integer.parseInt(height.getText());
						controller.changeGame(inst, col, row);
					}
					catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Input are supposed to be a number", "Bad input!",
								JOptionPane.ERROR_MESSAGE);
					}	
				}
				else {
					controller.changeGame(inst, 0, 0);
				}
			}		
		});

		secondPanel.add(list);
		secondPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		secondPanel.add(width);
		secondPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		secondPanel.add(height);
		secondPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		secondPanel.add(change);
		//Add all of the panles to the Central panel
		this.add(firstPanel, BorderLayout.PAGE_START);
        this.add(playerAndControlPanel, BorderLayout.CENTER);
        this.add(secondPanel, BorderLayout.PAGE_END);
		this.revalidate();
	}
	
	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		initGUI(player);
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
        if (winner != Counter.EMPTY) {
            int n = JOptionPane.showOptionDialog(new JFrame(),
                    "Do you want to play again?", winner.toString() + " wins",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);
            if (n == 0) {
            	switch(this.inst) {
				case PLAY_C4:
					this.rules = new Connect4Rules();
					break;
				case PLAY_CO:
					this.rules = new ComplicaRules();
					break;
				case PLAY_G:
					this.rules = new GravityRules(col, row);
					break;
				case PLAY_RV:
					this.rules = new ReversiRules();
					break;
				default:
					this.rules = new Connect4Rules();
					break;
				}
                this.controller.reset(this.rules);
            }
            else{
                System.exit(0);
            }
        }
        else {
            int n = JOptionPane.showOptionDialog(new JFrame(),
                    "Do you want to play again?", "It's a draw",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);
            if (n == 0) {
            	switch(this.inst) {
				case PLAY_C4:
					this.rules = new Connect4Rules();
					break;
				case PLAY_CO:
					this.rules = new ComplicaRules();
					break;
				case PLAY_G:
					this.rules = new GravityRules(col, row);
					break;
				default:
					this.rules = new Connect4Rules();
					break;
				}
                this.controller.reset(this.rules);
            }
            else{
                System.exit(0);
            }
        }
	}	
	

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
        initGUI(nextPlayer);
	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
        initGUI(nextPlayer);
	}

	@Override
	public void onUndoNotPossible() {
		JOptionPane.showMessageDialog(new JFrame(), 
				"You can't make an undo", "Impossible to undo!",
				JOptionPane.WARNING_MESSAGE);
	}	

	@Override
	public void onAttachToObserved(ReadOnlyBoard board, Counter turn) {
        initGUI(turn);
	}
	
	//----------
	//NESTED CLASSES
	//-----------
	private class BoardMeasureHintFocusListener implements FocusListener {

		private String hint;
		private JTextArea toReadFrom;		
		
		public BoardMeasureHintFocusListener(String hint, JTextArea toReadFrom) {
			super();
			this.hint = hint;
			this.toReadFrom = toReadFrom;
		}

		@Override
		public void focusGained(FocusEvent e) {
			if(this.toReadFrom.getText().equals(this.hint)) {
				this.toReadFrom.setText("");
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			
		}
	}
}

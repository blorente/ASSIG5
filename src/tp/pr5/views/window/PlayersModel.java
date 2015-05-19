package tp.pr5.views.window;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import tp.pr5.control.PlayerType;
import tp.pr5.control.WindowController;
import tp.pr5.logic.Counter;

public class PlayersModel implements ComboBoxModel<PlayerType> {

	private Counter player;
	private PlayerType selected;
	private WindowController controller;
	
	public PlayersModel(Counter player, WindowController controller) {
		this.player = player;
		this.selected = player.getMode();
		this.controller = controller;
	}
	
	@Override
	public int getSize() {
		return 2;
	}

	@Override
	public PlayerType getElementAt(int index) {
		PlayerType type;
		switch(index) {
		case 1:
			type = PlayerType.AUTO;
			break;
		default:
			type = PlayerType.HUMAN;
			break;
		}
		return type;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selected = (PlayerType) anItem;
		controller.setPlayerMode(this.player, this.selected);
	}

	@Override
	public Object getSelectedItem() {
		return this.selected;
	}

}

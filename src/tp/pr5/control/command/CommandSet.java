package tp.pr5.control.command;

public class CommandSet {
	
	static Command[] cmds = { new MoveCmd(), 
								new UndoCmd(), 
								new RestartCmd(), 
								new ExitCmd(), 
								new PlayC4Cmd(),
								new PlayCOCmd(),
								new PlayGRCmd(),
								new PlayRVCmd(),
								new PlayerCmd(),
								new HelpCmd()};
	
	public static Command parse(String line) {
		for( Command c : cmds) {
			Command cmd = c.parse(line);
			if ( cmd != null ) return cmd;
		}
		return null;
	}
	

}

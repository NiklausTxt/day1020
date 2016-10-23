package com.iwantbesuper.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.iwantbesuper.Player;
import com.iwantbesuper.command.impl.PlayerAcceptCommand;
import com.iwantbesuper.command.impl.PlayerAnswerCommand;
import com.iwantbesuper.command.impl.PlayerAskCommand;
import com.iwantbesuper.command.impl.PlayerHelpCommand;
import com.iwantbesuper.command.impl.PlayerListCommand;
import com.iwantbesuper.command.impl.PlayerScoreCommand;

public class PlayerCommandFactory extends SystemCommandFactory{

	private Player player;
	public PlayerCommandFactory(Player player) {
		this.player=player;
	}
	
	public static Map<CommandCode, Class<? extends Command>> commandMap = new HashMap<>();
	
	static {
		commandMap.put(CommandCode.LIST, PlayerListCommand.class);
		commandMap.put(CommandCode.HELP, PlayerHelpCommand.class);
		commandMap.put(CommandCode.ANSWER, PlayerAnswerCommand.class);
		commandMap.put(CommandCode.ACCEPT, PlayerAcceptCommand.class);
		commandMap.put(CommandCode.ASK, PlayerAskCommand.class);
		commandMap.put(CommandCode.SCORE, PlayerScoreCommand.class);
	}
	
	@Override
	public Command buildCommand(CommandCode cmd) {
		Command command =null;
		
		Class<? extends Command> cmdClass = commandMap.get(cmd);
		if(cmdClass!=null){
			try {
				@SuppressWarnings("unchecked")
				Constructor<Command> constructor = (Constructor<Command>) cmdClass.getConstructor(Player.class);
				
				command = constructor.newInstance(player);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
//		if(cmd.equalsIgnoreCase("help")){
//			command = new PlayerHelpCommand(player);
//		}
//		if(cmd.equalsIgnoreCase("ask")){
//			command = new PlayerAskCommand(player);
//		}
//		if(cmd.equalsIgnoreCase("List")){
//			command = new PlayerListCommand(player);
//		}
//		if(cmd.equalsIgnoreCase("answer")){
//			command = new PlayerAnswerCommand(player);
//		}
//		if(cmd.equalsIgnoreCase("accept")){
//			command = new PlayerAcceptCommand(player);
//		}
		if(command==null){
			command = super.buildCommand(cmd);
		}
		return command;
	}

}

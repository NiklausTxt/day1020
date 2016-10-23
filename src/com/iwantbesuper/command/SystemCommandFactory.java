package com.iwantbesuper.command;

import java.util.HashMap;
import java.util.Map;

import com.iwantbesuper.command.impl.ExitCommand;
import com.iwantbesuper.command.impl.HelpCommand;
import com.iwantbesuper.command.impl.InvalidCommand;
import com.iwantbesuper.command.impl.LoginCommand;
import com.iwantbesuper.command.impl.SignupCommand;

public class SystemCommandFactory extends CommandFactory{
	
	private static Map<CommandCode, Class<? extends Command>> commandMap = new HashMap<>();
	
	static {
		commandMap.put(CommandCode.EXIT, ExitCommand.class);
		commandMap.put(CommandCode.HELP, HelpCommand.class);
		commandMap.put(CommandCode.SIGNUP, SignupCommand.class);
		commandMap.put(CommandCode.LOGIN, LoginCommand.class);
	}
	
	public Command buildCommand(CommandCode identifier){
		Class<? extends Command> cmdClass = commandMap.get(identifier);
		if(cmdClass != null){
			try {
				return cmdClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
//		if(identifier.equalsIgnoreCase("exit")){
//			return new ExitCommand();
//		}
//		if (identifier.equalsIgnoreCase("help")) {
//			return new HelpCommand();
//		}
//		if(identifier.equalsIgnoreCase("signup")){
//			return new SignupCommand();
//		}
//		if(identifier.equalsIgnoreCase("login")){
//			return new LoginCommand();
//		}
		return new InvalidCommand();
	}
}

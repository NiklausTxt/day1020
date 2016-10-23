package com.iwantbesuper.command;

import com.iwantbesuper.Player;

public abstract class PlayerCommand implements Command{
	
	protected Player player;
	
	public PlayerCommand(Player player){
		this.player=player;
	}
}

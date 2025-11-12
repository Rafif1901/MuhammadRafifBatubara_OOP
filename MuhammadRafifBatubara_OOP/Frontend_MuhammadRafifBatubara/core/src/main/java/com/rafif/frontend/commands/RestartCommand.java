package com.rafif.frontend.commands;
import com.rafif.frontend.GameManager;
import com.rafif.frontend.Player;

public class RestartCommand implements Command{
    private Player player;
    private GameManager gameManager;

    public RestartCommand(Player player, GameManager gameManager){
        this.player = player;
        this.gameManager = gameManager;
    }

    @Override
    public void execute(){
        player.reset();
        gameManager.setScore(0);
    }
}

package Service;
import Repository.PlayerRepository;
import Model.Player;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
    public boolean existByUsername(String username){
        return playerRepository.findByUsername(username);
    }
    public Player createPlayer(Player player){
        if(existByUsername(player.getUsername())){
            throw new RuntimeException("Username " + player + " sudah dipakai");
        }
        playerRepository.save(player);
        return player;
    }
    public Optional<Player> getPlayerById(UUID playerId){
        return playerRepository.findById(playerId);
    }
    public Optional<Player> getPlayerByUsername(String username){
        return playerRepository.findByUsername(username);
    }
    public Optional<Player> getAllPlayers(){
        return new ArrayList<>(allData);
    }


}

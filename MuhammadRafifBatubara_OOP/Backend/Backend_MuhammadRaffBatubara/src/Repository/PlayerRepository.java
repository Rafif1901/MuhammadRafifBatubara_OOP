package Repository;

import java.util.Optional;

public class PlayerRepository<Player, UUID> extends BaseRepository{
    public Optional<Player> findByUsername(String Username){
        return allData.stream()
                .filter(player -> player.getUsername() .equals(username))
                .findFirst();
    }
    public void findTopPlayersByHighScore(int limit){

    }
    public void findByHighscoreGreaterThan(int minScore){

    }
    public void findAllByOrderByTotalCoinsDesc(){

    }
    public void findAllByOrderByTotalDistanceTravelledDesc(){

    }
    public save(Player player){

    }
}

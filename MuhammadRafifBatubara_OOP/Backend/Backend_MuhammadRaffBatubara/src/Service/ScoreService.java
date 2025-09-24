package Service;

import Repository.PlayerRepository;
import Repository.ScoreRepository;

public class ScoreService {
    private ScoreRepository scoreRepository;
    private PlayerRepository playerRepository;
    private PlayerService playerService;

    public(ScoreRepository scoreRepository, PlayerRepository playerRepository, PlayerService playerService){
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

}

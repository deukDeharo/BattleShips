package salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by gerarddeharoramirez on 2/5/17.
 */

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/games")
    private Map<String,Object> makeGamesDTO(){
        Map<String,Object> gameObjecDTO = new LinkedHashMap<String,Object>();
        gameObjecDTO.put("Games", gameRepository.findAll()
                .stream()
                .map(game -> getGamesDTO(game))
                .collect(Collectors.toList()));

        return gameObjecDTO;
    }

    private Map<String,Object> getGamesDTO(Game game){
        Map<String,Object> gameObjecDTO = new LinkedHashMap<String,Object>();
        gameObjecDTO.put("id", game.getId());
        gameObjecDTO.put("date", game.getDate());
        gameObjecDTO.put("participation",game.getGamePlayers()
                .stream()
                .map(gamePlayer -> getGamePlayersDTO(gamePlayer)).collect(Collectors.toList()));

        //gameObjecDTO.put("GamePlayers", getGamePlayersListDTO());

        return gameObjecDTO;

    }

    private Map<String,Object> getGamePlayersDTO(GamePlayer gamePlayer) {
        Map<String, Object> gamePlayersObjectDTO = new LinkedHashMap<String, Object>();

        gamePlayersObjectDTO.put("id", gamePlayer.getId());
        gamePlayersObjectDTO.put("player",getPlayersDTO(gamePlayer));

        /*gamePlayersObjectDTO.put("player", playerRepository.findAll()
                .stream().map(player -> getPlayersDTO(player))
                .collect(Collectors.toList()));
*/

        return gamePlayersObjectDTO;


    }

    private Map<String,Object> getPlayersDTO(GamePlayer gamePlayer){
        Map<String,Object> playerObjectDTO = new LinkedHashMap<String,Object>();
        playerObjectDTO.put("id", gamePlayer.getPlayer().getId());
        playerObjectDTO.put("userName", gamePlayer.getPlayer().getUserName());

        return playerObjectDTO;
    }

}




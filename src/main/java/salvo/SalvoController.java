package salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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

    @RequestMapping("game_view/{gameId}")
    public Map<String,Object> findGameId(@PathVariable Long gameId){

        GamePlayer gamePlayer = gamePlayerRepository.findOne(gameId);
        Game game = gamePlayer.getGame();

        Map<String,Object> gameObjectDTO = new LinkedHashMap<>();
        gameObjectDTO.put("id",game.getId());
        gameObjectDTO.put("Date",game.getDate());

        gameObjectDTO.put("participation",game.getGamePlayers()
                .stream()
                .map(gamePlayerX -> getGamePlayersDTO(gamePlayerX)).collect(Collectors.toList()));

        gameObjectDTO.put("Ships",gamePlayer.getShips().stream().map(ship -> findShips(ship)).collect(Collectors.toList()));
        //gameObjectDTO.put("Salvoes", gamePlayer.getSalvos().stream().map(salvo -> findSalvos(salvo)).collect(Collectors.toList()));
       gameObjectDTO.put("Salvoes",game.getGamePlayers().stream().map(gp -> findSalvos(gp)).collect(Collectors.toList()));

        return gameObjectDTO;

    }

    public Map<String,Object> findShips(Ship ship){
        Map<String,Object> gameObjectDTO = new LinkedHashMap<>();
        gameObjectDTO.put("type",ship.getBoatType());
        gameObjectDTO.put("locations",ship.getLocationList());
        return gameObjectDTO;
    }
    private Map<String,Object> findSalvos(GamePlayer gp){

            Map<String,Object> gameObjectDTO = new LinkedHashMap<>();
        gameObjectDTO.put("Player",gp.getPlayer().getId());
        gameObjectDTO.put("UserName",gp.getPlayer().getUserName());
            gameObjectDTO.put("Salvos",gp.getSalvos().stream().map(salvo -> findRealSalvos(salvo)).collect(Collectors.toList()));

            return gameObjectDTO;

    }

    private Map<String,Object> findRealSalvos(Salvo salvo){
        Map<String,Object> gameObjectDTO = new LinkedHashMap<>();
        gameObjectDTO.put("turn", salvo.getTurn());
        gameObjectDTO.put("locations", salvo.getLocationList());

        return gameObjectDTO;

    }






}




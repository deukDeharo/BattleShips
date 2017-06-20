package salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collector;
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

    @Autowired
    private ShipRepository shipRepository;

    @RequestMapping("/games")
    private Map<String,Object> makeGamesDTO(Authentication authentication){

        //TODO Añadir seguridad Authentication authentication en el parametro y descomenta el dto de la authentication


        Map<String,Object> gameObjecDTO = new LinkedHashMap<String,Object>();

        if (authentication != null){
            gameObjecDTO.put("CurrentPlayer", getCurrentPlayerData(authentication) );

        }




        gameObjecDTO.put("Games", gameRepository.findAll()
                .stream()
                .map(game -> getGamesDTO(game))
                .collect(Collectors.toList()));
        //DEBO COGER TODOS LOS SCORES DE LOS PLAYERS Y METERLOS EN LA FUNCIÓN DE ABAJO

        gameObjecDTO.put("LeaderBoard", playerRepository.findAll()
                .stream()
                .map(player ->getBasicData(player) )
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

        return gameObjecDTO;

    }
    private Map<String,Object> getBasicData (Player player){
        Map<String,Object> gameObjectDTO = new LinkedHashMap<>() ;

        gameObjectDTO.put("userName",player.getUserName());

        gameObjectDTO.put("scores", player.getPlayersScores().stream().map(score -> score.getScore()).collect(Collectors.toList()));
       gameObjectDTO.put("TotalScore",player.getPlayersScores().stream().mapToDouble(score -> score.getScore()).sum());
        gameObjectDTO.put("win", player.getPlayersScores().stream().filter(score -> score.getScore()== 1).count());
        gameObjectDTO.put("looses", player.getPlayersScores().stream().filter(score -> score.getScore()== 0).count());
        gameObjectDTO.put("ties", player.getPlayersScores().stream().filter(score -> score.getScore()== 0.5).count());

       /* gameObjectDTO.put("scores", player.getPlayersScores().stream().map(score -> getScoreMap(score))
                            .collect(Collectors.toList()));*/

        return gameObjectDTO;
    }

    private List<Float> getScoreMap ( Score score){
        List<Float> gameObjectDTO = new LinkedList<>() ;
        gameObjectDTO.add(score.getScore());

        return gameObjectDTO;
    }






    private Map<String,Object> getGamePlayersDTO(GamePlayer gamePlayer) {
        Map<String, Object> gamePlayersObjectDTO = new LinkedHashMap<String, Object>();

        gamePlayersObjectDTO.put("id", gamePlayer.getId());
        gamePlayersObjectDTO.put("player",getPlayersDTO(gamePlayer));
        //gamePlayersObjectDTO.put("Score",gamePlayer.getScore().score);

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
    public Object findGameId(@PathVariable Long gameId, Authentication authentication){
        String playerAuth = authentication.getName();
       String userAuthName = gamePlayerRepository.findOne(gameId).getPlayer().getUserName();

       if (playerAuth == userAuthName){

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
       else {

           return new ResponseEntity<>("Wrong Player",HttpStatus.UNAUTHORIZED);
       }



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


private Player getPlayerAuth(Authentication auth){

        return playerRepository.findByUserName(auth.getName());
}

private Map<String,Object> getCurrentPlayerData(Authentication authentication) {
    Map<String, Object> gameObjectDTO = new LinkedHashMap<>();
    gameObjectDTO.put("userName", getPlayerAuth(authentication).getUserName());
    gameObjectDTO.put("id", getPlayerAuth(authentication).getId());

    gameObjectDTO.put("games",getPlayerAuth(authentication).getGamePlayers()
            .stream().map(gamePlayer -> getParticipationAndGames(gamePlayer)).collect(Collectors.toList()));

    return gameObjectDTO;


}
private Map<String,Object> getParticipationAndGames(GamePlayer gamePlayer){
    Map<String, Object> gameObjectDTO = new LinkedHashMap<>();
    gameObjectDTO.put("game",gamePlayer.getGame());
    gameObjectDTO.put("gameplayer", gamePlayer.getId());
    return gameObjectDTO;

}

    @RequestMapping(value = "/players", method = RequestMethod.POST)
   public ResponseEntity<String> createUser (@RequestParam String userName,String password){

        if (userName.isEmpty()) {
            return new ResponseEntity<>("No name given", HttpStatus.FORBIDDEN);
        }
        Player player = playerRepository.findByUserName(userName);
        if(player != null){

            return new ResponseEntity<String>("Name already used",HttpStatus.CONFLICT);
        }

        playerRepository.save(new Player(userName, password));
        return new ResponseEntity<String>("Player added",HttpStatus.CREATED);

    }

    @RequestMapping(value = "/newGame", method = RequestMethod.POST)
    public ResponseEntity<String> createNewGame(Authentication authentication){

       Game newGame =gameRepository.save(new Game());
       Player playerAuth = playerRepository.findByUserName(authentication.getName());
       gamePlayerRepository.save(new GamePlayer(playerAuth,newGame));

        return new ResponseEntity<String>("GamePlayer created", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/joinGame/{gameId}", method = RequestMethod.POST)
    public ResponseEntity<String> joinGame(@PathVariable Long gameId, Authentication authentication){

        if (authentication!=null){
            Game joinedGame = gameRepository.findOne(gameId);
            if(joinedGame != null) {
                Player playerAuth = playerRepository.findByUserName(authentication.getName());
                gamePlayerRepository.save(new GamePlayer(playerAuth, joinedGame));

                return new ResponseEntity<String>("Game joined", HttpStatus.ACCEPTED);
            } else {
                return new  ResponseEntity<String>("error",HttpStatus.FORBIDDEN);
            }
        }
        else {
            return new ResponseEntity<String>("Player not logged",HttpStatus.UNAUTHORIZED);
        }

    }

  @RequestMapping(value = "/ships/{gamePlayerId}", method = RequestMethod.POST)
    public ResponseEntity<String> putShips (@PathVariable Long gamePlayerId, Authentication authentication,
                                                 @RequestBody Set<Ship> ships){
      Player currentPlayer =  playerRepository.findByUserName(authentication.getName());

        if(authentication.getName()==null){
            return new ResponseEntity<String>("not authenticated", HttpStatus.UNAUTHORIZED);

        }
        long currentPlayerGp = currentPlayer.getGamePlayers().stream().filter(gamePlayer -> gamePlayer.getId() == gamePlayerId).count();

     if(currentPlayerGp == 0) {

         return new ResponseEntity<String>("That's not your GamePlayer", HttpStatus.UNAUTHORIZED);
     }

        GamePlayer currentGamePlayer = gamePlayerRepository.findOne(gamePlayerId);

          if (currentGamePlayer.getShips().size() != 0){
              return new ResponseEntity<String>("You already have boats", HttpStatus.FORBIDDEN);
          }

      ships.forEach(ship -> currentGamePlayer.addShip(ship));
      ships.forEach(ship -> shipRepository.save(ship));

      return new ResponseEntity<String>("boats placed", HttpStatus.CREATED);

   }







}




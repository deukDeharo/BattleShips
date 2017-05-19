package salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);



	}

	@Bean
	public CommandLineRunner initData (PlayerRepository playerRepository,
									   GameRepository gameRepository,
									   GamePlayerRepository gamePlayerRepository,
									   ShipRepository shipRepository, SalvoRepository salvoRepository){
		return  args -> {

			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();

			Player player1 = new Player("Sputnik");
			Player player2 = new Player("IceMan");
			Player player3 = new Player("Sultan");
			Player player4 = new Player("GC-4");

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			GamePlayer gamePlayer1 = new GamePlayer(player2,game1);
			GamePlayer gamePlayer2 = new GamePlayer(player1,game1);

			GamePlayer gamePlayer3 = new GamePlayer(player3,game2);
			GamePlayer gamePlayer4 = new GamePlayer(player4,game2);

			GamePlayer gamePlayer5 = new GamePlayer(player1,game3);
			GamePlayer gamePlayer6 = new GamePlayer(player3,game3);

			//Loc del Carrier
			ArrayList<String> loc1 = new ArrayList<>(Arrays.asList("I6","I7","I8","I9","I10"));
			ArrayList<String> loc1_2 = new ArrayList<>(Arrays.asList("H1","H2","H3","H4","H5"));
			//Loc del BattleShip
			ArrayList<String> loc2 = new ArrayList<>(Arrays.asList("A4","B4","C4","D4"));
			ArrayList<String> loc2_2 = new ArrayList<>(Arrays.asList("A3","B3","C3","D3"));
			//Loc del Submarine
			ArrayList<String> loc3 = new ArrayList<>(Arrays.asList("H2","H3","H4"));
			ArrayList<String> loc3_2 = new ArrayList<>(Arrays.asList("J5","J6","J7"));
			//Loc del Destroyer
			ArrayList<String> loc4 = new ArrayList<>(Arrays.asList("E8","E9","E10"));
			ArrayList<String> loc4_2 = new ArrayList<>(Arrays.asList("E8","E9","E10"));
			//Loc del Patrol
			ArrayList<String> loc5 = new ArrayList<>(Arrays.asList("B8","B9"));
			ArrayList<String> loc5_2 = new ArrayList<>(Arrays.asList("A1","B1"));

			Ship ship1 = new Ship("Carrier",loc1);
			Ship ship2 = new Ship("BattleShip",loc2);
			Ship ship3 = new Ship("Submarine",loc3);
			Ship ship4 = new Ship("Destroyer",loc4);
			Ship ship5 = new Ship("Patrol",loc5);

			Ship ship6 = new Ship("Carrier",loc1_2);
			Ship ship7 = new Ship("BattleShip",loc2_2);
			Ship ship8 = new Ship("Submarine",loc3_2);
			Ship ship9 = new Ship("Destroyer",loc4_2);
			Ship ship10 = new Ship("Patrol",loc5_2);

			gamePlayer1.addShip(ship1);
			gamePlayer1.addShip(ship2);
			gamePlayer1.addShip(ship3);
			gamePlayer1.addShip(ship4);
			gamePlayer1.addShip(ship5);

			gamePlayer2.addShip(ship6);
			gamePlayer2.addShip(ship7);
			gamePlayer2.addShip(ship8);
			gamePlayer2.addShip(ship9);
			gamePlayer2.addShip(ship10);



			ArrayList<String> salvoLoc1 = new ArrayList<String>(Arrays.asList("H1","C6","I2"));
			ArrayList<String> salvoLoc2 = new ArrayList<String>(Arrays.asList("B1","B6","F2"));
			ArrayList<String> salvoLoc3 = new ArrayList<String>(Arrays.asList("H2","C7","I3"));
			ArrayList<String> salvoLoc4 = new ArrayList<String>(Arrays.asList("B2","B7","G3"));
			ArrayList<String> salvoLoc5 = new ArrayList<String>(Arrays.asList("H3","C8","I4"));
			ArrayList<String> salvoLoc6 = new ArrayList<String>(Arrays.asList("B3","B8","G4"));

			Salvo fire1 = new Salvo(1,salvoLoc1);
			Salvo fire2 = new Salvo(1,salvoLoc2);
			Salvo fire3 = new Salvo(2,salvoLoc3);
			Salvo fire4 = new Salvo(2,salvoLoc4);
			Salvo fire5 = new Salvo(3,salvoLoc5);
			Salvo fire6 = new Salvo(3,salvoLoc6);

			gamePlayer1.addSalvos(fire1);
			gamePlayer2.addSalvos(fire2);
			gamePlayer1.addSalvos(fire3);
			gamePlayer2.addSalvos(fire4);
			gamePlayer1.addSalvos(fire5);
			gamePlayer2.addSalvos(fire6);

//EL ORDEN DE LOS REOSITORYS INFLUYE EN SPRING
			//ADEMÁS, EL DEBUGGER IGNORA SI EL ELEMENTO ESTÁ MAPEADO. SIEMPRE EN EL ONE TO MANY
			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);

			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);

			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
			shipRepository.save(ship9);
			shipRepository.save(ship10);

			salvoRepository.save(fire1);
			salvoRepository.save(fire2);
			salvoRepository.save(fire3);
			salvoRepository.save(fire4);
			salvoRepository.save(fire5);
			salvoRepository.save(fire6);






			/*
			playerRepository.save(new Player("Sputnik"));
			playerRepository.save(new Player("IceMan"));
			playerRepository.save(new Player("Pedrolo"));
			playerRepository.save(new Player("Sultan"));
			playerRepository.save(new Player("GC-4"));


			gameRepository.save(game1);
			gameRepository.save(new Game());
			gameRepository.save(new Game());
			gameRepository.save(new Game());
			gameRepository.save(new Game());

			gamePlayerRepository.save(new GamePlayer(playerRepository.findByUserName("Sputnik"),
					gameRepository.findOne(game1.getId())));
			gamePlayerRepository.save(new GamePlayer(playerRepository.findByUserName("IceMan"),
					game1));

*/
		};
	}




}

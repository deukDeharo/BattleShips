package salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.Id;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);



	}

	@Bean
	public CommandLineRunner initData (PlayerRepository playerRepository,
									   GameRepository gameRepository,
									   GamePlayerRepository gamePlayerRepository){
		return  args -> {

			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();

			Player player1 = new Player("Sputnik");
			Player player2 = new Player("IceMan");
			Player player3 = new Player("Sultan");
			Player player4 = new Player("GC-4");

			GamePlayer gamePlayer1 = new GamePlayer(player2,game1);
			GamePlayer gamePlayer2 = new GamePlayer(player1,game1);

			GamePlayer gamePlayer3 = new GamePlayer(player3,game2);
			GamePlayer gamePlayer4 = new GamePlayer(player4,game2);

			GamePlayer gamePlayer5 = new GamePlayer(player1,game3);
			GamePlayer gamePlayer6 = new GamePlayer(player3,game3);

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);


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

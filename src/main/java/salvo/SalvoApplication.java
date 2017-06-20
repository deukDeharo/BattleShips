package salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
									   ShipRepository shipRepository,
									   SalvoRepository salvoRepository,
									   ScoreRepository scoreRepository){
		return  args -> {

			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();
			Game game4 = new Game();
			Game game5 = new Game();
			Game game6 = new Game();



			Player player1 = new Player("Sputnik","LoremIpsum1");
			Player player2 = new Player("IceMan","LoremIpsum2");
			Player player3 = new Player("Sultan","LoremIpsum3");
			Player player4 = new Player("GC-4","LoremIpsum4");

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);
			gameRepository.save(game5);
			gameRepository.save(game6);



			GamePlayer gamePlayer1 = new GamePlayer(player2,game1);
			GamePlayer gamePlayer2 = new GamePlayer(player1,game1);

			GamePlayer gamePlayer3 = new GamePlayer(player3,game2);
			GamePlayer gamePlayer4 = new GamePlayer(player4,game2);

			GamePlayer gamePlayer5 = new GamePlayer(player1,game3);
			GamePlayer gamePlayer6 = new GamePlayer(player3,game3);

			GamePlayer gamePlayer7 = new GamePlayer(player2,game4);
			GamePlayer gamePlayer8 = new GamePlayer(player4,game4);

			GamePlayer gamePlayer9 = new GamePlayer(player3,game5);
			GamePlayer gamePlayer10 = new GamePlayer(player1,game5);

			GamePlayer gamePlayer11 = new GamePlayer(player1,game6);
			GamePlayer gamePlayer12 = new GamePlayer(player4,game6);


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

			Score score1 = new Score(gamePlayer1,1);
			Score score2 = new Score(gamePlayer2,0);
			Score score3 = new Score(gamePlayer3,0.5f);
			Score score4 = new Score(gamePlayer4,0.5f);
			Score score5 = new Score(gamePlayer5,0);
			Score score6 = new Score(gamePlayer6,1);
			Score score7 = new Score(gamePlayer7,0.5f);
			Score score8 = new Score(gamePlayer8,0.5f);
			Score score9 = new Score(gamePlayer9,0.5f);
			Score score10 = new Score(gamePlayer10,1);


//EL ORDEN DE LOS REOSITORYS INFLUYE EN SPRING
			//ADEMÁS, EL DEBUGGER IGNORA SI EL ELEMENTO ESTÁ MAPEADO. SIEMPRE EN EL ONE TO MANY
			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);
			gamePlayerRepository.save(gamePlayer7);
			gamePlayerRepository.save(gamePlayer8);
			gamePlayerRepository.save(gamePlayer9);
			gamePlayerRepository.save(gamePlayer10);
			gamePlayerRepository.save(gamePlayer11);
			gamePlayerRepository.save(gamePlayer12);

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

			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			scoreRepository.save(score4);
			scoreRepository.save(score5);
			scoreRepository.save(score6);
			scoreRepository.save(score7);
			scoreRepository.save(score8);
			scoreRepository.save(score9);
			scoreRepository.save(score10);








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
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter{

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}


	@Bean
	UserDetailsService userDetailsService(){
	return new UserDetailsService() {
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Player player = playerRepository.findByUserName(username);
			if (player.getUserName()!=null){
				return new User(player.getUserName(),player.getPassword(),
						AuthorityUtils.createAuthorityList("USER"));
			}
			else {
				throw new UsernameNotFoundException("Unknown user: "+ username);
			}
		}
	};
	}
}
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure (HttpSecurity httpSecurity) throws Exception{

		httpSecurity.authorizeRequests()

				.antMatchers("/api/games").permitAll()
				.antMatchers("/api/players").permitAll()
				.antMatchers("/games.html").permitAll()
				.antMatchers("/styles/main.css").permitAll()
				.antMatchers("/scripts/games.js").permitAll()
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin()
				.usernameParameter("userName")
				.passwordParameter("password")
				.loginPage("/api/login")
				.and()
				.logout().logoutUrl("/api/logout");

		// turn off checking for CSRF tokens
		httpSecurity.csrf().disable();

		// if user is not authenticated, just send an authentication failure response
		httpSecurity.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if login is successful, just clear the flags asking for authentication
		httpSecurity.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

		// if login fails, just send an authentication failure response
		httpSecurity.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if logout is successful, just send a success response
		httpSecurity.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}



	}

}


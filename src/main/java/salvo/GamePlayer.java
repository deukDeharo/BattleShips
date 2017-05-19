package salvo;

import javax.persistence.*;
import java.util.*;

/**
 * Created by gerarddeharoramirez on 28/4/17.
 */
@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    Calendar calendar = Calendar.getInstance();

    private Date joinDate = calendar.getTime();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "gamePlayer" ,fetch = FetchType.EAGER)
    Set<Ship> ships = new LinkedHashSet<>();

    @OneToMany(mappedBy ="gamePlayer", fetch = FetchType.EAGER)
    Set<Salvo> salvos = new LinkedHashSet<>();

    public Set<Salvo> getSalvos() {
        return salvos;
    }

    public void setSalvos(Set<Salvo> salvos) {
        this.salvos = salvos;
    }

    public GamePlayer(){}



    public GamePlayer(Player player, Game game){
        this.player = player;
        this.game = game;

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }


    public Player getPlayer() {
        return player;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Ship> getShips(){
        return ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
        ship.setGamePlayer(this);
    }
    public void addSalvos ( Salvo salvo){
        salvos.add(salvo);
        salvo.setGamePlayer(this);

    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }
}

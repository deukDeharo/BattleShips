package salvo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gerarddeharoramirez on 28/4/17.
 */
@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private Date joinDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

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
}

package salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * Created by gerarddeharoramirez on 28/4/17.
 */
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    Calendar calendar = Calendar.getInstance();
    private Date date = calendar.getTime();
    public Game(){


    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public Date getDate() {
        return date;
    }

    @JsonIgnore
    public Set<GamePlayer> getGamePlayers(){
        return gamePlayers;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Game{" +
                "Id=" + Id +
                ", date=" + date +
                '}';
    }
}

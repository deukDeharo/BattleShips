package salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by gerarddeharoramirez on 27/4/17.
 */

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @OneToMany(mappedBy = "player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    private String userName;



    public Player() { }

    public Player(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return userName;
    }
}



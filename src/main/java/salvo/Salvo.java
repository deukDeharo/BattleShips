package salvo;

//import org.springframework.data.annotation.Id; ESTO LO COMENTO. El persistance es lo que funciona con la ID.

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerarddeharoramirez on 15/5/17.
 */
@Entity
public class Salvo {



    public int turn;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name = "locations")
    private List<String> locationList;

    public Salvo(){

    };

    public Salvo(int turn,ArrayList<String>locations){
        this.turn = turn;
        this.locationList = locations;

    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public List<String> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<String> locationList) {
        this.locationList = locationList;
    }
}

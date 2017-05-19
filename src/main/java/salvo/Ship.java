package salvo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gerarddeharoramirez on 8/5/17.
 */
@Entity
public class Ship {
    private String boatType;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ElementCollection
    @Column(name = "locations")
    private List<String> locationList;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayers")
    private GamePlayer gamePlayer;


    public Ship(){ };

    public Ship(String boatType, List<String> locationList){
        this.boatType = boatType;
        this.locationList = locationList;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<String> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<String> locationList) {
        this.locationList = locationList;
    }

    public GamePlayer getGamePlayer(){
        return this.gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
}

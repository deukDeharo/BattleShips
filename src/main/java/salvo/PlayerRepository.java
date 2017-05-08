package salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by gerarddeharoramirez on 27/4/17.
 */
@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player,Long>{

    Player findByUserName(String userName);
}

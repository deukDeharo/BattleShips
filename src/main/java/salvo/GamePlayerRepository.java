package salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by gerarddeharoramirez on 28/4/17.
 */
@RepositoryRestResource
public interface GamePlayerRepository extends JpaRepository <GamePlayer, Long>{


}

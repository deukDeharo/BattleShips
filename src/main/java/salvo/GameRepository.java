package salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.Id;

/**
 * Created by gerarddeharoramirez on 28/4/17.
 */
@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game,Long> {



}

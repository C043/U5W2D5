package fragnito.U5W2D5.repositories;

import fragnito.U5W2D5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
}

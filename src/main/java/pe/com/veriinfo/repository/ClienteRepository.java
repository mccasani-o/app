package pe.com.veriinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.veriinfo.entity.ClienteEntity;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
}

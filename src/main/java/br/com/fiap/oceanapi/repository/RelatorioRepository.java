package br.com.fiap.oceanapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fiap.oceanapi.model.Relatorio;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
    
}

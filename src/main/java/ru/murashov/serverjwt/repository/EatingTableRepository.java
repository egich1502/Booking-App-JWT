package ru.murashov.serverjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.murashov.serverjwt.domain.EatingTable;

public interface EatingTableRepository extends JpaRepository<EatingTable, Integer> {

}

package ru.murashov.serverjwt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.serverjwt.domain.EatingTable;
import ru.murashov.serverjwt.repository.EatingTableRepository;

@Service
public class EatingTableService {

  private final EatingTableRepository eatingTableRepository;

  @Autowired
  public EatingTableService(EatingTableRepository eatingTableRepository) {
    this.eatingTableRepository = eatingTableRepository;
  }

  public List<EatingTable> getAllTables() {
    return eatingTableRepository.findAll();
  }

  public EatingTable getTableById(int id) {
    return eatingTableRepository.findById(id).orElseThrow();
  }

  public EatingTable saveTable(EatingTable eatingTable){
    return eatingTableRepository.save(eatingTable);
  }
}

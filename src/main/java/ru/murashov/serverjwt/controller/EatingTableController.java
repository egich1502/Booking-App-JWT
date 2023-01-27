package ru.murashov.serverjwt.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.murashov.serverjwt.domain.EatingTable;
import ru.murashov.serverjwt.service.EatingTableService;

@RestController
@RequestMapping("api/tables")
@RequiredArgsConstructor
public class EatingTableController {

  private final EatingTableService eatingTableService;


  @GetMapping(path = "", produces = "application/json")
  public List<EatingTable> getAllTables() {
    return eatingTableService.getAllTables();
  }

  @PostMapping(path = "save", consumes = "application/json", produces = "application/json")
  public ResponseEntity<EatingTable> saveTable(@RequestBody EatingTable eatingTable) {
    return ResponseEntity.ok(eatingTableService.saveTable(eatingTable));
  }

}

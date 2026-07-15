package br.com.nameList.controller;

import br.com.nameList.model.PersonName;
import br.com.nameList.repository.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/names")
public class NameController {

    @Autowired
    private NameRepository nameRepository;

    // Endpoint para inserir um nome
    @PostMapping
    public ResponseEntity<PersonName> createName(@RequestBody PersonName personName) {
        PersonName savedName = nameRepository.save(personName);
        return ResponseEntity.ok(savedName);
    }

    // Endpoint para modificar todos os nomes iguais
    @PutMapping("/{oldName}")
    public ResponseEntity<String> updateNames(
            @PathVariable String oldName, 
            @RequestParam String newName) {
        
        int updatedCount = nameRepository.updateAllNames(oldName, newName);
        return ResponseEntity.ok("Updated " + updatedCount + " record(s).");
    }

    // Endpoint para deletar todos os nomes iguais
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteNames(@PathVariable String name) {
        
        int deletedCount = nameRepository.deleteAllByName(name);
        return ResponseEntity.ok("Deleted " + deletedCount + " record(s).");
    }
}
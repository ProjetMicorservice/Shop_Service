package com.example.shop_service.Controller;

import com.example.shop_service.DTO.EggDTO;
import com.example.shop_service.Service.EggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eggs")
public class EggController {
    private final EggService eggService;

    @Autowired
    public EggController(EggService eggService) {
        this.eggService = eggService;
    }

    @GetMapping("/{eggId}")
    public ResponseEntity<EggDTO> getEggById(@PathVariable Long eggId) {
        EggDTO eggDTO = eggService.getEggById(eggId);
        return eggDTO != null ? ResponseEntity.ok(eggDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<EggDTO>> getAllEggs() {
        List<EggDTO> allEggs = eggService.getAllEggs();
        return ResponseEntity.ok(allEggs);
    }

    @PostMapping
    public ResponseEntity<EggDTO> saveEgg(@RequestBody EggDTO eggDTO) {
        EggDTO savedEgg = eggService.saveEgg(eggDTO);
        return ResponseEntity.ok(savedEgg);
    }

    @PutMapping("/{eggId}")
    public ResponseEntity<EggDTO> updateEgg(@PathVariable Long eggId, @RequestBody EggDTO eggDTO) {
        EggDTO updatedEgg = eggService.updateEgg(eggId, eggDTO);
        return updatedEgg != null ? ResponseEntity.ok(updatedEgg) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{eggId}")
    public ResponseEntity<Void> deleteEgg(@PathVariable Long eggId) {
        eggService.deleteEgg(eggId);
        return ResponseEntity.noContent().build();
    }
}

package com.example.shop_service.Service;

import com.example.shop_service.DTO.EggDTO;

import java.util.List;

public interface EggService {
    EggDTO getEggById(Long eggId);
    List<EggDTO> getAllEggs();
    EggDTO saveEgg(EggDTO eggDTO);
    EggDTO updateEgg(Long eggId, EggDTO eggDTO);
    void deleteEgg(Long eggId);
}

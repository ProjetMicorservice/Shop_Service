package com.example.shop_service.ServiceImpl;

import com.example.shop_service.DTO.EggDTO;
import com.example.shop_service.Model.EggEntity;
import com.example.shop_service.Repository.EggRepository;
import com.example.shop_service.Service.EggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EggServiceImpl implements EggService {
    private final EggRepository eggRepository;

    @Autowired
    public EggServiceImpl(EggRepository eggRepository) {
        this.eggRepository = eggRepository;
    }

    @Override
    public EggDTO getEggById(Long eggId) {
        Optional<EggEntity> eggOptional = eggRepository.findById(eggId);
        return eggOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<EggDTO> getAllEggs() {
        List<EggEntity> allEggs = eggRepository.findAll();
        return allEggs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public EggDTO saveEgg(EggDTO eggDTO) {
        EggEntity eggEntity = convertToEntity(eggDTO);
        EggEntity savedEntity = eggRepository.save(eggEntity);
        return convertToDTO(savedEntity);
    }

    @Override
    public EggDTO updateEgg(Long eggId, EggDTO eggDTO) {
        Optional<EggEntity> existingEggOptional = eggRepository.findById(eggId);

        if (existingEggOptional.isPresent()) {
            EggEntity existingEgg = existingEggOptional.get();
            // Update fields based on your business logic
            existingEgg.setType(eggDTO.getType());
            existingEgg.setPrice(eggDTO.getPrice());
            existingEgg.setQuantity(eggDTO.getQuantity());

            EggEntity updatedEgg = eggRepository.save(existingEgg);
            return convertToDTO(updatedEgg);
        } else {
            // Handle egg not found
            return null;
        }
    }

    @Override
    public void deleteEgg(Long eggId) {
        eggRepository.deleteById(eggId);
    }

    // Helper methods for conversion between DTO and Entity
    private EggDTO convertToDTO(EggEntity eggEntity) {
        EggDTO eggDTO = new EggDTO();
        eggDTO.setId(eggEntity.getId());
        eggDTO.setType(eggEntity.getType());
        eggDTO.setPrice(eggEntity.getPrice());
        eggDTO.setQuantity(eggEntity.getQuantity());
        return eggDTO;
    }

    private EggEntity convertToEntity(EggDTO eggDTO) {
        EggEntity eggEntity = new EggEntity();
        eggEntity.setId(eggDTO.getId());
        eggEntity.setType(eggDTO.getType());
        eggEntity.setPrice(eggDTO.getPrice());
        eggEntity.setQuantity(eggDTO.getQuantity());
        return eggEntity;
    }
}

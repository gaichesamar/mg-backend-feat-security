package com.dev.mygavel.services;

import com.dev.mygavel.dto.OfficeDto;
import com.dev.mygavel.entities.Office;
import com.dev.mygavel.repo.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OfficeServiceImp implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public Office create(OfficeDto officeDto) {

        if (officeRepository.existsByEmail(officeDto.getEmail())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        } else {
            Office office = new Office();
            office.setLogo(officeDto.getLogo());
            office.setDateCreation(new Date());
            office.setNom(officeDto.getNom());
            office.setEmail(officeDto.getEmail());
            office.setSiteWeb(officeDto.getSiteWeb());

            System.out.println(office);

            return officeRepository.save(office);
        }
    }

    @Override
    public void update(OfficeDto officeDto) {
        if (officeRepository.existsById(officeDto.getId())) {
            Office office = officeRepository.getById(officeDto.getId());

            office.setLogo(officeDto.getLogo());
            office.setDateCreation(officeDto.getDateCreation());
            office.setNom(officeDto.getNom());
            office.setEmail(officeDto.getEmail());
            office.setSiteWeb(officeDto.getSiteWeb());
            officeRepository.save(office);

            System.out.println(office);

        } else {
            throw new IllegalArgumentException("Ce cabinet n'existe pas.");
        }
    }

    @Override
    public void delete(OfficeDto officeDto) {
        if (officeRepository.existsById(officeDto.getId())) {
            Office office = officeRepository.getById(officeDto.getId());

            office.setDateSupp(new Date());
            officeRepository.save(office);

            System.out.println(office);

        } else {
            throw new IllegalArgumentException("Ce cabinet n'existe pas.");
        }
    }

    public List<Office> getAllOffices() {
        return officeRepository.findByDateSuppNull();
    }
}

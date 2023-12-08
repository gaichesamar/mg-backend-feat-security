package com.dev.mygavel.services;

import com.dev.mygavel.dto.OfficeDto;
import com.dev.mygavel.entities.Office;

import java.util.List;

public interface OfficeService {
    Office create(OfficeDto officeDto);

    void update(OfficeDto officeDto);

    void delete(OfficeDto officeDto);

    List<Office> getAllOffices();
}

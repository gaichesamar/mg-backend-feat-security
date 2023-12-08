package com.dev.mygavel.web;

import com.dev.mygavel.dto.OfficeDto;
import com.dev.mygavel.entities.Office;
import com.dev.mygavel.services.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
public class OfficeController {

    public static final Logger logger = LoggerFactory.getLogger(OfficeController.class);
    @Autowired
    private OfficeService officeService;

    @PostMapping("/create")
    public Office create(@RequestBody OfficeDto officeDto) {
        logger.info("register");
        return officeService.create(officeDto);
    }

    @PostMapping("/update")
    public void update(@RequestBody OfficeDto officeDto) {
        logger.info("register");
          officeService.update(officeDto);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody OfficeDto officeDto) {
        logger.info("register");
         officeService.delete(officeDto);
    }

    @GetMapping("/getAll")
    public List<Office> getAllOffices() {
        logger.info("get all");
        return officeService.getAllOffices();
    }
}

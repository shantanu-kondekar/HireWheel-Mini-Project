package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/hirewheels/v1")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity AddVehicle(@RequestBody VehicleDTO vehicleDTO) throws Exception {

        Vehicle newVehicle = modelMapper.map(vehicleDTO, Vehicle.class);

        Vehicle savedVehicle = adminService.registerVehicle(newVehicle);

        VehicleDTO savedVehicleDTO = modelMapper.map(savedVehicle, VehicleDTO.class);

        return new ResponseEntity(savedVehicleDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/vehicles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateVehicle(@PathVariable(value = "id") int id, @RequestBody VehicleDTO vehicleDTO){

        Vehicle newVehicle = modelMapper.map(vehicleDTO, Vehicle.class);

        Vehicle updatedVehicle = adminService.changeAvailability(id, newVehicle.getAvailabilityStatus());

        VehicleDTO updatedVehicleDTO = modelMapper.map(updatedVehicle, VehicleDTO.class);

        return new ResponseEntity(updatedVehicleDTO, HttpStatus.ACCEPTED);

    }

}

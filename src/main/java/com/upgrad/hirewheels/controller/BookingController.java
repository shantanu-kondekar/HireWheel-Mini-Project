package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.BookingService;
import com.upgrad.hirewheels.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/hirewheels/v1")

public class BookingController {


    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity AddBooking(@RequestBody BookingDTO bookingDTO) throws Exception {

        Booking newBooking = modelMapper.map(bookingDTO, Booking.class);

        settingVehicle(bookingDTO, newBooking);

        Booking saveBooking = bookingService.addBooking(newBooking);

        BookingDTO saveBookingDTO = modelMapper.map(saveBooking, BookingDTO.class);

        return new ResponseEntity(saveBookingDTO, HttpStatus.CREATED);

    }

    private void settingVehicle(BookingDTO bookingDTO, Booking booking) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(bookingDTO.getVehicleId());
        booking.setVehicleWithBooking(vehicle);
    }

}

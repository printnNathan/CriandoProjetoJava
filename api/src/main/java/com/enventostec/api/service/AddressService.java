package com.enventostec.api.service;

import com.enventostec.api.Domain.address.Address;
import com.enventostec.api.Domain.event.Event;
import com.enventostec.api.Domain.event.EventRequestDTO;
import com.enventostec.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(EventRequestDTO dto, Event event) {
        Address address = new Address();
        address.setCity(dto.city());
        address.setUf(dto.state());
        address.setEvent(event);

        return addressRepository.save(address);
    }
}
package com.galva.dealership.location;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/locations/{location_id}")
    public Optional<Location> getOneLocation(@PathVariable(value = "location_id") Long location_id){
        Optional<Location> location = locationRepository.findById(location_id);
        return location;
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    @PatchMapping("/locations/{location_id}")
    public Location updateLocation(@PathVariable(value = "location_id") Long location_id, @RequestBody Location locationSpecs) throws NotFoundException {
        Location location = locationRepository.findById(location_id).orElseThrow(() -> new NotFoundException("not found"));
        location.setName(locationSpecs.getName());
        location.setAddress(locationSpecs.getAddress());

        Location updatedLocation = locationRepository.save(location);
        return updatedLocation;
    }

    @PostMapping("/locations")
    public Location createLocation(@RequestBody Location location){
        return locationRepository.save(location);
    }

    @DeleteMapping("/locations/{location_id}")
    public void deleteLocation(@PathVariable(value = "location_id") Long location_id) throws NotFoundException {
        Location location = locationRepository.findById(location_id).orElseThrow(() -> new NotFoundException("not found"));
        if(location.getId().equals(location_id)) {
            locationRepository.delete(location);
        }
    }
}

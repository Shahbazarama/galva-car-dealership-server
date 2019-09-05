package com.galva.dealership.car;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/cars/{car_id}")
    public Optional<Car> getOneCar(@PathVariable(value = "car_id") Long car_id){
        Optional<Car> car = carRepository.findById(car_id);
        return car;
    }

    // find all cars at a specific location
    @GetMapping("/cars/location/{location_id}")
    public List<Car> getLocationCars(@PathVariable(value = "location_id") int locationId){
        List<Car> cars = carRepository.findByLocationId(locationId);
        return cars;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return (List<Car>) carRepository.findAll();
    }

    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car){
        return carRepository.save(car);
    }

    @PatchMapping("/cars/{car_id}")
    public Car updateCar(@PathVariable(value = "car_id") Long car_id, @RequestBody Car carSpecs) throws NotFoundException {

        Car car = carRepository.findById(car_id).orElseThrow(() -> new NotFoundException("not found"));
        car.setYear(carSpecs.getYear());
        car.setMake(carSpecs.getMake());
        car.setModel(carSpecs.getModel());
        car.setMiles(carSpecs.getMiles());
        car.setPrice(carSpecs.getPrice());
        car.setVin(carSpecs.getVin());
        car.setPhoto_url(carSpecs.getPhoto_url());
        car.setLocationId(carSpecs.getLocationId());


        Car updatedCar = carRepository.save(car);
        return updatedCar;
    }

    @DeleteMapping("/cars/{car_id}")
    public void deleteCar(@PathVariable(value = "car_id") Long car_id) throws NotFoundException{
        Car car = carRepository.findById(car_id)
                .orElseThrow(() -> new NotFoundException("not found"));
        if(car.getId().equals(car_id)){
            carRepository.delete(car);
        }
    }
}

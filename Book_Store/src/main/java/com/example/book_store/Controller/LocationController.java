package com.example.book_store.Controller;

import com.example.book_store.Api.ApiException;
import com.example.book_store.DTO.LocationDTO;
import com.example.book_store.Model.Location;
import com.example.book_store.Service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/Location")
@RestController
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    @GetMapping("/get")
    public ResponseEntity getLocations(){
        List<Location>locations=locationService.getLocations();
        return ResponseEntity.status(200).body(locations);
    }
    @PostMapping("/add")
    public ResponseEntity addLocation(@Valid @RequestBody LocationDTO locationDTO){
        locationService.addLocation(locationDTO);
        return ResponseEntity.status(200).body("Location Added");
    }
    @PutMapping("/update")
    public ResponseEntity updateLocation(@Valid@RequestBody LocationDTO locationDTO){
        boolean isUpdate= locationService.updateLocation(locationDTO);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Location Updated");
        }
        throw new ApiException("Invalid Location ");
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteLocation(@PathVariable Integer id){
        boolean isDelete= locationService.deleteLocation(id);
        if(isDelete) {
            return ResponseEntity.status(200).body("Location Deleted");
        }
        throw new ApiException("Invalid Location ");
    }
}

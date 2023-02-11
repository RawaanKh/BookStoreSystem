package com.example.book_store.Service;

import com.example.book_store.Api.ApiException;
import com.example.book_store.DTO.LocationDTO;
import com.example.book_store.Model.Location;
import com.example.book_store.Model.Store;
import com.example.book_store.Repository.LocationRepository;
import com.example.book_store.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;
    public List<Location>getLocations(){
        return locationRepository.findAll();
    }
    public void addLocation(LocationDTO locationDTO){
        Store store=storeRepository.findStoreById(locationDTO.getStore_id());
        if(store==null){
            throw new ApiException("Required Store Not Found");
        }
        Location location=new Location(null,locationDTO.getArea(),locationDTO.getStreet(),store);
        locationRepository.save(location);
    }
    public boolean updateLocation(LocationDTO locationDTO){
        Location location=locationRepository.findLocationById(locationDTO.getStore_id());
        if (location==null){
            throw new ApiException("Store Not Found");
        }
        location.setArea(locationDTO.getArea());
        location.setStreet(locationDTO.getStreet());
        locationRepository.save(location);
        return true;
    }
    public boolean deleteLocation(Integer id){
        Location location=locationRepository.findLocationById(id);
        if(location==null){
            return false;
        }
        locationRepository.delete(location);
        return true;
    }

}

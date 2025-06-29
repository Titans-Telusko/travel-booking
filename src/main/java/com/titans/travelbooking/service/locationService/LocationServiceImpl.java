package com.titans.travelbooking.service.locationService;

import com.titans.travelbooking.entity.Location;
import com.titans.travelbooking.repository.LocationRepo;
import com.titans.travelbooking.validation.LocationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocationServiceImpl implements ILocationService {

    private final LocationRepo locationRepo;

    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public Location saveLocation(LocationRequest locationReq) {

        Location res = null;

        Location location = Location.builder()
                .fromPlace(locationReq.getFromPlace())
                .toPlace(locationReq.getToPlace())
                .build();

        try {
            res = locationRepo.save(location);
        } catch (Exception e) {
            log.error("Location saving failed: {}", e.getMessage());
        }

        return res;
    }

}

package com.titans.travelbooking.service.locationService;

import com.titans.travelbooking.entity.Location;
import com.titans.travelbooking.validation.LocationRequest;

public interface ILocationService {

    Location saveLocation(LocationRequest location);

}

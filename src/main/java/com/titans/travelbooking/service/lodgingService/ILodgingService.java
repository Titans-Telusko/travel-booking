package com.titans.travelbooking.service.lodgingService;

import com.titans.travelbooking.dto.LodgingPatchRequest;
import com.titans.travelbooking.entity.Lodging;
import com.titans.travelbooking.validation.LodgingRequest;

import java.util.List;
import java.util.Optional;

public interface ILodgingService {
    Lodging addLodge(LodgingRequest lodge);

    List<Lodging> getAllLodges();

    Optional<Lodging> getLodgeById(Integer id);

    Lodging updateLodge(Integer id, LodgingRequest lodge);

    Lodging patchLodgeDetails(Integer id, LodgingPatchRequest lodge);

    String deleteLodge(Integer id);

}

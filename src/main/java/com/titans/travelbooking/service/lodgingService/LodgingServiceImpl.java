package com.titans.travelbooking.service.lodgingService;

import com.titans.travelbooking.dto.LodgingPatchRequest;
import com.titans.travelbooking.entity.Lodging;
import com.titans.travelbooking.exception.LodgeNotFoundException;
import com.titans.travelbooking.exception.LodgingServiceException;
import com.titans.travelbooking.repository.LodgingRepo;
import com.titans.travelbooking.validation.LodgingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LodgingServiceImpl implements ILodgingService {

    private final LodgingRepo lodgingRepo;

    public LodgingServiceImpl(LodgingRepo lodgingRepo) {
        this.lodgingRepo = lodgingRepo;
    }

    @Override
    public Lodging addLodge(LodgingRequest lodge) {
        try {
            log.info("Received request to add lodge: {}", lodge.getHotelName());

            Lodging lodging = lodgingReqToLodging(lodge);
            Lodging savedLodging = lodgingRepo.save(lodging);

            log.info("Successfully saved lodging with ID: {}", savedLodging.getLId());
            return savedLodging;
        } catch (Exception e) {
            log.error("Error while saving lodging: {}", e.getMessage());
            throw new LodgingServiceException("Failed to save lodging", e);
        }
    }

    @Override
    public List<Lodging> getAllLodges() {
        try {
            List<Lodging> allLodges = lodgingRepo.findAll();
            log.info("Fetched all the lodge available with count: {}", (long) allLodges.size());
            return allLodges;
        }  catch (Exception e) {
            log.error("Error while fetching all the lodges: {}", e.getMessage());
            throw new LodgingServiceException("Failed to get all lodges", e);
        }
    }

    @Override
    public Optional<Lodging> getLodgeById(Integer id) {
        try {
            Optional<Lodging> fetchedLodged = lodgingRepo.findById(id);
            if (fetchedLodged.isEmpty()) {
                log.info("Lodge not found for the ID: {}", id);
            } else
                log.info("Lodge successfully fetched with ID: {}", id);

            return fetchedLodged;
        }  catch (Exception e) {
            log.error("Error while fetching the lodge with ID: {}, {}", id, e.getMessage());
            throw new LodgingServiceException("Failed to get the lodge", e);
        }
    }

    @Override
    public Lodging updateLodge(Integer id, LodgingRequest lodge) {

        Lodging existingLodge = lodgingRepo.findById(id)
                .orElseThrow(() -> new LodgeNotFoundException("Lodge is not found for the ID " + id));

        existingLodge.setHotelName(lodge.getHotelName());
        existingLodge.setCity(lodge.getCity());
        existingLodge.setRating(lodge.getRating());
        existingLodge.setPrice(lodge.getPrice());
        existingLodge.setRoomsAvailabele(lodge.getRoomsAvailabele());
        existingLodge.setCountry(lodge.getCountry());
        existingLodge.setImageUrl(lodge.getImageUrl());
        existingLodge.setCity(lodge.getCity());

        return lodgingRepo.save(existingLodge);
    }

    @Override
    public Lodging patchLodgeDetails(Integer id, LodgingPatchRequest patch) {
        Lodging existingLodge = lodgingRepo.findById(id)
                .orElseThrow(() -> new LodgeNotFoundException("Lodge is not found for the ID " + id));

        if(patch.hotelName() != null) existingLodge.setHotelName(patch.hotelName());
        if(patch.hotelType() != null) existingLodge.setHotelType(patch.hotelType());
        if(patch.rating() != null) existingLodge.setRating(patch.rating());
        if(patch.price() != null) existingLodge.setPrice(patch.price());
        if(patch.roomsAvailabele() != null) existingLodge.setRoomsAvailabele(patch.roomsAvailabele());
        if(patch.country() != null) existingLodge.setCountry(patch.country());
        if(patch.imageUrl()!= null) existingLodge.setImageUrl(patch.imageUrl());
        if(patch.city() != null) existingLodge.setCity(patch.city());

        return lodgingRepo.save(existingLodge);
    }

    @Override
    public String deleteLodge(Integer id) {
        Lodging existingLodge = lodgingRepo.findById(id)
                .orElseThrow(() -> new LodgeNotFoundException("Lodge is not found for the ID " + id));

        lodgingRepo.deleteById(id);

        return String.format("Lodge with ID %d has been removed from the list.", id);
    }

    private Lodging lodgingReqToLodging(LodgingRequest lodgingRequest) {
        return Lodging.builder()
                .hotelName(lodgingRequest.getHotelName())
                .hotelType(lodgingRequest.getHotelType())
                .rating(lodgingRequest.getRating())
                .price(lodgingRequest.getPrice())
                .roomsAvailabele(lodgingRequest.getRoomsAvailabele())
                .country(lodgingRequest.getCountry())
                .imageUrl(lodgingRequest.getImageUrl())
                .city(lodgingRequest.getCity())
                .build();
    }

}

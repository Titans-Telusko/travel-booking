package com.titans.travelbooking.controller.LodgingController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titans.travelbooking.dto.CustomErrorResponse;
import com.titans.travelbooking.dto.CustomSingleSuccessResponse;
import com.titans.travelbooking.dto.CustomSuccessResponse;
import com.titans.travelbooking.dto.LodgingPatchRequest;
import com.titans.travelbooking.entity.Lodging;
import com.titans.travelbooking.service.lodgingService.ILodgingService;
import com.titans.travelbooking.utils.CloudinaryUpload;
import com.titans.travelbooking.validation.LodgingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/lodging")
public class LodgingController {

    @Autowired
    private ILodgingService service;

    @Autowired
    private CloudinaryUpload cloudinaryUpload;

    @PostMapping("/add-lodge")
    public ResponseEntity<Lodging> addLodge(
            @RequestParam("lodge") String lodgeJson,
            @RequestParam("image") MultipartFile imageFile
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        LodgingRequest lodgingRequest = mapper.readValue(lodgeJson, LodgingRequest.class);

        String imageSource = cloudinaryUpload.uploadMedia(imageFile);
        log.info("Image successfully uploaded to the Cloudinary storage");

        lodgingRequest.setImageUrl(imageSource);

        Lodging savedLodge = service.addLodge(lodgingRequest);

        return new ResponseEntity<>(savedLodge, HttpStatus.CREATED);
    }

    @GetMapping("/all-lodges")
    public ResponseEntity<CustomSuccessResponse<Lodging>> fetchLodges() {
        log.info("List all the lodges");

        List<Lodging> fetchedLodges = service.getAllLodges();

        CustomSuccessResponse<Lodging> response = new CustomSuccessResponse<>(
                HttpStatus.OK.value(),
                "Successfully fetched all lodges",
                new ArrayList<>(fetchedLodges)
        );
        log.info("Successfully fetched all the lodges");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchLodgeById(@PathVariable Integer id) {
        log.info("Fetching lodge with ID: {}", id);
        Optional<Lodging> lodge = service.getLodgeById(id);

        if (lodge.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomErrorResponse(404, "No lodge found with ID " + id, "Lodge Not Found"));
        }

        CustomSingleSuccessResponse<Lodging> response = new CustomSingleSuccessResponse<>(
                HttpStatus.OK.value(),
                "Lodge fetched successfully",
                lodge.get()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-lodge/{id}")
    public ResponseEntity<CustomSingleSuccessResponse<Lodging>> updateLodgeDetails(
            @PathVariable Integer id,
            @RequestBody LodgingRequest lodgingRequest
    ) {
        log.info("Updating lodge with ID: {}", id);

        Lodging updatedLodge = service.updateLodge(id, lodgingRequest);

        CustomSingleSuccessResponse<Lodging> response = new CustomSingleSuccessResponse<>(
                HttpStatus.OK.value(),
                "Lodge updated successfully",
                updatedLodge
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/patch-lodge/{id}")
    public ResponseEntity<?> patchLodgeDetails(
            @PathVariable Integer id,
            @RequestBody LodgingPatchRequest lodgingRequest
    ) {
        log.info("Patching lodge with ID: {}", id);

        Lodging updatedLodge = service.patchLodgeDetails(id, lodgingRequest);

        CustomSingleSuccessResponse<Lodging> response = new CustomSingleSuccessResponse<>(
                HttpStatus.OK.value(),
                "Lodge updated successfully",
                updatedLodge
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remove-lodge/{id}")
    public ResponseEntity<?> removeLodge(@PathVariable Integer id) {
        log.info("Deleting lodge with ID: {}", id);

        String message = service.deleteLodge(id);

        CustomSingleSuccessResponse<String> response = new CustomSingleSuccessResponse<>(
                HttpStatus.OK.value(),
                "Lodge Deleted successfully",
                message
        );

        return ResponseEntity.ok(response);
    }

}

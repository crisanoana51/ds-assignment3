package device.microservice.controllers;


import device.microservice.entities.Reading;
import device.microservice.payload.request.ReadingRequest;
import device.microservice.payload.response.MessageResponse;
import device.microservice.services.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/reading")
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @GetMapping("/findAll")
    public List<Reading> getReadings(){
        return readingService.findAllReadings();
    }

    @PostMapping("/addReading")
   public ResponseEntity<MessageResponse> addReading(@RequestBody ReadingRequest readingRequest){
        Reading reading = new Reading(readingRequest.getTimestamp(),readingRequest.getConsumption());
        readingService.addReading(reading);
        return ResponseEntity.ok(new MessageResponse("Reading added successfully!"));

   }

   @PutMapping("/updateReading")
   public ResponseEntity<MessageResponse> updateReading(@RequestBody ReadingRequest readingRequest){
        Reading updatedReading = readingService.getReadingById(readingRequest.getId());
        updatedReading.setTimestamp(readingRequest.getTimestamp());
        updatedReading.setMaximumHourlyEnergyConsumption(readingRequest.getConsumption());
        readingService.updateReading(updatedReading);
       return ResponseEntity.ok(new MessageResponse("Reading updated successfully!"));
   }

   @DeleteMapping("/deleteReading/{id}")
   public ResponseEntity<MessageResponse> deleteReading(@PathVariable Long id){
        readingService.deleteReading(id);
       return ResponseEntity.ok(new MessageResponse("Reading deleted successfully!"));
   }
}

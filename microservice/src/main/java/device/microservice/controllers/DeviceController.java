package device.microservice.controllers;


import device.microservice.entities.Device;
import device.microservice.payload.request.AddDeviceRequest;
import device.microservice.payload.request.UpdateDeviceRequest;
import device.microservice.payload.response.MessageResponse;
import device.microservice.rabbitmq.MessageSender;
import device.microservice.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/device")
public class DeviceController {


    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MessageSender messageSender;


    @PostMapping("/addDevice")
    public ResponseEntity<MessageResponse> addDevice(@RequestBody AddDeviceRequest addDeviceRequest){
        System.out.println(addDeviceRequest.getName() + " " + addDeviceRequest.getAddress() + " " + addDeviceRequest.getDescription() + " " + addDeviceRequest.getUserId() + " " + addDeviceRequest.getMaximumHourlyEnergyConsumption());
            Device device = new Device(addDeviceRequest.getName(), addDeviceRequest.getDescription(), addDeviceRequest.getAddress(), addDeviceRequest.getMaximumHourlyEnergyConsumption(), addDeviceRequest.getUserId());
            deviceService.addDevice(device);
            messageSender.sendMessage(device);

            return ResponseEntity.ok(new MessageResponse("Device added successfully!"));

    }

    @PutMapping("/updateDevice")
    public ResponseEntity<MessageResponse> updateDevice(@RequestBody UpdateDeviceRequest updateDeviceRequest){

        Device updatedDevice = deviceService.getDeviceById(updateDeviceRequest.getId());
        updatedDevice.setName(updateDeviceRequest.getName());
        updatedDevice.setDescription(updateDeviceRequest.getDescription());
        updatedDevice.setAddress(updateDeviceRequest.getAddress());
        updatedDevice.setMaximumHourlyEnergyConsumption(updateDeviceRequest.getMaximumHourlyEnergyConsumption());
        deviceService.updateDevice(updatedDevice);
        return ResponseEntity.ok(new MessageResponse("Device updated successfully!"));

    }

    @DeleteMapping("/deleteDevice/{id}")
    public ResponseEntity<MessageResponse> deleteDevice(@PathVariable Long id){
        deviceService.deleteDevice(id);
        return ResponseEntity.ok(new MessageResponse("Device deleted successfully!"));
    }

    @GetMapping("/findAll")
    public List<Device> getAllDevices(){
        return deviceService.findAllDevices();
    }


    @GetMapping("/findUserDevices/{id}")
    public List<Device> getDeviceByUser(@PathVariable Long id){
        return deviceService.getDeviceByUserId(id);
    }


    @DeleteMapping("/deleteDevicesByUser/{id}")
    public ResponseEntity<MessageResponse> deleteUserDevices(@PathVariable Long id){
        deviceService.deleteDeviceByUserId(id);
        return ResponseEntity.ok(new MessageResponse("Devices deleted successfully!"));
    }
}

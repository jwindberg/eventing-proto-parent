package com.marsraver.eventing.client;

import com.avaya.adf.model.Origin;
import com.avaya.adf.model.property.PropertyState;
import com.avaya.adf.model.property.PropertyType;
import com.avaya.adf.model.property.WritePropertyRequest;
import com.google.protobuf.Timestamp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("eventStore")
public class EventStoreController {

  private EventStoreClientService eventStoreService;

  @GetMapping("writeProperty")
  public ResponseEntity<String> writeProperty() {
    WritePropertyRequest request = WritePropertyRequest.newBuilder()
        .setUserHandle("userHandle")
        .setCreated(Timestamp.newBuilder().build())
        .setOrigin(Origin.UNSPECIFIED)
        .setOriginValue(123)
        .setPropertyState(PropertyState.PROPERTYSTATE_UNSPECIFIED)
        .setPropertyStateValue(456)
        .setPropertyType(PropertyType.PROPERTYTYPE_UNSPECIFIED)
        .build();
    eventStoreService.writeProperty(request);
    return ResponseEntity.ok(null);
  }

}

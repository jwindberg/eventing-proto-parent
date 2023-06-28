package com.marsraver.eventing.client;

import com.avaya.adf.model.Origin;
import com.avaya.adf.model.property.PropertyState;
import com.avaya.adf.model.property.PropertyType;
import com.avaya.adf.model.property.WritePropertyRequest;
import com.avaya.adf.service.gateway.EventStoreGrpc;
import com.avaya.adf.service.gateway.EventStoreGrpc.EventStoreBlockingStub;
import com.google.protobuf.Timestamp;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MainClient {

  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
        .usePlaintext()
        .build();
    EventStoreBlockingStub eventStoreBlockingStub = EventStoreGrpc.newBlockingStub(channel);

    WritePropertyRequest request = WritePropertyRequest.newBuilder()
        .setUserHandle("userHandle")
        .setCreated(Timestamp.newBuilder().build())
        .setOrigin(Origin.UNSPECIFIED)
        .setOriginValue(123)
        .setPropertyState(PropertyState.PROPERTYSTATE_UNSPECIFIED)
        .setPropertyStateValue(456)
        .setPropertyType(PropertyType.PROPERTYTYPE_UNSPECIFIED)
        .build();

    eventStoreBlockingStub.writeProperty(request);
    channel.shutdown();
  }

}

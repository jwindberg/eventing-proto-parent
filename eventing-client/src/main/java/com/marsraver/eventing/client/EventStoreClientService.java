package com.marsraver.eventing.client;

import com.avaya.adf.model.error.WriteErrorRequest;
import com.avaya.adf.model.interaction.WriteInteractionContextRequest;
import com.avaya.adf.model.interaction.WriteInteractionRequest;
import com.avaya.adf.model.interaction.WriteMediaMessageRequest;
import com.avaya.adf.model.interaction.WriteMediaRequest;
import com.avaya.adf.model.property.WritePropertyRequest;
import com.avaya.adf.model.request.Request;
import com.avaya.adf.model.resource.WriteResourceRequest;
import com.avaya.adf.model.user.WriteUserRequest;
import com.avaya.adf.service.gateway.EventStoreGrpc;
import io.grpc.Channel;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventStoreClientService {

    @GrpcClient("EventStore")
    private EventStoreGrpc.EventStoreBlockingStub eventStoreClient;
//    private EventStoreGrpc.EventStoreFutureStub eventStoreClient;


    public Channel getChannel() {
        return eventStoreClient.getChannel();
    }

    public void writeErrorSync(WriteErrorRequest request) {
        eventStoreClient.writeError(request);
    }


    public void writeInteractionSync(WriteInteractionRequest request) {
        log.info("request: " + request);
        eventStoreClient.writeInteraction(request);
    }

    public void writeInteractionContext(WriteInteractionContextRequest request) {
        log.info("request: " + request);
        eventStoreClient.writeInteractionContext(request);
    }

    public void writeMedia(WriteMediaRequest request) {
        log.info("request: " + request);
        eventStoreClient.writeMedia(request);
    }

    public void writeMediaMessage(WriteMediaMessageRequest request) {
        log.info("request: " + request);
        eventStoreClient.writeMediaMessage(request);
    }

    public void writeProperty(WritePropertyRequest request) {
        log.info("request: " + request);
        eventStoreClient.writeProperty(request);
    }

    public void writeRequest(Request request) {
        log.info("request: " + request);
        eventStoreClient.writeRequest(request);
    }

    public void writeResource(WriteResourceRequest request) {
        log.info("request: " + request);
        eventStoreClient.writeResource(request);
    }

    public void writeUser(WriteUserRequest request) {
        log.info("request: " + request);
        eventStoreClient.writeUser(request);
    }

}

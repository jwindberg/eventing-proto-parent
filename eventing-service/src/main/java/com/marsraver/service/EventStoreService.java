package com.marsraver.service;

import com.avaya.adf.model.error.WriteErrorRequest;
import com.avaya.adf.model.interaction.WriteInteractionContextRequest;
import com.avaya.adf.model.interaction.WriteInteractionRequest;
import com.avaya.adf.model.interaction.WriteMediaMessageRequest;
import com.avaya.adf.model.interaction.WriteMediaRequest;
import com.avaya.adf.model.property.WritePropertyRequest;
import com.avaya.adf.model.request.Request;
import com.avaya.adf.model.resource.WriteResourceRequest;
import com.avaya.adf.model.user.WriteUserRequest;
import com.avaya.adf.service.gateway.Ack;
import com.avaya.adf.service.gateway.EventStoreGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class EventStoreService extends EventStoreGrpc.EventStoreImplBase {


  @Override
  public void writeError(WriteErrorRequest request, StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }

  @Override
  public void writeInteraction(WriteInteractionRequest request,
      StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }

  @Override
  public void writeInteractionContext(WriteInteractionContextRequest request,
      StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }

  @Override
  public void writeMedia(WriteMediaRequest request, StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }

  @Override
  public void writeMediaMessage(WriteMediaMessageRequest request,
      StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }

  @Override
  public void writeProperty(WritePropertyRequest request, StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }


  @Override
  public void writeRequest(Request request, StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }

  @Override
  public void writeResource(WriteResourceRequest request, StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }

  @Override
  public void writeUser(WriteUserRequest request, StreamObserver<Ack> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(ack());
    responseObserver.onCompleted();
  }

  private Ack ack() {
    return Ack.getDefaultInstance();
  }

}

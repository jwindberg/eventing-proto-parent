package com.marsraver.service;

import com.avaya.adf.service.gateway.QueryAPIGrpc;
import com.avaya.adf.service.gateway.UserResource;
import com.avaya.adf.service.gateway.UserResourceList;
import com.avaya.adf.service.gateway.UserResourceRequest;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class QueryApiService extends QueryAPIGrpc.QueryAPIImplBase {

  @Override
  public void getUserResourceList(UserResourceRequest request,
      StreamObserver<UserResourceList> responseObserver) {
    log.info("request: " + request);
    responseObserver.onNext(userResourceList());
    responseObserver.onCompleted();
  }

  private UserResourceList userResourceList() {
    return UserResourceList.newBuilder()
        .setUserHandle("userHandle")
        .addUserResources(UserResource.newBuilder()
            .setLoginId("jbwindberg")
            .build())
        .build();
  }
}

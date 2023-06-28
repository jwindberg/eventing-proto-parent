package com.marsraver.eventing.client;

import com.avaya.adf.service.gateway.QueryAPIGrpc;
import com.avaya.adf.service.gateway.UserResourceList;
import com.avaya.adf.service.gateway.UserResourceRequest;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QueryApiClientService {

    @GrpcClient("QueryApi")
    private QueryAPIGrpc.QueryAPIBlockingStub queryApiClient;

    public UserResourceList getUserResourceList(UserResourceRequest request) {
        return queryApiClient.getUserResourceList(request);
    }

}

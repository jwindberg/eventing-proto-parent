package com.marsraver.eventing.client;

import com.avaya.adf.service.gateway.UserResourceList;
import com.avaya.adf.service.gateway.UserResourceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("queryApi")
public class QueryApiController {

  private QueryApiClientService queryApiClientService;

  @GetMapping
  public ResponseEntity<?> getUserResourceList() {
    UserResourceList userResourceList = queryApiClientService.getUserResourceList(
        userResourceRequest());
    log.info("getUserResourceList: " + userResourceList);
    return ResponseEntity.ok(userResourceResponse(userResourceList));
  }

  private UserResourceRequest userResourceRequest() {
    return UserResourceRequest.newBuilder()
        .setUserHandle("userHandle")
        .setTenantId("tenantId")
        .setTraceId("traceId")
        .build();
  }

  private UserResourceResponse userResourceResponse(UserResourceList userResourceList) {
    return UserResourceResponse.builder()
        .userResources(UserResources.builder()
            .loginId(userResourceList.getUserHandle())
            .build())
        .build();
  }

  @Value
  @Builder
  private static class UserResourceResponse {

    private UserResources userResources;
  }

  @Value
  @Builder
  private static class UserResources {

    private String loginId;
  }

}

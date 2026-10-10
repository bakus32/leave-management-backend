package ci.yobak.leavemanagement.application.dto;

import java.time.Instant;


public record ProfileResponseDto(
     Long id,
     String code,
     String name,
     Instant createdDate,
     Instant modifiedDate){ }

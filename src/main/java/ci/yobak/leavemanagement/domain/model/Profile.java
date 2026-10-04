package ci.yobak.leavemanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    private Long id;
    private String code;
    private String name;
    private Instant createdDate;
    private Instant modifiedDate;
}

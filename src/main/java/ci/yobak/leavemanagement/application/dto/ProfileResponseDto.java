package ci.yobak.leavemanagement.application.dto;

import java.time.Instant;

public class ProfileResponseDto {
    private Long id;
    private String code;
    private String name;
    private Instant createdDate;
    private Instant modifiedDate;

    public ProfileResponseDto() {
    }

    public ProfileResponseDto(Long id, String code, String name, Instant createdDate, Instant modifiedDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

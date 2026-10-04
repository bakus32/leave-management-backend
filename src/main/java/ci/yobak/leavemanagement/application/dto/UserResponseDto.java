package ci.yobak.leavemanagement.application.dto;

import java.time.Instant;

public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Long profileId;
    private Instant createdDate;
    private Instant modifiedDate;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String name, String email, String phone,
                           Long profileId, Instant createdDate, Instant modifiedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profileId = profileId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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

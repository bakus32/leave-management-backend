package ci.yobak.leavemanagement.domain.in;

import ci.yobak.leavemanagement.domain.model.User;

import java.util.List;

public interface UserUseCase {
    User create(User user);

    User getById(Long id);

    List<User> getAll();

    User update(Long id, User user);

    void delete(Long id);
}

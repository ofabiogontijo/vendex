package com.vendex.api.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.vendex.api.core.VendexBeanUtils.copyNonNullProperties;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserCommand {

	private final UserRepository repository;

	private final UserQuery query;

	public User create(User user) {
		return repository.save(user);
	}

	public User update(UUID id, User user) {
		User userPersisted = query.findById(id);
		copyNonNullProperties(User.of(user), userPersisted, "id");
		return userPersisted;
	}

}

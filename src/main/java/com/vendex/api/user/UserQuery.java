package com.vendex.api.user;

import com.vendex.api.core.VendexMessageSource;
import com.vendex.api.core.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserQuery {

	private final UserRepository repository;

	private final VendexMessageSource messageSource;

	public Page<User> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public User findById(UUID id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("user.not.found", id), true));
	}

}

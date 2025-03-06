package com.vendex.api.user.web;

import com.vendex.api.user.User;
import com.vendex.api.user.UserCommand;
import com.vendex.api.user.UserQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
class UserRestService {

	private final UserCommand command;

	private final UserQuery query;

	@PostMapping
	@ResponseStatus(CREATED)
	User create(@Valid @RequestBody User user) {
		return command.create(User.of(user));
	}

	@GetMapping
	@ResponseStatus(OK)
	Page<User> findAll(@PageableDefault(direction = DESC, value = 20) final Pageable pageable) {
		return query.findAll(pageable);
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	User findById(@PathVariable UUID id) {
		return query.findById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(OK)
	User update(@PathVariable UUID id, @Valid @RequestBody User user) {
		return command.update(id, user);
	}

}

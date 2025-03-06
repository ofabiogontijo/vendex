package com.vendex.api.user.web;

import br.com.six2six.fixturefactory.Fixture;
import com.vendex.api.support.TestSupport;
import com.vendex.api.user.User;
import com.vendex.api.user.UserCommand;
import com.vendex.api.user.UserQuery;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static com.vendex.api.support.FixtureTemplates.VALID;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class UserRestServiceTest extends TestSupport {

	private static final UUID ID = UUID.fromString("05bf7740-72b1-4507-b821-5a91f51b08ab");

	private UserRestService restService;

	@Mock
	private UserCommand command;

	@Mock
	private UserQuery query;

	@Override
	public void init() {
		this.restService = new UserRestService(command, query);
	}

	@Test
	void should_create_user_successfully() {
		// Arrange: Criar um usuário fake usando FixtureFactory
		User user = Fixture.from(User.class).gimme(VALID.name());

		when(command.create(any())).thenReturn(user);

		// Act & Assert
		assertEquals(user, restService.create(user));

		// Verifica a ordem das interações
		InOrder inOrder = inOrder(command);
		inOrder.verify(command).create(any());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_find_all_users_successfully() {
		// Arrange
		User user = Fixture.from(User.class).gimme(VALID.name());
		Page<User> page = new PageImpl<>(singletonList(user));
		Pageable pageable = PageRequest.of(0, 10);

		when(query.findAll(any(Pageable.class))).thenReturn(page);

		// Act & Assert
		assertEquals(page, restService.findAll(pageable));

		// Verifica a ordem das interações
		InOrder inOrder = inOrder(query);
		inOrder.verify(query).findAll(any());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_find_user_by_id_successfully() {
		// Arrange
		User user = Fixture.from(User.class).gimme(VALID.name());
		when(query.findById(ID)).thenReturn(user);

		// Act
		User result = restService.findById(ID);

		// Assert
		assertEquals(user, result);

		// Verifica a ordem das interações
		InOrder inOrder = inOrder(query);
		inOrder.verify(query).findById(ID);
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_update_user_successfully() {
		// Arrange
		User user = Fixture.from(User.class).gimme(VALID.name());

		when(command.update(ID, user)).thenReturn(user);

		// Act
		User result = restService.update(ID, user);

		// Assert
		assertEquals(user, result);

		// Verifica a ordem das interações
		InOrder inOrder = inOrder(command);
		inOrder.verify(command).update(ID, user);
		inOrder.verifyNoMoreInteractions();
	}

}
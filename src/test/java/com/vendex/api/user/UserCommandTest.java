package com.vendex.api.user;

import br.com.six2six.fixturefactory.Fixture;
import com.vendex.api.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.UUID;

import static com.vendex.api.support.FixtureTemplates.VALID;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class UserCommandTest extends TestSupport {

	private static final UUID ID = UUID.fromString("05bf7740-72b1-4507-b821-5a91f51b08ab");

	private UserCommand command;

	@Mock
	private UserRepository repository;

	@Mock
	private UserQuery query;

	@Override
	public void init() {
		this.command = new UserCommand(repository, query);
	}

	@Test
	void should_create_user_successfully() {
		// Arrange: Criar um usuário fake usando FixtureFactory
		User user = Fixture.from(User.class).gimme(VALID.name());

		when(repository.save(any())).thenReturn(user);

		// Act & Assert
		assertEquals(user, command.create(user));

		// Verifica a ordem das interações
		InOrder inOrder = inOrder(repository);
		inOrder.verify(repository).save(any());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_update_user_successfully() {
		// Arrange
		User user = Fixture.from(User.class).gimme(VALID.name());

		when(query.findById(ID)).thenReturn(user);

		// Act & Assert
		assertEquals(user, command.update(ID, user));

		// Verifica a ordem das interações
		InOrder inOrder = inOrder(query, repository);
		inOrder.verify(query).findById(ID);
		inOrder.verifyNoMoreInteractions();
	}

}
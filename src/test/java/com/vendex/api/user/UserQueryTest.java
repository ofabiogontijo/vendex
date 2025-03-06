package com.vendex.api.user;

import br.com.six2six.fixturefactory.Fixture;
import com.vendex.api.core.VendexMessageSource;
import com.vendex.api.core.exception.ResourceNotFoundException;
import com.vendex.api.support.TestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static com.vendex.api.support.FixtureTemplates.VALID;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class UserQueryTest extends TestSupport {

	private static final UUID ID = UUID.fromString("05bf7740-72b1-4507-b821-5a91f51b08ab");

	private UserQuery query;

	@Mock
	private UserRepository repository;

	@Mock
	private VendexMessageSource messageSource;

	@Override
	public void init() {
		this.query = new UserQuery(repository, messageSource);
	}

	@Test
	void should_retrieve_all_users() {
		// Arrange: Criar um usuário fake e um Page<User>
		User user = Fixture.from(User.class).gimme(VALID.name());
		Page<User> page = new PageImpl<>(singletonList(user));
		Pageable pageable = PageRequest.of(0, 10);

		when(repository.findAll(any(Pageable.class))).thenReturn(page);

		// Act
		Page<User> result = query.findAll(pageable);

		// Assert
		assertEquals(page.getContent(), result.getContent());

		// Verifica se o método foi chamado corretamente
		InOrder inOrder = inOrder(repository);
		inOrder.verify(repository).findAll(any(Pageable.class));
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_find_user_by_id() {
		// Arrange
		User user = Fixture.from(User.class).gimme(VALID.name());
		when(repository.findById(ID)).thenReturn(Optional.of(user));

		// Act
		User result = query.findById(ID);

		// Assert
		assertEquals(user, result);

		// Verify
		InOrder inOrder = inOrder(repository);
		inOrder.verify(repository).findById(ID);
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	void should_throw_exception_when_user_not_found_by_id() {
		// Arrange
		when(repository.findById(ID)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(ResourceNotFoundException.class, () -> query.findById(ID));

		// Verify
		InOrder inOrder = inOrder(repository, messageSource);
		inOrder.verify(repository).findById(ID);
	}

}
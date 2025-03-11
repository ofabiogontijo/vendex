package com.vendex.api.order;

import br.com.six2six.fixturefactory.Fixture;
import com.vendex.api.product.Product;
import com.vendex.api.product.ProductQuery;
import com.vendex.api.support.TestSupport;
import com.vendex.api.user.User;
import com.vendex.api.user.UserQuery;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.UUID;

import static com.vendex.api.support.FixtureTemplates.VALID;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class OrderCommandTest extends TestSupport {

	private static final UUID ID = UUID.fromString("05bf7740-72b1-4507-b821-5a91f51b08ab");

	private OrderCommand command;

	@Mock
	private OrderRepository repository;

	@Mock
	private UserQuery userQuery;

	@Mock
	private ProductQuery productQuery;

	@Mock
	private OrderQuery query;

	@Override
	public void init() {
		this.command = new OrderCommand(repository, userQuery, productQuery, query);
	}

	@Test
	void should_create_order_successfully() {
		// Arrange: Criar um usuário fake usando FixtureFactory
		Order order = Fixture.from(Order.class).gimme(VALID.name());
		Product product = Fixture.from(Product.class).gimme(VALID.name());

		when(productQuery.findById(any(UUID.class))).thenReturn(product);
		when(repository.save(any())).thenReturn(order);

		// Act & Assert
		assertEquals(order, command.create(ID, order));

		// Verifica a ordem das interações
		InOrder inOrder = inOrder(repository);
		inOrder.verify(repository).save(any());
		inOrder.verifyNoMoreInteractions();
	}

}
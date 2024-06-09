package com.mycompany.orderAssignmentSystem.repository.postgres;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mycompany.orderAssignmentSystem.enumerations.OrderCategory;
import com.mycompany.orderAssignmentSystem.enumerations.OrderStatus;
import com.mycompany.orderAssignmentSystem.model.CustomerOrder;
import com.mycompany.orderAssignmentSystem.model.Worker;

public class OrderPostgresRepositoryTest {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private OrderDatabaseRepository orderDataRepository;
	private WorkerDatabaseRepository workerDataRepository;
	private static final String PERSISTENCE_UNIT_NAME = "test_myPersistenceUnit";

	@Before
	public void onSetUp() throws IOException {

		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
		orderDataRepository = new OrderDatabaseRepository(entityManager);
		workerDataRepository = new WorkerDatabaseRepository(entityManager);
	}

	@After
	public void onTearDown() throws IOException {
		entityManagerFactory.close();
		entityManager.close();
	}

	@Test
	public void testFindAllWhenDatabaseIsEmpty() {
		assertThat(orderDataRepository.findAll()).isEmpty();

	}

	@Test
	public void testFindAllWhenDatabaseIsNotEmpty() {
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		CustomerOrder order2 = new CustomerOrder("Alic", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		order2 = orderDataRepository.save(order2);

		assertThat(orderDataRepository.findAll()).containsExactly(order1, order2);
	}

	@Test
	public void testSave() {
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		assertThat(orderDataRepository.findAll()).containsExactly(order1);

	}

	@Test
	public void testDelete() {
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		orderDataRepository.delete(order1);
		assertThat(orderDataRepository.findAll()).isEmpty();

	}

	@Test
	public void testFindByIdFound() {
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		assertThat(orderDataRepository.findById(order1.getOrderId())).isEqualTo(order1);
	}

	@Test
	public void testFindByIdNotFound() {
		assertThat(orderDataRepository.findById(1l)).isNull();

	}

	@Test
	public void testFindByCustomerNameFound() {
		String searchText = "Jhon";
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		assertThat(orderDataRepository.findByCustomerName(searchText)).isEqualTo(Arrays.asList(order1));
	}

	@Test
	public void testFindByCustomerNameNotFound() {
		String searchText = "Jhon";
		assertThat(orderDataRepository.findByCustomerName(searchText)).isEmpty();

	}

	@Test
	public void testFindByCustomerPhoneNumberFound() {
		String searchText = "3401372678";
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		assertThat(orderDataRepository.findByCustomerPhoneNumber(searchText)).isEqualTo(Arrays.asList(order1));
	}

	@Test
	public void testFindByCustomerPhoneNumberNotFound() {
		String searchText = "3401372678";

		assertThat(orderDataRepository.findByCustomerPhoneNumber(searchText)).isEmpty();

	}

	@Test
	public void testFindByOrderDateFound() {
		String searchText = "12-12-2024";
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		assertThat(orderDataRepository.findByDate(searchText)).isEqualTo(Arrays.asList(order1));
	}

	@Test
	public void testFindByOrderDateNotFound() {
		String searchText = "12-12-2024";

		assertThat(orderDataRepository.findByDate(searchText)).isEmpty();

	}

	@Test
	public void testFindByOrderOrderCategoryFound() {
		OrderCategory searchText = OrderCategory.PLUMBER;
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		assertThat(orderDataRepository.findByOrderCategory(searchText)).isEqualTo(Arrays.asList(order1));
	}

	@Test
	public void testFindByOrderCategoryNotFound() {
		OrderCategory searchText = OrderCategory.PLUMBER;
		assertThat(orderDataRepository.findByOrderCategory(searchText)).isEmpty();

	}

	@Test
	public void testFindByOrderOrderStatusFound() {
		OrderStatus searchText = OrderStatus.PENDING;
		Worker worker1 = new Worker("Bob", "3401372678", OrderCategory.PLUMBER);
		worker1 = workerDataRepository.save(worker1);
		CustomerOrder order1 = new CustomerOrder("Jhon", "Piazza Luigi Dalla", "3401372678", "12-12-2024",
				"No description", OrderCategory.PLUMBER, OrderStatus.PENDING, worker1);
		order1 = orderDataRepository.save(order1);
		assertThat(orderDataRepository.findByOrderStatus(searchText)).isEqualTo(Arrays.asList(order1));
	}

	@Test
	public void testFindByOrderStatusNotFound() {
		OrderStatus searchText = OrderStatus.PENDING;

		assertThat(orderDataRepository.findByOrderStatus(searchText)).isEmpty();

	}

}
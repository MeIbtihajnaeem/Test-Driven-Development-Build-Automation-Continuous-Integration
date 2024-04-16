package com.mycompany.orderAssignmentSystem.model;

import java.util.List;

import com.mycompany.orderAssignmentSystem.enumerations.OrderCategory;

public class Worker {
	private Long workerId;
	private String workerName;
	private String workerPhoneNumber;
	private OrderCategory workerCategory;
	private List<CustomerOrder> orders;

	public Worker() {
	};

	public Worker(String workerName, String workerPhoneNumber, OrderCategory workerCategory,
			List<CustomerOrder> orders) {
		super();
		this.workerName = workerName;
		this.workerPhoneNumber = workerPhoneNumber;
		this.workerCategory = workerCategory;
		this.orders = orders;
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerPhoneNumber() {
		return workerPhoneNumber;
	}

	public void setWorkerPhoneNumber(String workerPhoneNumber) {
		this.workerPhoneNumber = workerPhoneNumber;
	}

	public OrderCategory getWorkerCategory() {
		return workerCategory;
	}

	public void setWorkerCategory(OrderCategory workerCategory) {
		this.workerCategory = workerCategory;
	}

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}

	public String displayWorker() {
		return String.format("Worker ID: %d\nWorker Name: %s\nWorker Phone Number: %s\nWorker Category: %s", workerId,
				workerName, workerPhoneNumber, workerCategory);
	}

}
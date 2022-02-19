package hu.oktatas.transport.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Milestone {

	@Id
	@GeneratedValue	
	private Long id;
	
	@ManyToOne
	private Address address;
	private LocalDate plannedTime;

	public Milestone(Long id, Address address, LocalDate plannedTime) {
		super();
		this.id = id;
		this.address = address;
		this.plannedTime = plannedTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDate getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDate plannedTime) {
		this.plannedTime = plannedTime;
	}
}

package hu.oktatas.transport.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Milestone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Address address;
	private LocalDateTime plannedTime;

	public Milestone(Long id, Address address, LocalDateTime plannedTime) {
		super();
		this.id = id;
		this.address = address;
		this.plannedTime = plannedTime;
	}

	public Milestone() {
	}

	public Milestone(Long id, /* Optional<Address> */ Address address, String plannedTime) {
		this.id = id;
		this.address = address;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(plannedTime, formatter);
		this.plannedTime = dateTime;
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

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}


}

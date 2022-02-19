package hu.oktatas.transport.dto;

import java.time.LocalDate;

public class MilestoneDto {

	private Long id;
	private AddressDto address;
	private LocalDate plannedTime;

	public MilestoneDto(Long id, AddressDto address, LocalDate plannedTime) {
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

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public LocalDate getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDate plannedTime) {
		this.plannedTime = plannedTime;
	}
}

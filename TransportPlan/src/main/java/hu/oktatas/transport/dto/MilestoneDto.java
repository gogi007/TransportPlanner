package hu.oktatas.transport.dto;

import java.time.LocalDateTime;

public class MilestoneDto {

	private Long id;
	private AddressDto address;
	private LocalDateTime plannedTime;

	public MilestoneDto(Long id, AddressDto address, LocalDateTime plannedTime) {
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

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}
}

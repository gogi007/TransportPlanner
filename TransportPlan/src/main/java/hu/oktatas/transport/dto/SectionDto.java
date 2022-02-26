package hu.oktatas.transport.dto;

import hu.oktatas.transport.model.TransportPlan;

public class SectionDto {
	private Long id;
	private MilestoneDto fromMilestone;
	private MilestoneDto toMilestone;
	private int number;
	private TransportPlan transportPlan;

	public SectionDto(Long id, MilestoneDto fromMilestone, MilestoneDto toMilestone, int number,
			TransportPlan transportPlan) {
		super();
		this.id = id;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.number = number;
		this.setTransportPlan(transportPlan);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MilestoneDto getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(MilestoneDto fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public MilestoneDto getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(MilestoneDto toMilestone) {
		this.toMilestone = toMilestone;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public TransportPlan getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlan transportPlan) {
		this.transportPlan = transportPlan;
	}

}

package hu.oktatas.transport.dto;

import java.util.ArrayList;
import java.util.List;

public class TransportPlanDto {

	private Long id;
	private int plannedIncome;
	List<SectionDto> sections = new ArrayList<>();

	public TransportPlanDto(Long id, int plannedIncome, List<SectionDto> sections) {
		super();
		this.id = id;
		this.plannedIncome = plannedIncome;
		this.sections = sections;
	}

	public List<SectionDto> getSections() {
		return sections;
	}

	public void setSections(List<SectionDto> sections) {
		this.sections = sections;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPlannedIncome() {
		return plannedIncome;
	}

	public void setPlannedIncome(int plannedIncome) {
		this.plannedIncome = plannedIncome;
	}

}

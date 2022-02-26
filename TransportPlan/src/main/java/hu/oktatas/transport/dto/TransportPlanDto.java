package hu.oktatas.transport.dto;

import java.util.ArrayList;
import java.util.List;

import hu.oktatas.transport.model.Section;

public class TransportPlanDto {

	private Long id;
	private int plannedIncome;
	private List<Section> sections = new ArrayList<>();

//	public TransportPlanDto(long id, int plannedIncome) {
//		this.id = id;
//		this.plannedIncome = plannedIncome;
//	}
//
//	public TransportPlanDto(Long id, int plannedIncome, List<Section> sections) {
//		this.id = id;
//		this.plannedIncome = plannedIncome;
//		this.sections = sections;
//	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
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

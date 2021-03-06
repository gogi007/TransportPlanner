package hu.oktatas.transport.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransportPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int plannedIncome;

	@OneToMany(mappedBy = "transportPlan")
	private List<Section> sections;

	public TransportPlan() {

	};
	public TransportPlan(Long id, int plannedIncome) {
		super();
		this.id = id;
		this.plannedIncome = plannedIncome;		
	}
	
	public TransportPlan(Long id, int plannedIncome, List<Section> sections) {
		super();
		this.id = id;
		this.plannedIncome = plannedIncome;
		this.sections = sections;
	}

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

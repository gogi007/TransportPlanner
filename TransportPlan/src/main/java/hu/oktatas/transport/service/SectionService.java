package hu.oktatas.transport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.oktatas.transport.model.Milestone;
import hu.oktatas.transport.model.Section;
import hu.oktatas.transport.model.TransportPlan;
import hu.oktatas.transport.repository.SectionRepository;

@Service
public class SectionService {

	@Autowired
	SectionRepository sectionRepository;

	public Section findByPlanIdMileStoneId(long transportPlanId, long milestoneId) {
		Section example = new  Section();
		TransportPlan exampleTransportPlan= new TransportPlan();
		exampleTransportPlan.setId(transportPlanId);
		
		Milestone exampleMilestone = new Milestone();
		exampleMilestone.setId(milestoneId);
		example.setTransportPlan(exampleTransportPlan);
		example.setFromMilestone(exampleMilestone);
		example.setToMilestone(exampleMilestone);
		List<Section> results = findSectionByExample(example);			
		if (results.isEmpty()) {			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return results.get(0);
	}

	public Section findNextService(long transportPlanId, int PlaceInTransportPlan) {
		Section example = new  Section();
		TransportPlan exampleTransportPlan = new TransportPlan();
		exampleTransportPlan.setId(transportPlanId);
		example.setNumber(PlaceInTransportPlan);
		example.setTransportPlan(exampleTransportPlan);
		return findSectionByExample(example).get(0);
	}
	
	public List<Section> findSectionByExample(Section example){		
		long id=0;
		if (example.getId() != null)
			id = example.getId();
		Milestone fromMilestone = example.getFromMilestone();
		Milestone toMilestone = example.getToMilestone();
		int number = example.getNumber();
		TransportPlan transportPlan = example.getTransportPlan();		
		long fromMilestoneId = fromMilestone.getId();
		long toMilestoneId = toMilestone.getId();
		long transportPlanId = transportPlan.getId();
		
		Specification<Section> spec = Specification.where(null);
		if (id >0)
			spec = spec.and(SectionSpecifications.hasId(id));
		if (number>0)
			spec = spec.and(SectionSpecifications.hasNumber(number));
		if ((fromMilestoneId>0) && (toMilestoneId>0))  
			spec = spec.and(SectionSpecifications.hasFromMilestoneIdOrToMileStoneId(fromMilestoneId,toMilestoneId));		
		else if (fromMilestoneId>0)  
			spec = spec.and(SectionSpecifications.hasFromMilestoneId(fromMilestoneId));
		else if (toMilestoneId>0)		
			spec = spec.and(SectionSpecifications.hasToMileStoneId(toMilestoneId));
		if (transportPlanId>0)
			spec = spec.and(SectionSpecifications.hasTransportPlanId(transportPlanId));
		return sectionRepository.findAll(spec);
		
	}
	
}

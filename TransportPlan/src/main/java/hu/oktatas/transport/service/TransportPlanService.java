package hu.oktatas.transport.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.oktatas.transport.config.TransportPlanConfigProperties;
import hu.oktatas.transport.model.Milestone;
import hu.oktatas.transport.model.Section;
import hu.oktatas.transport.model.TransportPlan;
import hu.oktatas.transport.repository.MilestoneRepository;
import hu.oktatas.transport.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	private TransportPlanRepository transportPlanRepository;

	@Autowired
	private MilestoneRepository milestoneRepository;

	@Autowired
	private SectionService sectionService;

	// @Autowired
	// private TransportPlanService transportPlanService;

	@Autowired
	TransportPlanConfigProperties config;

	public Optional<TransportPlan> findById(long id) {
		return transportPlanRepository.findById(id);
	}

	@Transactional
	public void delayMilestone(long id, long milestoneId, Long delayInMin) {
		TransportPlan transportPlan = transportPlanRepository.findById(id).get();
		Milestone milestone = milestoneRepository.findById(milestoneId).get();

		if (transportPlan == null || milestone == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		Section section = sectionService.findByPlanIdMileStoneId(id, milestoneId);

		if (section == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delayInMin));

		milestoneRepository.save(milestone);

		if ((section.getFromMilestone() != null) && (section.getFromMilestone().getId() == milestoneId)) {
			toMilestonePlannedTimeUpd(delayInMin, section);
		} else {
			nextSectionMilestonePlannedTimeSliding(id, delayInMin, section);
		}

		transportPlan.setPlannedIncome(newPlannedIncomeWithLate(transportPlan, delayInMin));
		System.out.println(transportPlan.getPlannedIncome()); 
		transportPlanRepository.save(transportPlan);
	}

	private void toMilestonePlannedTimeUpd(Long delayInMin, Section section) {
		Milestone toMileStone = milestoneRepository.findById(section.getToMilestone().getId()).get();
		toMileStone.setPlannedTime(toMileStone.getPlannedTime().plusMinutes(delayInMin));
		milestoneRepository.save(toMileStone);
	}

	private void nextSectionMilestonePlannedTimeSliding(long transportPlanId, Long delayInMin, Section LastSection) {
		Section nextSection = sectionService.findNextService(transportPlanId, LastSection.getNumber());
		Milestone nextStartMilestone = milestoneRepository.findById(nextSection.getFromMilestone().getId()).get();
		nextStartMilestone.setPlannedTime(nextStartMilestone.getPlannedTime().plusMinutes(delayInMin));
		milestoneRepository.save(nextStartMilestone);
	}

	public Integer newPlannedIncomeWithLate(TransportPlan transportPlan, long delayInMin) {
		return (int) (transportPlan.getPlannedIncome() * (decreasePercentForLate(delayInMin)));
	}

	public double decreasePercentForLate(long delayInMin) {
		int percent = 0;
		if (delayInMin >= 120) {
			percent = config.getPercentMins().getPenalty120Min();
		} else if (delayInMin >= 60) {
			percent = config.getPercentMins().getPenalty60Min();
		} else if (delayInMin >= 30) {
			percent = config.getPercentMins().getPenalty30Min();
		}
		;
		System.out.println(config.getPercentMins().getPenalty30Min());
		return (100 - percent) / 100;
	}

}

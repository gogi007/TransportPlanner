package hu.oktatas.transport.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.oktatas.transport.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

	@Autowired
	private TransportPlanService transportPlanService;

//	public TransportPlanController(TransportPlanService transportPlanService) {
//		super();
//		this.transportPlanService = transportPlanService;
//	}

	@PostMapping(value = "/{id}/delay", params = { "milestoneId", "delayInMin" })
	public void delayMilestone(@PathVariable long id, @RequestParam long milestoneId, @RequestParam Long delayInMin) {
		// try {
		transportPlanService.delayMilestone(id, milestoneId, delayInMin);
		// } catch (NoSuchElementException e) {
		// throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		// }
	}

}

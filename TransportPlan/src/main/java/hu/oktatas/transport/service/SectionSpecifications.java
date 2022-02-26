package hu.oktatas.transport.service;

import org.springframework.data.jpa.domain.Specification;

import hu.oktatas.transport.model.Milestone_;
import hu.oktatas.transport.model.Section;
import hu.oktatas.transport.model.Section_;
import hu.oktatas.transport.model.TransportPlan_;

public class SectionSpecifications {

	public static Specification<Section> hasId(long id) {
		return (root, cq, cb) -> cb.equal(root.get(Section_.id), id);
	}

	public static Specification<Section> hasNumber(int number) {
		return (root, cq, cb) -> cb.equal(root.get(Section_.number), number);
	}

	public static Specification<Section> hasFromMilestoneId(long fromMilestoneId) {
		return (root, cq, cb) -> cb.equal(root.get(Section_.fromMilestone).get(Milestone_.id), fromMilestoneId);
	}

	public static Specification<Section> hasToMileStoneId(long toMilestoneId) {
		return (root, cq, cb) -> cb.equal(root.get(Section_.toMilestone).get(Milestone_.id), toMilestoneId);
	}

	public static Specification<Section> hasTransportPlanId(long transportPlanId) {
		return (root, cq, cb) -> cb.equal(root.get(Section_.transportPlan).get(TransportPlan_.id), transportPlanId);

	};

	public static Specification<Section> hasFromMilestoneIdOrToMileStoneId(long fromMilestoneId, long toMilestoneId) {
		return (root, cq, cb) -> cb.or(cb.equal(root.get(Section_.fromMilestone).get(Milestone_.id), fromMilestoneId),
				cb.equal(root.get(Section_.toMilestone).get(Milestone_.id), toMilestoneId));
	}

}

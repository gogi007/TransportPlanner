package hu.oktatas.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.oktatas.transport.model.TransportPlan;

public interface TransportPlanRepository
		extends JpaRepository<TransportPlan, Long>, JpaSpecificationExecutor<TransportPlan> {


}

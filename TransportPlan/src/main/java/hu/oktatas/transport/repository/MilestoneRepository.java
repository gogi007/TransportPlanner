package hu.oktatas.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.oktatas.transport.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>{

}

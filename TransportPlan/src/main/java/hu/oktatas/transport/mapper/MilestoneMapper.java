package hu.oktatas.transport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.oktatas.transport.dto.MilestoneDto;
import hu.oktatas.transport.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {
	List<MilestoneDto> MilestoneToDtos(List<Milestone> milestones);

	MilestoneDto milestoneToDto(Milestone milestone);

	Milestone dtoToMilestone(MilestoneDto milestoneDto);
}

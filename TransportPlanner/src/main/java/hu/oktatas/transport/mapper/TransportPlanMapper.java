package hu.oktatas.transport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.oktatas.transport.dto.TransportPlanDto;
import hu.oktatas.transport.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {
	List<TransportPlanDto> TransportPlanToDtos(List<TransportPlan> transportPlans);

	TransportPlanDto transportPlanToDto(TransportPlan transportPlan);

	TransportPlan dtoToTransportPlan(TransportPlanDto transportPlanDto);
}

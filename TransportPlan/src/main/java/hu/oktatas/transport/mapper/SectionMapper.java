package hu.oktatas.transport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.oktatas.transport.dto.SectionDto;
import hu.oktatas.transport.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {
	SectionDto sectionToDto(Section section);

	Section dtoToSection(SectionDto sectionDto);

	List<SectionDto> SectionsToDtos(List<Section> sections);


}

package hu.oktatas.transport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.oktatas.transport.dto.SectionDto;
import hu.oktatas.transport.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {
	List<SectionDto> SectionToDtos(List<Section> sections);

	SectionDto sectionToDto(Section section);

	Section dtoToSection(SectionDto sectionDto);
}

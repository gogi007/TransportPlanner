package hu.oktatas.transport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.oktatas.transport.dto.AddressDto;
import hu.oktatas.transport.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	List<AddressDto> AddressToDtos(List<Address> addresses);

	AddressDto addressToDto(Address address);

	Address dtoToAddress(AddressDto addressDto);
}
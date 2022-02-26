package hu.oktatas.transport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.oktatas.transport.dto.AddressDto;
import hu.oktatas.transport.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	AddressDto addressToDto(Address address);

	Address dtoToAddress(AddressDto addressDto);

	List<AddressDto> AddressesToDtos(List<Address> addresses);

	List<AddressDto> addressesToDtos(List<Address> findAddressesByExample);

	//List<AddressDto> addressToDtos(List<Address> addresses);
}

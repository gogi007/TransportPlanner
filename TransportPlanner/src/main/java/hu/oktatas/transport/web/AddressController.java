package hu.oktatas.transport.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.oktatas.transport.dto.AddressDto;
import hu.oktatas.transport.mapper.AddressMapper;
import hu.oktatas.transport.model.Address;
import hu.oktatas.transport.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	private AddressMapper addressMapper;
	private AddressService addressService;
	//private AddressRepository addressRepository;

	@GetMapping("/{id}")
	public AddressDto getById(@PathVariable long id) {
		Address address = findByIdOrThrow(id);
		return addressMapper.addressToDto(address);
	}

	public AddressController(AddressMapper addressMapper, AddressService addressService) {
		super();
		this.addressMapper = addressMapper;
		this.addressService = addressService;
	}

	private Address findByIdOrThrow(long id) {
		return addressService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public AddressDto createAddress(@RequestBody AddressDto addressDto) {
		return addressMapper.addressToDto(addressService.save(addressMapper.dtoToAddress(addressDto)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressDto> modifyAddress(@PathVariable long id, @RequestBody @Valid AddressDto addressDto) {
		addressDto.setId(id);
		Address updatedAddress = addressService.update(addressMapper.dtoToAddress(addressDto));
		if (updatedAddress == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(addressMapper.addressToDto(updatedAddress));
		}
	}

	@GetMapping
	public List<AddressDto> getAll(@RequestParam(required = false) Boolean full) {

		List<Address> addresses = addressService.findAll();

		return addressMapper.addressesToDtos(addresses);

	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable long id) {
		addressService.delete(id);
	}
/*
	@PostMapping("/search")
	public List<AddressDto> findByExample(@RequestBody AddressDto example) {
		return addressMapper
				.addressesToDtos(addressService.findAddressesByExample(addressMapper.dtoToAddress(example)));
	}*/
}

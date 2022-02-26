package hu.oktatas.transport.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.oktatas.transport.dto.AddressDto;
import hu.oktatas.transport.mapper.AddressMapper;
import hu.oktatas.transport.model.Address;
import hu.oktatas.transport.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private AddressService addressService;

	/*public AddressController(AddressMapper addressMapper, AddressService addressService) {
		super();
		this.addressMapper = addressMapper;
		this.addressService = addressService;
	}*/

	@PostMapping
	public AddressDto createAddress(@RequestBody AddressDto addressDto) {
		return addressMapper.addressToDto(addressService.save(addressMapper.dtoToAddress(addressDto)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressDto> modifyAddress(@PathVariable long id, @RequestBody AddressDto addressDto) {
		if (addressDto.getId() != id)
			return ResponseEntity.badRequest().build();
		addressDto.setId(id);
		Address updatedAddress = addressService.update(addressMapper.dtoToAddress(addressDto));
		if (updatedAddress == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(addressMapper.addressToDto(updatedAddress));
		}
	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable long id) {
		addressService.delete(id);
	}

	@GetMapping("/{id}")
	public AddressDto getById(@PathVariable long id) {
		Address address = findByIdOrThrow(id);
		return addressMapper.addressToDto(address);
	}

	private Address findByIdOrThrow(long id) {
		return addressService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public List<AddressDto> getAll() {

		List<Address> addresses = addressService.findAll();

		return addressMapper.AddressesToDtos(addresses);

	}

	@PostMapping(value = "/search")
	public List<AddressDto> findByExample(@RequestBody AddressDto example, Pageable pageable,
			final HttpServletResponse response) {
		Page<Address> page = addressService.findAddressesByExample(example, pageable);
		response.setHeader("Count", String.valueOf(page.getTotalElements())); // ("Count", page.getTotalElements());
		return addressMapper.addressesToDtos(page.getContent());
	}

}

package hu.oktatas.transport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hu.oktatas.transport.dto.AddressDto;
import hu.oktatas.transport.model.Address;
import hu.oktatas.transport.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Transactional
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Transactional
	public Address update(Address company) {
		if (!addressRepository.existsById(company.getId()))
			return null;
		return addressRepository.save(company);
	}

	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	public Optional<Address> findById(long id) {
		return addressRepository.findById(id);
	}

	@Transactional
	public void delete(long id) {
		if (addressRepository.existsById(id))
			addressRepository.deleteById(id);
	}

	public Page<Address> findAddressesByExample(AddressDto example, Pageable pageable) {
		String addressIso = example.getIsoCode();
		String addressCity = example.getCity();
		String addressPostCode = example.getPostCode();
		String addressStreet = example.getStreet();

		Specification<Address> spec = Specification.where(null);

		if (StringUtils.hasText(addressIso))
			spec = spec.and(AddressSpecifications.hasExactAddressIso(addressIso));
		if (StringUtils.hasText(addressCity))
			spec = spec.and(AddressSpecifications.hasAddressCity(addressCity));
		if (StringUtils.hasText(addressPostCode))
			spec = spec.and(AddressSpecifications.hasExactAddressPostCode(addressPostCode));
		if (StringUtils.hasText(addressStreet))
			spec = spec.and(AddressSpecifications.hasAddressStreet(addressStreet));

		return addressRepository.findAll(spec, pageable);
	}
}

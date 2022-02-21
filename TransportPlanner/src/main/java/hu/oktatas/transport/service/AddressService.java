package hu.oktatas.transport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		addressRepository.deleteById(id);
	}

	public List<Address> findAddressesByExample(Address dtoToAddress) {
		// TODO Auto-generated method stub
		return null;
	}
}

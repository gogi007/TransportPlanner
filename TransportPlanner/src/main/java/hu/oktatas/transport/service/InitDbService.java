package hu.oktatas.transport.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.oktatas.transport.repository.AddressRepository;

@Service
public class InitDbService {

	@Autowired
	AddressRepository addressRepository;
	
	@Transactional
	public void initDb() {
	
	}
}

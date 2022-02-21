package hu.oktatas.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.oktatas.transport.model.Address;

public interface AddressRepository  extends JpaRepository<Address, Long>{

}

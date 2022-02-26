package hu.oktatas.transport.service;

import org.springframework.data.jpa.domain.Specification;

import hu.oktatas.transport.model.Address;
import hu.oktatas.transport.model.Address_;

public class AddressSpecifications {

	public static Specification<Address> hasExactAddressIso(String addressIso) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.isoCode), addressIso);
	}

	public static Specification<Address> hasAddressCity(String addressCity) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)), (addressCity + "%").toLowerCase());
	}

	public static Specification<Address> hasExactAddressPostCode(String addressPostCode) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.postCode), addressPostCode);
	}

	public static Specification<Address> hasAddressStreet(String addressStreet) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)), (addressStreet + "%").toLowerCase());
	}

}

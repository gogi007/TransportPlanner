package hu.oktatas.transport.dto;

import javax.validation.constraints.Size;

public class AddressDto {

	private Long id;
	@Size(min = 2, max = 2)
	private String isoCode;
	private String city;
	private String street;
	private String postCode;
	private String houseNumber;
	private Double latitude;
	private Double longtitude;

	public AddressDto(Long id, @Size(min = 2, max = 2) String isoCode, String city, String street, String postCode,
			String houseNumber, Double latitude, Double longtitude) {
		this.id = id;
		this.isoCode = isoCode;
		this.city = city;
		this.street = street;
		this.postCode = postCode;
		this.houseNumber = houseNumber;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}
}

package br.com.mr.app.model;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "BUS_STOP")
@SequenceGenerator(name = "BUS_STOP_SEQUENCY", sequenceName = "BUS_STOP_SEQUENCY")
public class BusStop implements Serializable {

	private static final long serialVersionUID = -3623991544601983947L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUS_STOP_SEQUENCY")
	private Long id;

	@Column(name = "DESCRIPTION", length = 2048)
	private String description;

	@Column(name = "ADRDESS_LOCATION", length = 2048)
	private String addressLocation;

	@ManyToMany(mappedBy = "busStops")
	@JsonIgnore
	private Set<BusService> busServices;

	@Column(name = "THE_GEOM")
	@Type(type = "org.hibernate.spatial.GeometryType")
	@JsonIgnore
	private Geometry geoLocation;

	public BusStop() {
	}

	public Set<BusService> getBusServices() {
		if (busServices == null) {
			setBusServices(new HashSet<BusService>());
		}
		return busServices;
	}

	public void setBusServices(Set<BusService> busServices) {
		this.busServices = busServices;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Geometry getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(Geometry geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getAddressLocation() {
		return addressLocation;
	}

	public void setAddressLocation(String addressLocation) {
		this.addressLocation = addressLocation;
	}

	@Override
	public String toString() {
		return "BusStop [id=" + id + ", geoLocation=" + geoLocation + "]";
	}
}

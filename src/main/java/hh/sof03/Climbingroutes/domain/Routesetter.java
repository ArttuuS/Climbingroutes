package hh.sof03.Climbingroutes.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Routesetter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long routesetterid;
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "routesetter")
	@JsonIgnoreProperties("routesetter")
	private List<Route> routes;

	// Count of routes set by the Routesetter
	private Long routeCount;

	public Routesetter() {
		super();
	}

	public Routesetter(String name) {
		super();
		this.name = name;
	}

	public Long getRoutesetterid() {
		return routesetterid;
	}

	public void setRoutesetterid(Long routesetterid) {
		this.routesetterid = routesetterid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public Long getRouteCount() {
		return routeCount;
	}

	public void setRouteCount(Long routeCount) {
		this.routeCount = routeCount;
	}

	@Override
	public String toString() {
		return "Routesetter [routesetterid=" + routesetterid + ", name=" + name + "]";
	}

}

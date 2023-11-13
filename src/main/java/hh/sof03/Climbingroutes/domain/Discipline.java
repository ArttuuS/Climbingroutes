package hh.sof03.Climbingroutes.domain;

import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Discipline {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long disciplineid;
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "discipline")
	@JsonIgnoreProperties("discipline")
	private List<Route> routes;

	@Transient
	private Long disciplineCount; // Add this field

	public Discipline(String name) {
		super();
		this.name = name;

	}

	public Discipline() {
		super();
	}

	public Long getDisciplineid() {
		return disciplineid;
	}

	public void setDisciplineid(Long disciplineid) {
		this.disciplineid = disciplineid;
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

	public Long getDisciplineCount() {
		return disciplineCount;
	}

	public void setDisciplineCount(Long disciplineCount) {
		this.disciplineCount = disciplineCount;
	}

	@Override
	public String toString() {
		return "Discipline [disciplineid=" + disciplineid + ", name=" + name + "]";
	}

}

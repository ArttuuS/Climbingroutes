package hh.sof03.Climbingroutes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long routeid;
	private String sector;
	private String grade;
	private String date;

	@ManyToOne
	@JsonIgnoreProperties("routes")
	@JoinColumn(name = "routesetterid")
	private Routesetter routesetter;

	@ManyToOne
	@JsonIgnoreProperties("routesS")
	@JoinColumn(name = "disciplineid")
	private Discipline discipline;

	public Route() {

	}

	public Route(String sector, String grade, String date, Routesetter routesetter, Discipline discipline) {
		this.sector = sector;
		this.grade = grade;
		this.routesetter = routesetter;
		this.date = date;
		this.discipline = discipline;
	}

	public Long getRouteid() {
		return routeid;
	}

	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String crag) {
		this.sector = crag;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Routesetter getRoutesetter() {
		return routesetter;
	}

	public void setRoutesetter(Routesetter routesetter) {
		this.routesetter = routesetter;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	@Override
	public String toString() {
		return "Route [routeid=" + routeid + ", sector=" + sector + ", grade=" + grade + ", date=" + date + "]";
	}

}

package hh.sof03.Climbingroutes.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ClimbingLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long climbinglogid;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "routeid")
	private Route route;

	// Date for climb log
	private LocalDate climbedDate;

	// Transient field to temporarily store the climbing grade
	@Transient
	private String grade;

	public ClimbingLog() {
		super();
	}

	public ClimbingLog(Long climbinglogid, User user, Route route, LocalDate climbedDate, String grade) {
		super();
		this.climbinglogid = climbinglogid;
		this.user = user;
		this.route = route;
		this.climbedDate = climbedDate;
		this.grade = grade;
	}

	public Long getClimbinglogid() {
		return climbinglogid;
	}

	public void setClimbinglogid(Long climbinglogid) {
		this.climbinglogid = climbinglogid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public LocalDate getClimbedDate() {
		return climbedDate;
	}

	public void setClimbedDate(LocalDate climbedDate) {
		this.climbedDate = climbedDate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "ClimbingLog [climbinglogid=" + climbinglogid + ", user=" + user + ", route=" + route + ", climbedDate="
				+ climbedDate + ", grade=" + grade + "]";
	}

}
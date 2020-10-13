package sbr.team;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sbr.me.Coder;


@Entity
@Table(name = "TEAMS")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CodGen")
    @SequenceGenerator(sequenceName = "CODER_SEQ", allocationSize = 1, name = "CodGen")
	
	@Column(name = "TEAM_ID")
	private long id;
	
	@Column(name = "NAME")
	private String teamName;
	
	@ManyToMany
    @JoinTable(name = "TEAM_CODER", joinColumns = @JoinColumn(name = "TEAM_ID"), inverseJoinColumns = @JoinColumn(name = "CODER_ID"))
    @JsonIgnoreProperties({"teams","leadingTeam"})
	private Set<Coder> coders;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "LEADER_ID")
	@JsonIgnoreProperties({"leadingTeam","teams"})
    private Coder leader;

	public Team() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Set<Coder> getCoders() {
		return coders;
	}

	public void setCoders(Set<Coder> coders) {
		this.coders = coders;
	}


	public Coder getLeader() {
		return leader;
	}

	public void setLeader(Coder leader) {
		this.leader = leader;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", coders=" + coders + ", leader=" + leader + "]";
	}

	

	
	
}

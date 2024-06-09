package org.raghav.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "enrollment_id", nullable = false)
	private String enrollmentId;

	private String name;

	/*
	 * cascade = { CascadeType.PERSIST } save guide automatically whenever associated student saves into database
	 * 
	 * fetch = FetchType.LAZY : load always when it's needed (By default eager fetching strategy)
	 * 
	 * cascade = { CascadeType.MERGE } update operation happened automatically guide salary updated to 45000 without
	 * proving CascadeType.MERGE attribute
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinColumn(name = "guide_id")
	private Guide guide;

	public Student(String enrollmentId, String name) {
		this.enrollmentId = enrollmentId;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Student= [id:" + this.id + ", enrollmentId: " + this.enrollmentId + ", name: " + this.name + "]" ;
	}
}

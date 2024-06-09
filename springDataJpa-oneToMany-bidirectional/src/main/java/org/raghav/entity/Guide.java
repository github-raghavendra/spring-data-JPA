package org.raghav.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Guide {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "staff_id", nullable = false)
	private String staffId;
	
	private String name;
	
	private Integer salary;
	

	@OneToMany(mappedBy="guide", cascade= {CascadeType.PERSIST}, orphanRemoval = true )
	private Set<Student> students = new HashSet<Student>();
	

	public Guide(String staffId, String name, Integer salary) {
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}
	
	public void addStudent(Student student) {
		student.setGuide(this);
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
		student.setGuide(null);
	}
	
	public void removeAllStudents() {
		students.forEach(student -> {
			student.setGuide(null);
		});
		students.clear();
	}



	@Override
	public String toString() {
		return "Guide [id=" + id + ", staffId=" + staffId + ", name=" + name + ", salary=" + salary + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(staffId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guide other = (Guide) obj;
		return Objects.equals(staffId, other.staffId);
	}

}

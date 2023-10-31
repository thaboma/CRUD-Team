package za.co.bmw.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle implements Serializable {

		@Id
		@Column(name = "id", nullable = false)
		private Long id;

		@Column(name = "team_id")
		private Long teamId;

		@Column(name = "model", nullable = false)
		private String model;

		@Column(name = "colour",nullable = false)
		private String colour;

		@Column(name = "name")
		private String name;

		@Column(name = "vin_number")
		private String vinNumber;

		@Column(name = "acquiredDate")
		private Date acquiredDate;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(
				name = "team_id", referencedColumnName = "id", updatable = false, insertable = false

		)
		Team team;

	}

package vn.ncuong.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permissions")
public class Permission {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
}

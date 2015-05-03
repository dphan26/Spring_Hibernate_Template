package vn.ncuong.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role_permission")
public class RolePermission {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
}

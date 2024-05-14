package com.air.health.member.entity;

import com.air.health.common.handler.EncodeTypeHandler;
import com.air.health.common.util.Constants;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-24 09:55:13
 */
@Data
@TableName("tb_member")
public class MemberEntity implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(value = "member_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long memberId;
	/**
	 *
	 */
	@TableField("ins_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long insId;
	/**
	 *
	 */
	@TableField("depart_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long departId;
	/**
	 *
	 */
	@TableField("office_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Integer officeId;
	/**
	 *
	 */
	@TableField("member_name")
	private String memberName;
	/**
	 *
	 */
	@TableField(value = "member_password", typeHandler = EncodeTypeHandler.class)
	@JsonIgnore()
	private String password;
	/**
	 *
	 */
	@TableField("member_gender")
	private Integer memberGender;
	/**
	 *
	 */
	@TableField("member_fullName")
	private String fullName;
	/**
	 *
	 */
	@TableField("member_phoneNumber")
	private Long memberPhonenumber;
	/**
	 *
	 */
	@TableField("member_score")
	private Integer memberScore;
	/**
	 *
	 */
	@TableField("member_status")
	private Constants.AccountStatus memberStatus;
	/**
	 *
	 */
	@TableField(value = "member_joinDate", fill = FieldFill.INSERT)
	private LocalDateTime memberJoinDate;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return memberName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

package com.air.health.member.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 *
 *
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-24 09:55:13
 */
@Data
@TableName("tb_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	@TableId(value = "member_id", type = IdType.ASSIGN_ID)
	private Long memberId;
	/**
	 *
	 */
	@TableField("ins_id")
	private Long insId;
	/**
	 *
	 */
	@TableField("depart_id")
	private Long departId;
	/**
	 *
	 */
	@TableField("office_id")
	private Integer officeId;
	/**
	 *
	 */
	@TableField("member_name")
	private String memberName;
	/**
	 *
	 */
	@TableField("member_password")
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
	private Integer memberStatus;
	/**
	 *
	 */
	@TableField(value = "member_joinDate", fill = FieldFill.INSERT)
	private LocalDateTime memberJoinDate;
}

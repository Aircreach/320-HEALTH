package com.air.health.instruction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * 
 * 
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-29 20:13:31
 */
@Data
@TableName("tb_facility")
public class FacilityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "facility_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long facilityId;
	/**
	 * 
	 */
	@TableField("facility_name")
	private String facilityName;
	/**
	 * 
	 */
	@TableField("facility_type")
	private Integer facilityType;
	/**
	 * 
	 */
	@TableField("facility_desc")
	private String facilityDesc;
	/**
	 * 
	 */
	@TableField("facility_hold")
	private Integer facilityHold;
	/**
	 * 
	 */
	@TableField("facility_unit")
	private String facilityUnit;
	/**
	 * 
	 */
	@TableField("facility_status")
	private String facilityStatus;
	/**
	 * 
	 */
	@TableField("ins_id")
	private Long insId;

}

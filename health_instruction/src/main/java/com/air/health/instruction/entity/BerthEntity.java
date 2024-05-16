package com.air.health.instruction.entity;

import com.air.health.instruction.model.BerthStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.ibatis.type.EnumTypeHandler;

/**
 * 
 * 
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-04-29 20:13:31
 */
@Data
@TableName("tb_berth")
public class BerthEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "berth_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long berthId;
	/**
	 * 
	 */
	@TableField("berth_number")
	private Integer berthNumber;
	/**
	 * 
	 */
	@TableField("berth_location")
	private String berthLocation;

	@TableField("ins_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long insId;


	@TableField("user_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long userId;
	/**
	 * 
	 */
	@TableField("start_time")
	private LocalDateTime startTime;
	/**
	 * 
	 */
	@TableField(value = "berth_status")
	private BerthStatus berthStatus;

}

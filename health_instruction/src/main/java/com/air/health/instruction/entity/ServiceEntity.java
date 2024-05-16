package com.air.health.instruction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:38:13
 */
@Data
@TableName("tb_service")
public class ServiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "service_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long serviceId;
	/**
	 * 
	 */
	@TableField("service_type")
	private Integer serviceType;
	/**
	 * 
	 */
	@TableField("service_desc")
	private String serviceDesc;
	/**
	 * 
	 */
	@TableField("service_price")
	private Long servicePrice;
	/**
	 * 
	 */
	@TableField("ins_id")
	private Long insId;

}

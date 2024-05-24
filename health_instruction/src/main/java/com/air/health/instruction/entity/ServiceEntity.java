package com.air.health.instruction.entity;

import com.air.health.instruction.model.ServiceType;
import com.baomidou.mybatisplus.annotation.*;
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

 
	@TableId(value = "service_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long serviceId;

	@TableField("service_name")
	private String name;
 
	@TableField("service_type")
	private ServiceType type;
 
	@TableField("service_desc")
	private String description;
 
	@TableField("service_price")
	private Long price;
 
	@TableField("ins_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long insId;

	@TableField("isDeleted")
	@TableLogic(value = "0",delval = "1")
	private Integer isDeleted;
}

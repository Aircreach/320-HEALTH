package com.air.health.user.entity;

import com.air.health.user.model.OrderStatus;
import com.air.health.user.model.OrderType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * 
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:38:13
 */
@Data
@TableName("tb_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "order_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long orderId;
	/**
	 * 
	 */
	@TableField("order_type")
	private OrderType type;
	/**
	 * 
	 */
	@TableField("user_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long userId;


	@TableField("member_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long memberId;

	@TableField("service_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long serviceId;

	@TableField("order_balance")
	private Long balance;

	@TableField("order_num")
	private Integer num;

	/**
	 * 
	 */
	@TableField("order_createdDate")
	private LocalDateTime createdDate;

	@TableField("order_payedDate")
	private LocalDateTime payedDate;
	/**
	 * 
	 */
	@TableField("order_finishedDate")
	private LocalDateTime finishedDate;
	/**
	 * 
	 */
	@TableField("order_status")
	private OrderStatus status;

}

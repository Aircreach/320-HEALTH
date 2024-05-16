package com.air.health.user.entity;

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
	private Integer orderType;
	/**
	 * 
	 */
	@TableField("ins_id")
	private Long insId;
	/**
	 * 
	 */
	@TableField("member_id")
	private Long memberId;
	/**
	 * 
	 */
	@TableField("order_createdDate")
	private LocalDateTime createdDate;
	/**
	 * 
	 */
	@TableField("order_finishedDate")
	private LocalDateTime finishedDate;
	/**
	 * 
	 */
	@TableField("order_status")
	private String orderStatus;

}

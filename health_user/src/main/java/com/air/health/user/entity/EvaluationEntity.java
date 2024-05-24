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
 * @date 2024-05-19 09:18:44
 */
@Data
@TableName("tb_evaluation")
public class EvaluationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "eval_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long evalId;
	/**
	 * 
	 */
	@TableField("user_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long userId;
	/**
	 * 
	 */
	@TableField("ins_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long insId;
	/**
	 * 
	 */
	@TableField("content")
	private String content;
	/**
	 * 
	 */
	@TableField("score")
	private Integer score;
	/**
	 * 
	 */
	@TableField("createdDate")
	private LocalDateTime createdDate;

}

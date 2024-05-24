package com.air.health.instruction.entity;

import com.air.health.instruction.handler.JSONListTypeHandler;
import com.air.health.instruction.model.MedicineType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-16 15:55:15
 */
@Data
@TableName("tb_medicine")
public class MedicineEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(value = "medicine_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long medicineId;

	@TableField("medicine_name")
	private String name;
	/**
	 * 
	 */
	@TableField("medicine_number")
	private Integer number;
	/**
	 * 
	 */
	@TableField("medicine_price")
	private Integer price;
	/**
	 * 
	 */
	@TableField("medicine_desc")
	private String description;
	/**
	 * 
	 */
	@TableField("medicine_type")
	private MedicineType type;
	/**
	 * 
	 */
	@TableField(value = "medicine_label", typeHandler = JSONListTypeHandler.class)
	private List<String> label;
	/**
	 * 
	 */
	@TableField("ins_id")
	private Long insId;

}

package com.air.health.admin.entity;

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
 * @date 2024-05-24 18:28:36
 */
@Data
@TableName("tb_product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "product_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long productId;
	/**
	 * 
	 */
	@TableField("product_name")
	private String productName;
	/**
	 * 
	 */
	@TableField("product_desc")
	private String description;
	/**
	 * 
	 */
	@TableField("product_ic")
	private String icon;
	/**
	 * 
	 */
	@TableField("product_num")
	private Integer productNum;
	/**
	 * 
	 */
	@TableField("product_price")
	private Integer price;
	/**
	 * 
	 */
	@TableField(value = "createdDate", fill = FieldFill.INSERT)
	private LocalDateTime createdDate;

}

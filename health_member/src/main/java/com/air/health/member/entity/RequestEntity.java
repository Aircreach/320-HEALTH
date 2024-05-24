package com.air.health.member.entity;

import com.air.health.member.model.RequestStatus;
import com.air.health.member.model.RequestType;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * 
 * @author Aircreach
 * @email 3064568039@qq.com
 * @date 2024-05-23 18:12:06
 */
@Data
@TableName("tb_request")
public class RequestEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "request_id", type = IdType.ASSIGN_ID)
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long requestId;
	/**
	 * 
	 */
	@TableField("member_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long memberId;
	/**
	 * 
	 */
	@TableField("ins_id")
	@JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
	private Long insId;
	/**
	 * 
	 */
	@TableField("request_type")
	private RequestType requestType;
	/**
	 * 
	 */
	@TableField("request_content")
	private String requestContent;
	/**
	 * 
	 */
	@TableField("request_reason")
	private String requestReason;
	/**
	 * 
	 */
	@TableField(value = "createdDate", fill = FieldFill.INSERT)
	@OrderBy()
	private LocalDateTime createdDate;
	/**
	 * 
	 */
	@TableField("status")
	@OrderBy(asc = true)
	private RequestStatus status;

}

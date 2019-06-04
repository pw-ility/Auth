/**
 * Response Wrapper
 * @author Mayank Mishra
 * @version 1.0
 * @since 2019-04-16 
 */

package com.ility.customconfig.exception;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(NON_NULL)

public class ResponseWrapper {

    private Object data;
    private Object meta;
    private List<ErrorMessage> errors;

	/**
	 * @param data
	 * @param metadata
	 * @param errors
	 */
	public ResponseWrapper(Object data, Object meta, List<ErrorMessage> errors) {
		super();
		this.data = data;
		this.meta = meta;
		this.errors = errors;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the metadata
	 */
	public Object getMeta() {
		return meta;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMeta(Object meta) {
		this.meta = meta;
	}

	/**
	 * @return the errors
	 */
	public List<ErrorMessage> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<ErrorMessage> errors) {
		this.errors = errors;
	}
	
	public static ResponseWrapper setResponse(Object data, HttpStatus statusCode, String status, List<ErrorMessage> errors) {
		HashMap<Object, Object> map = new HashMap<Object, Object>(); 
		map.put("status", status);
		map.put("code", statusCode.value());
		return new ResponseWrapper(data, map, errors);
	}

    
   
}

package com.ility.customconfig.controller;
/**
 * 
 */

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nimbusds.jwt.proc.BadJWTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ility.customconfig.exception.ResponseWrapper;

/**
 * The BaseController class implements common functionality for all Controller
 * classes. The <code>@ExceptionHandler</code> methods provide a consistent
 * response when Exceptions are thrown from <code>@RequestMapping</code>
 * annotated Controller methods.
 * 
 */
public class BaseController {

    /**
     * The Logger for this class.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpServletRequest  request;
    private HttpServletResponse response;
    private HttpSession         session;
    
    /**
     * Handles JPA NoResultExceptions thrown from web service controller
     * methods. Creates a response with Exception Attributes as JSON and HTTP
     * status code 404, not found.
     * 
     * @param noResultException A NoResultException instance.
     * @param request The HttpServletRequest in which the NoResultException was
     *        raised.
     * @return A ResponseEntity containing the Exception Attributes in the body
     *         and HTTP status code 404.
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ResponseWrapper> handleNoResultException(NoResultException noResultException, HttpServletRequest request) {
       // ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
      //  Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(noResultException, request,HttpStatus.NOT_FOUND);
       return ResponseEntity.ok(ResponseWrapper.setResponse(new Object[] {} , HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), null));
    }

    /**
     * Handles all Exceptions not addressed by more specific
     * <code>@ExceptionHandler</code> methods. Creates a response with the
     * Exception Attributes in the response body as JSON and a HTTP status code
     * of 500, internal server error.
     * 
     * @param exception An Exception instance.
     * @param request The HttpServletRequest in which the Exception was raised.
     * @return A ResponseEntity containing the Exception Attributes in the body
     *         and a HTTP status code 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper> handleException(Exception exception, HttpServletRequest request) {
        //ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
       // Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(exception, request,HttpStatus.INTERNAL_SERVER_ERROR);
    	 return ResponseEntity.ok(ResponseWrapper.setResponse(new Object[] {} , HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
    }

    @ExceptionHandler(BadJWTException.class)
    public ResponseEntity<ResponseWrapper> handleBadJWTException(NoResultException noResultException, HttpServletRequest request) {
        // ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
        //  Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(noResultException, request,HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(ResponseWrapper.setResponse(new Object[] {} , HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), null));
    }
    
   
    @ModelAttribute
    protected void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected HttpServletResponse getResponse() {
        return response;
    }

    protected HttpSession getSession() {
        return session;
    }

}
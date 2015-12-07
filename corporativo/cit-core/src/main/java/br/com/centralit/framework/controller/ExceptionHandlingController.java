package br.com.centralit.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.ErrorInfo;
import br.com.centralit.framework.exception.ValidacaoException;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: ExceptionHandlingController</b></p>
 *
 * <p><b>Description: Tratamento generico de exception's</b></p>
 *
 * @since 18/11/2014 - 17:10:49
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@ControllerAdvice
public class ExceptionHandlingController {

	/**
	 * Método responsável por retorna json da exception capturada, sempre retorna {@link br.com.centralit.framework.exception.ErrorInfo}
	 *
	 * @author ally.barra
	 *
	 * @param req
	 * @param ex
	 * @return {@link br.com.centralit.framework.exception.ErrorInfo}
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {

		System.out.println(ex);

		System.out.println(ex.getMessage());

	    return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.toString());
	}

	/**
	 * Método responsável por retorna json da BusinessException capturada, sempre retorna {@link br.com.centralit.framework.exception.ErrorInfo}
	 *
	 * @author ally.barra
	 *
	 * @param req
	 * @param ex
	 * @return {@link br.com.centralit.framework.exception.ErrorInfo}
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, BusinessException ex) {

		System.out.println(ex);

		System.out.println(ex.getMessage());

	    return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.toString());
	}

	/**
	 * Método responsável por retornar json da exception capturada
	 *
	 * @author wilker.machado
	 *
	 * @param req
	 * @param ex
	 * @return <code>ErrorInfo</code>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidacaoException.class)
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, ValidacaoException ex) {

		System.out.println(ex);

	    return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.toString());
	}

	/**
	 * Método responsável por retornar json da exception capturada
	 *
	 * @author wilker.machado
	 *
	 * @param req
	 * @param ex
	 * @return <code>ErrorInfo</code>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, AccessDeniedException ex) {

		System.out.println(ex);

	    return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.toString());
	}

	/**
	 * Método responsável por retornar json da exception capturada
	 *
	 * @author wilker.machado
	 *
	 * @param req
	 * @param ex
	 * @return <code>ErrorInfo</code>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ErrorInfo handleBadRequest(HttpServletRequest req, Throwable ex) {

		System.out.println(ex);

	    return new ErrorInfo(req.getRequestURL().toString(), ex, HttpStatus.BAD_REQUEST.toString());
	}
}
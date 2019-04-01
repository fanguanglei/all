package com.xw.controller;


import com.xw.util.DateEditor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * ��������
 */
public class BaseController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	protected HttpServletRequest request;
		/**
		 * ��InitBinder��ʾ�ķ��������Զ�WebDataBinder������г�ʼ����WebDataBinder��DataBinder�����࣬
		 * ��������ɱ���JavaBean���Եİ󶨡� InitBinder���������з���ֵ����������Ϊvoid��
		 * InitBinder�����Ĳ���ͨ����WebDataBinder��@InitBinder���Զ�WebDataBinder���г�ʼ����
		 * 
		 * @time 2018��4��10�� ����5:12:31.</br>
		 * @version V1.0</br>
		 * @param webDataBinder</br>
		 */
	@InitBinder
	protected void initBinder(WebDataBinder webDataBinder) {
		/**
		 * һ������trim �� String���͵����Ա༭�� ��Ĭ��ɾ�����ߵĿո�charsToDelete���ԣ���������Ϊ�����ַ�
		 * emptyAsNull���ԣ���һ�����ַ���ת��Ϊnullֵ��ѡ�
		 */
		webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	/**
	 * �����ض���
	 *
	 * @param path
	 * @return String
	 */
	protected String redirect(String path) {
		return "redirect:" + path;
	}

	/**
	 * �������ض���
	 *
	 * @param response
	 * @param path
	 * @return String
	 */
	protected String redirect(HttpServletResponse response, String path) {
		try {
			response.sendRedirect(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ȡ��ҳ���� ������
	 * 
	 * @return PageRequest
	 */
	protected PageRequest getPageRequest() {
		int page = 1;
		int size = 10;
		Sort sort = null;
		try {
			String sortName = request.getParameter("sortName");
			String sortOrder = request.getParameter("sortOrder");
			if (StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)) {
				if (sortOrder.equalsIgnoreCase("desc")) {
					sort = new Sort(Sort.Direction.DESC, sortName);
				} else {
					sort = new Sort(Sort.Direction.ASC, sortName);
				}
			}

			page = Integer.parseInt(request.getParameter("pageNumber"))-1;
			size = Integer.parseInt(request.getParameter("pageSize"));
			log.info("ҳ��" + page);
			log.info("����" + size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageRequest pageRequest = new PageRequest(page, size, sort);
		return pageRequest;
	}

	/**
	 * ��ȡ��ҳ���� ������
	 * 
	 * @param sort
	 *           ��������
	 * @return PageRequest
	 */
	protected PageRequest getPageRequest(Sort sort) {
		int page = 0;
		int size = 10;
		try {
			String sortName = request.getParameter("sortName");
			String sortOrder = request.getParameter("sortOrder");
			if (StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)) {
				if (sortOrder.equalsIgnoreCase("desc")) {
					sort.and(new Sort(Sort.Direction.DESC, sortName));
				} else {
					sort.and(new Sort(Sort.Direction.ASC, sortName));
				}
			}
			page = Integer.parseInt(request.getParameter("pageNumber")) - 1;
			size = Integer.parseInt(request.getParameter("pageSize"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageRequest pageRequest = new PageRequest(page, size, sort);
		return pageRequest;
	}

}

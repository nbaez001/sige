package com.sige.servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DescargarArchivo
 */
@WebServlet("/DescargarArchivo")
public class DescargarArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DescargarArchivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileName = (String) request.getParameter("fileName");
		String fileLocation = (String) request.getParameter("fileLocation");
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;

		try {
			stream = response.getOutputStream();
			File doc = new File(fileLocation + fileName);
			// set response headers
			response.setContentType("application/msword");
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			response.setContentLength((int) doc.length());
			FileInputStream input = new FileInputStream(doc);
			buf = new BufferedInputStream(input);
			int readBytes = 0;

			while ((readBytes = buf.read()) != -1)
				stream.write(readBytes);

		} catch (IOException ioe) {
			throw new ServletException(ioe.getMessage());
		} finally {
			if (stream != null)
				stream.close();
			if (buf != null)
				buf.close();
		}
	}

}

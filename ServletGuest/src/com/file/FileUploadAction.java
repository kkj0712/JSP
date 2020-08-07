package com.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUploadAction
 */
@WebServlet("/file/upload.do")
public class FileUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int uploadFileSizeLimit=5*1024*1024; //5mb�� ����
		String encType="UTF-8";
		String savePath="upload";
		ServletContext context=getServletContext();
		String uploadFilePath=context.getRealPath(savePath);
		System.out.println("���� ���� ���� ���丮: "+uploadFilePath);
		
		//multipart/form ����� request.getParameter�� �޾ƿ� �� ����
		MultipartRequest multi=new MultipartRequest(
				request, //request��ü
				uploadFilePath, //�������� ���� ���丮
				uploadFileSizeLimit, //�ִ� ���ε� ���� ũ��
				encType, //���ڵ� ���
				new DefaultFileRenamePolicy()); //���� ���� ���̸� �ο�
		//���ε�� �����̸� ���ϱ�
		String fileName=multi.getFilesystemName("uploadFile");
		System.out.println("fileName:"+fileName);
		if(fileName==null) { //���� ���ε� �ȵ�
			System.out.println("���� ���ε� ���� �ʾ���");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<b>�۾���: "+multi.getParameter("name")+"<br>");
			out.println("<b>����: "+multi.getParameter("title")+"<br>");
			out.println("<b>���ϸ�: "+fileName);
		}
	}
}
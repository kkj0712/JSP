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
		int uploadFileSizeLimit=5*1024*1024; //5mb로 지정
		String encType="UTF-8";
		String savePath="upload";
		ServletContext context=getServletContext();
		String uploadFilePath=context.getRealPath(savePath);
		System.out.println("서버 상의 실제 디렉토리: "+uploadFilePath);
		
		//multipart/form 방식은 request.getParameter로 받아올 수 없음
		MultipartRequest multi=new MultipartRequest(
				request, //request객체
				uploadFilePath, //서버상의 실제 디렉토리
				uploadFileSizeLimit, //최대 업로드 파일 크기
				encType, //인코딩 방법
				new DefaultFileRenamePolicy()); //동일 파일 새이름 부여
		//업로드된 파일이름 구하기
		String fileName=multi.getFilesystemName("uploadFile");
		System.out.println("fileName:"+fileName);
		if(fileName==null) { //파일 업로드 안됨
			System.out.println("파일 업로드 되지 않았음");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<b>글쓴이: "+multi.getParameter("name")+"<br>");
			out.println("<b>제목: "+multi.getParameter("title")+"<br>");
			out.println("<b>파일명: "+fileName);
		}
	}
}
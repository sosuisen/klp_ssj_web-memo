package com.example;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemoServlet extends HttpServlet {     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getMemoList(request.getSession());
		request.getRequestDispatcher("/WEB-INF/memo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		var text = request.getParameter("memo");
		var memoList = getMemoList(request.getSession());
		memoList.add(new Memo(text));
		doGet(request, response);
	}
	
	private ArrayList<Memo> getMemoList(HttpSession session) {
		var memoList = (ArrayList<Memo>) session.getAttribute("memoList");
		if (memoList == null) {
			memoList = new ArrayList<Memo>();
			session.setAttribute("memoList", memoList);
		}
		return memoList;
	}
}

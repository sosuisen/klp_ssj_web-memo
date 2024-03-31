package com.example;

import java.io.IOException;

import com.example.model.Memo;
import com.example.model.MemoManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Controller
public class MemoServlet extends HttpServlet {     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからModelモジュールのオブジェクトを取得します。
		var model = getModel(request.getSession());

		// Modelの管理するデータを、Viewで表示したい形式へ変換します。		
		// 具体的には、MemoManagerが保持しているMemoオブジェクトのリストを、文字列のリストへ変換。
		var textList = model.getMemoList().stream().map(memo -> memo.getText()).toList();

		// 変換結果のtextListをViewに渡して、Webブラウザへ返します。
		request.setAttribute("textList", textList);
		request.getRequestDispatcher("/WEB-INF/memo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// Webブラウザから送られてきたデータを取得します。
		var text = request.getParameter("memo");

		// セッションからModelモジュールのオブジェクトを取得します。
		var model = getModel(request.getSession());
		
		// Modelの管理するデータを、更新します。
		model.add(new Memo(text));
		
		// この後の処理はdoGetメソッドと同じなので、任せます。
		doGet(request, response);
	}
	
	private MemoManager getModel(HttpSession session) {
		// ControllerからModelモジュールを呼び出すためのメソッドです。
		
		// Modelモジュール側の窓口として、MemoManagerオブジェクトがセッションに登録されているものとします。
		var manager = (MemoManager) session.getAttribute("manager");
		// まだ、セッションに保存されていない場合はnullなので、新たに作成してセッションに保存します。
		if (manager == null) {
			manager = new MemoManager();
			session.setAttribute("manager", manager);
		}
		return manager;
	}
}

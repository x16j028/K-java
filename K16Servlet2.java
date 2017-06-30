package apli;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class K16Servlet2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		//super.doGet(req, resp);
		System.out.println("K16Servlet2開始");
		HttpSession sess =req.getSession();
		String x = (String)req.getAttribute("TOI");
		String mon = x.getmonNo();
		

		String y = req.getParameter("corse");

		//inSyaNo = "'"+inSyaNo+"'";
		DBAcs dba = new DBAcs();

		try {
			ResultSet rs =
					dba.selectExe("select * from 問題表 where 番号 = '"+monn+"'");
			rs.next();
			String url = "";

				int no = rs.getInt("番号");
				String bun = rs.getString("問題文");
				String a = rs.getString("選択ア");
				String i = rs.getString("選択イ");
				String u = rs.getString("選択ウ");
				String e = rs.getString("選択エ");
				String kai = rs.getString("解答");

				System.out.println(no+bun);
				System.out.println(a);
				System.out.println(i);
				System.out.println(u);
				System.out.println(e);
				System.out.println(kai);
				Mondai m = new Mondai();
				m.setZikai(y);
				m.setNo(no);
				m.setToi(bun);
				m.setSena(a);
				m.setSeni(i);
				m.setSenu(u);
				m.setSene(e);
				m.setKai(kai);


				System.out.println("セット");

				//セッション取得
				HttpSession ses = req.getSession();
				ses.setAttribute("TOI", m);

				dba.closeDB();//ログアウト

				//画面遷移
				url = "k16_3.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);//エラーの表示
			dba.closeDB();//ログアウト
		}
		System.out.println("K16Servlet2終了");
	}
}

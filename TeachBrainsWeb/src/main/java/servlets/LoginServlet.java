package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.LoginController;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		
		
		String appName = getServletContext().getInitParameter("appName");
		String pageName = getServletConfig().getInitParameter("pageName");
		
		response.setContentType("text/html");
		
		String username = request.getParameter("user");
		String pswd = request.getParameter("pswd");
		
		PrintWriter out = response.getWriter();
		//out.write("Entered values are " + username + " " + pswd);
		//out.write("<b> Bold</b>");
		
		//RequestDispatcher success = request.getRequestDispatcher("views/home.html");
		RequestDispatcher success = request.getRequestDispatcher("views/home.html");
		
		RequestDispatcher failure = request.getRequestDispatcher("login.html");
		
		Cookie cookie = new Cookie("username", username);
		cookie.setMaxAge(1000);
		
		//if(new LoginController().login(username, pswd))
		if(new LoginController().login(username, pswd))
		{
			out.write("Login successfull");
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			response.addCookie(cookie);
			//success.forward(request, response);
			success.forward(request, response);
		}
		else
		{
			out.write(appName + " <br>");
			out.write("Login Failed");
			out.write(pageName);
			failure.include(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

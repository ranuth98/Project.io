package com.cognizant.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.dao.PackageDao;
import com.cognizant.dao.PackageDaoImpl;
import com.cognizant.dao.PolicyDao;
import com.cognizant.dao.PolicyDaoImpl;
import com.cognizant.dao.UserDao;
import com.cognizant.dao.UserDaoImpl;
import com.cognizant.model.Package;
import com.cognizant.model.Policy;
import com.cognizant.model.User;

/**
 * Servlet implementation class PackageStatusServlet
 */
@WebServlet({  "/Index","/PackageStatus", "/PackageView", "/ViewPolicy", "/UserProfile", "/TrackPackage" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlMap = request.getServletPath();
		if(urlMap.equals("/Index")) {
			request.getRequestDispatcher("WEB-INF/user/user-registration.jsp").forward(request, response);
		}else if (urlMap.equals("/PackageView")) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("userSession");
			String userName = user.getUserName();
			PackageDao packageDao = new PackageDaoImpl();
			List<Package> packageList = packageDao.getAllPackage(userName);
			request.setAttribute("packageList", packageList);
			request.getRequestDispatcher("WEB-INF/user/package-view.jsp").forward(request, response);
		} else if (urlMap.equals("/ViewPolicy")) {
			PolicyDao policyDao = new PolicyDaoImpl();
			Policy policy = policyDao.getPolicy();
			request.setAttribute("policy", policy);
			request.getRequestDispatcher("WEB-INF/user/policy-view.jsp").forward(request, response);
		} else if (urlMap.equals("/TrackPackage")) {
			request.getRequestDispatcher("WEB-INF/user/track-package.jsp").forward(request, response);
		} else if (urlMap.equals("/UserProfile")) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("userSession");
			String userName = user.getUserName();
			UserDao userDao = new UserDaoImpl();
			User userObj = userDao.getUser(userName);
			request.setAttribute("user", userObj);
			request.getRequestDispatcher("WEB-INF/user/user-profile.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlMap = request.getServletPath();
		if (urlMap.equals("/PackageStatus")) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("userSession");
			String userName = user.getUserName();
			PackageDao packageDao = new PackageDaoImpl();
			Package packages = new Package();
			long packageId = Integer.parseInt(request.getParameter("packageId"));
			packages = packageDao.getPackage(packageId);
			;
			if (packages != null) {
				request.setAttribute("location", packages.getWarehouseLocation().getLocation());
				request.setAttribute("packages", packages);
				if (!packages.getUserName().getUserName().equalsIgnoreCase(userName)) {
					request.setAttribute("track", true);
					request.getRequestDispatcher("WEB-INF/user/track-package.jsp").forward(request, response);
				} else if (packages.getStatus().equalsIgnoreCase("Booked")) {
					request.setAttribute("status", "booked");
					request.getRequestDispatcher("WEB-INF/user/track-package.jsp").forward(request, response);
				} else if (packages.getStatus().equalsIgnoreCase("In Transit")) {
					request.setAttribute("status", "intransit");
					request.getRequestDispatcher("WEB-INF/user/track-package.jsp").forward(request, response);
				} else if (packages.getStatus().equalsIgnoreCase("In Delivery")) {
					request.setAttribute("status", "indelivery");
					request.getRequestDispatcher("WEB-INF/user/track-package.jsp").forward(request, response);
				} else if (packages.getStatus().equalsIgnoreCase("Delivered")) {
					request.setAttribute("status", "delivered");
					request.getRequestDispatcher("WEB-INF/user/track-package.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("track", true);
				request.getRequestDispatcher("WEB-INF/user/track-package.jsp").forward(request, response);
			}
		}
	}

}

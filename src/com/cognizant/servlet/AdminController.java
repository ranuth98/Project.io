package com.cognizant.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.dao.ConsignmentDao;
import com.cognizant.dao.ConsignmentDaoImpl;
import com.cognizant.dao.ConsignmentListDao;
import com.cognizant.dao.ConsignmentListDaoImpl;
import com.cognizant.dao.EmployeeDao;
import com.cognizant.dao.EmployeeDaoImpl;
import com.cognizant.dao.PackageDao;
import com.cognizant.dao.PackageDaoImpl;
import com.cognizant.dao.ParcelTypeDao;
import com.cognizant.dao.ParcelTypeDaoImpl;
import com.cognizant.dao.PolicyDao;
import com.cognizant.dao.PolicyDaoImpl;
import com.cognizant.dao.QuotationDao;
import com.cognizant.dao.QuotationDaoImpl;
import com.cognizant.dao.UserDao;
import com.cognizant.dao.UserDaoImpl;
import com.cognizant.dao.UserNameNotAvailableException;
import com.cognizant.dao.WareHouseDao;
import com.cognizant.dao.WareHouseDaoImpl;
import com.cognizant.model.Consignment;
import com.cognizant.model.ConsignmentList;
import com.cognizant.model.Employee;
import com.cognizant.model.Package;
import com.cognizant.model.ParcelType;
import com.cognizant.model.Policy;
import com.cognizant.model.Quotation;
import com.cognizant.model.User;
import com.cognizant.model.WareHouse;

/**
 * Servlet implementation class AdminController
  */
@WebServlet({ "/SuperAdminHome","/UserRegistration", "/AdminHome", "/StaffHome", "/UserHome", "/Login", "/ParcelTypeView",
		"/ParcelUpdate", "/ParcelSave", "/AddConsignment", "/AddWareHouse", "/CreateConsignment", "/AdminApproval",
		"/AdminReject", "/StaffApproval", "/StaffReject", "/EmployeeRegistration", "/LoginAuthentication",
		"/ShowStaffList", "/EditStaff", "/RemoveStaff", "/UpdateStaff", "/PackageRegistration", "/CalculateBill",
		"/GenerateBill", "/PackageSave", "/AddNewPackage", "/PackageUpdate", "/ViewPackageDetails", "/SearchPackage",
		"/EditPolicy", "/UpdatePolicy", "/QuotationView", "/QuotationUpdate", "/QuotationSave", "/Registration",
		"/ShowWareHouseDetails", "/UpdateConsignment", "/AddToThisConsignment", "/PackageUpdateInConsignment",
		"/UpdateWareHouse", "/UserApproval", "/UserReject", "/ViewConsignment", "/ViewAdminRegistrationRequest",
		"/LogOut", "/ViewStaffRegistrationRequest", "/ViewUserRegistrationRequest" ,"/AboutUs"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static boolean setEmployeeApprovalStatus(long employeeId, char active, long salary) {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		Employee employee = employeeDao.getEmployee(employeeId);
		employee.setSalary(salary);
		employee.setActive(active);
		return employeeDao.updateEmployee(employee);
	}

	private static List<Employee> getAllStaff() {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<Employee> employeeList = new ArrayList<Employee>();
		for (Employee i : employeeDao.getAllEmployees()) {
			if (i.getActive() == 'y') {
				if (i.getLoginType() == 's') {
					employeeList.add(i);
				}
			}
		}
		return employeeList;
	}

	private static boolean setUserApprovalStatus(long userId, String active) {
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUser(userId);
		user.setActive(active);

		boolean check = userDao.updateUser(user);

		return check;
	}

	private static List<Consignment> getConsignmentList(long warehouseId, String status) {
		ConsignmentDao consignmentDao = new ConsignmentDaoImpl();
		List<Consignment> consignmentList = new ArrayList<Consignment>();
		for (Consignment i : consignmentDao.getAllConsignments()) {
			if (status.equals("from")) {
				if (warehouseId == i.getFromWareHouse().getId()) {
					consignmentList.add(i);
				}
			} else if (status.equals("to")) {
				if (warehouseId == i.getToWareHouse().getId()) {
					consignmentList.add(i);
				}
			} else {
				if (warehouseId == i.getToWareHouse().getId() || warehouseId == i.getFromWareHouse().getId()) {
					consignmentList.add(i);
				}
			}
		}
		return consignmentList;
	}

	private static List<Employee> getEmployeeRegistrationList(char loginType) {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<Employee> employeeList = new ArrayList<Employee>();
		for (Employee i : employeeDao.getAllEmployees()) {
			if (i.getActive() == 'w') {
				if (i.getLoginType() == loginType) {
					employeeList.add(i);
				}
			}
		}
		return employeeList;
	}

	private static List<User> getUserRegistrationList() {
		UserDao userDao = new UserDaoImpl();
		List<User> userList = new ArrayList<>();
		for (User i : userDao.getAllUsers()) {

			if (i.getActive().equals("w")) {
				userList.add(i);
			}
		}
		return userList;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlMap = request.getServletPath();
		if(urlMap.equals("/SuperAdminHome")) {
			request.getRequestDispatcher("WEB-INF/super-admin/super-admin-home-page.jsp").forward(request, response);
		}else if (urlMap.equals("/AdminHome")) {
			request.getRequestDispatcher("WEB-INF/admin/admin-home-page.jsp").forward(request, response);
		} else if (urlMap.equals("/StaffHome")) {
			request.getRequestDispatcher("WEB-INF/staff/staff-home-page.jsp").forward(request, response);
		} else if (urlMap.equals("/UserHome")) {
			request.getRequestDispatcher("WEB-INF/user/user-home-page.jsp").forward(request, response);
		} else if (urlMap.equals("/Login")) {
			if (request.getParameter("role").equals("a")) {
				request.setAttribute("loginType", request.getParameter("role"));
			} else if (request.getParameter("role").equals("u")) {
				request.setAttribute("loginType", request.getParameter("role"));
			} else if (request.getParameter("role").equals("s")) {
				request.setAttribute("loginType", request.getParameter("role"));
			}
			request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request, response);
		} else if (urlMap.equals("/ParcelTypeView")) {
			ParcelTypeDao parcelTypeDao = new ParcelTypeDaoImpl();
			List<ParcelType> parcelList = parcelTypeDao.getAllParcelType();
			request.setAttribute("parcel", parcelList);
			request.getRequestDispatcher("WEB-INF/admin/parcel-view.jsp").forward(request, response);
		} else if (urlMap.equals("/ParcelUpdate")) {
			ParcelTypeDao parcelTypeDao = new ParcelTypeDaoImpl();
			int parcelId = Integer.parseInt(request.getParameter("parcelId"));
			if (parcelId == 0) {
				request.setAttribute("add", true);
				request.getRequestDispatcher("WEB-INF/admin/parcel-update.jsp").forward(request, response);
			} else {
				ParcelType parcel = parcelTypeDao.getParcelType(parcelId);
				request.setAttribute("parcel", parcel);
				request.getRequestDispatcher("WEB-INF/admin/parcel-update.jsp").forward(request, response);
			}
		} else if (urlMap.equals("/AddWareHouse")) {
			String id = request.getParameter("wareHouseId");
			char from = request.getParameter("from").charAt(0);
			if (id == null || id == "") {
				// add a new Ware house
				WareHouse wareHouse = new WareHouse();
				Employee employee = new Employee();
				wareHouse.setName(request.getParameter("name"));
				wareHouse.setLocation(request.getParameter("location"));
				employee.setEmployeeId(Long.parseLong(request.getParameter("managerId")));
				wareHouse.setEmployee(employee);
				wareHouse.setCapacity(Float.parseFloat(request.getParameter("capacity")));
				WareHouseDao adminDaoObj = new WareHouseDaoImpl();
				if (adminDaoObj.createWareHouse(wareHouse)) {
					response.sendRedirect("ShowWareHouseDetails?status=add");
				} else
					response.sendRedirect("ShowWareHouseDetails?status=fail");
			} else {
				// Update a wareHouse.
				WareHouse wareHouse = new WareHouse();
				Employee employee = new Employee();
				wareHouse.setId(Integer.parseInt(id));
				wareHouse.setName(request.getParameter("name"));
				wareHouse.setLocation(request.getParameter("location"));
				wareHouse.setCapacity(Float.parseFloat(request.getParameter("capacity")));
				employee.setEmployeeId(Long.parseLong(request.getParameter("managerId")));
				wareHouse.setEmployee(employee);
				WareHouseDao adminDaoObj = new WareHouseDaoImpl();
				if (adminDaoObj.updateWareHouse(wareHouse)) {
					if (from == 'r') {
						response.sendRedirect("ShowStaffList");
					} else {
						response.sendRedirect("ShowWareHouseDetails?status=update");
					}
				} else {
					response.sendRedirect("ShowWareHouseDetails?status=fail");
				}
			}
		} else if (urlMap.equals("/CreateConsignment")) {
			WareHouseDao wareHouseDao = new WareHouseDaoImpl();
			List<WareHouse> warehouseList = wareHouseDao.getAllWareHouse();
			request.setAttribute("wareHouse", warehouseList);
			request.getRequestDispatcher("WEB-INF/admin/create-consignment.jsp").forward(request, response);
		} else if (urlMap.equals("/AdminApproval")) {
			long employeeId = Long.parseLong(request.getParameter("employeeId"));
			request.setAttribute("approveStatus", AdminController.setEmployeeApprovalStatus(employeeId, 'y',
					Long.parseLong(request.getParameter("salary"))));
			String url = "ViewAdminRegistrationRequest";
			request.getRequestDispatcher(url).forward(request, response);
		} else if (urlMap.equals("/AdminReject")) {
			long employeeId = Long.parseLong(request.getParameter("employeeId"));
			request.setAttribute("removeStatus", AdminController.setEmployeeApprovalStatus(employeeId, 'n', 0));
			String url = "ViewAdminRegistrationRequest";
			request.getRequestDispatcher(url).forward(request, response);
		} else if (urlMap.equals("/StaffApproval")) {
			long employeeId = Long.parseLong(request.getParameter("employeeId"));
			request.setAttribute("approveStatus", AdminController.setEmployeeApprovalStatus(employeeId, 'y',
					Long.parseLong(request.getParameter("salary"))));
			String url = "ViewStaffRegistrationRequest";
			request.getRequestDispatcher(url).forward(request, response);
		} else if (urlMap.equals("/StaffReject")) {
			long employeeId = Long.parseLong(request.getParameter("employeeId"));
			request.setAttribute("removeStatus", AdminController.setEmployeeApprovalStatus(employeeId, 'n', 0));
			String url = "ViewStaffRegistrationRequest";
			request.getRequestDispatcher(url).forward(request, response);
		} else if (urlMap.equals("/ShowStaffList")) {
			HttpSession session = request.getSession(false);
			Employee employeeSession = (Employee) session.getAttribute("employeeSession");
			request.setAttribute("employeeSession", employeeSession);
			request.setAttribute("employeeList", AdminController.getAllStaff());
			request.getRequestDispatcher("WEB-INF/admin/modify-employee.jsp").forward(request, response);
		} else if (urlMap.equals("/EditStaff")) {
			HttpSession session = request.getSession(false);
			Employee employeeSession = (Employee) session.getAttribute("employeeSession");
			request.setAttribute("employeeSession", employeeSession);
			EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
			request.setAttribute("employee",
					employeeDao.getEmployee(Long.parseLong(request.getParameter("employeeId"))));
			request.getRequestDispatcher("WEB-INF/admin/edit-employee.jsp").forward(request, response);
		} else if (urlMap.equals("/UpdateStaff")) {
			HttpSession session = request.getSession(false);
			Employee employeeSession = (Employee) session.getAttribute("employeeSession");
			request.setAttribute("employeeSession", employeeSession);
			EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
			Employee employee = employeeDao.getEmployee(Long.parseLong(request.getParameter("employeeId")));
			employee.setFirstName(request.getParameter("firstName"));
			employee.setLastName(request.getParameter("lastName"));
			employee.setContactNumber(Long.parseLong(request.getParameter("contactNumber")));
			employee.setDesignation(request.getParameter("designation"));
			employee.setSalary(Long.parseLong(request.getParameter("salary")));
			request.setAttribute("editStatus", employeeDao.updateEmployee(employee));
			request.getRequestDispatcher("ShowStaffList").forward(request, response);
		} else if (urlMap.equals("/RemoveStaff")) {
			HttpSession session = request.getSession(false);
			Employee employeeSession = (Employee) session.getAttribute("employeeSession");
			request.setAttribute("employeeSession", employeeSession);
			EmployeeDao employeeDao = new EmployeeDaoImpl();
			int wareHouseId = employeeDao.checkEmployee(Long.parseLong(request.getParameter("employeeId")));
			if (wareHouseId != 0) {
				String url = "UpdateWareHouse?wareHouseId=" + wareHouseId + "&from=r";
				request.getRequestDispatcher(url).forward(request, response);
			} else {
				request.setAttribute("removeStatus",
						employeeDao.removeEmployee(Long.parseLong(request.getParameter("employeeId"))));
				request.getRequestDispatcher("ShowStaffList").forward(request, response);
			}
		} else if (urlMap.equals("/PackageRegistration")) {
			WareHouseDao wareHouseDao = new WareHouseDaoImpl();
			ParcelTypeDao parcelTypeDao = new ParcelTypeDaoImpl();
			List<WareHouse> locationList = new ArrayList<WareHouse>();
			locationList = wareHouseDao.getAllWareHouse();
			List<ParcelType> parcelList = new ArrayList<ParcelType>();
			parcelList = parcelTypeDao.getAllParcelType();
			request.setAttribute("parcelType", parcelList);
			request.setAttribute("warehouse", locationList);
			request.getRequestDispatcher("WEB-INF/admin/package-registration.jsp").forward(request, response);
		} else if (urlMap.equals("/GenerateBill")) {
			PackageDao packageDao = new PackageDaoImpl();
			String packageId = request.getParameter("id");
			Package packages = packageDao.getPackage((Integer.parseInt(packageId)));
			request.setAttribute("packages", packages);
			request.getRequestDispatcher("WEB-INF/admin/invoice.jsp").forward(request, response);
		} else if (urlMap.equals("/PackageUpdate")) {
			int packageId = Integer.parseInt(request.getParameter("id"));
			PackageDao packageDao = new PackageDaoImpl();
			Package packages = packageDao.getPackage(packageId);
			request.setAttribute("packages", packages);
			WareHouseDao wareHouseDao = new WareHouseDaoImpl();
			List<WareHouse> warehouseList = wareHouseDao.getAllWareHouse();
			request.setAttribute("locationList", warehouseList);
			request.getRequestDispatcher("WEB-INF/admin/package-update.jsp").forward(request, response);
		} else if (urlMap.equals("/ViewPackageDetails")) {
			PackageDao packageDao = new PackageDaoImpl();
			List<Package> packageList = new ArrayList<Package>();
			packageList = packageDao.getAllPackage();
			request.setAttribute("packageDetails", packageList);
			request.getRequestDispatcher("WEB-INF/admin/view-package-details.jsp").forward(request, response);
		} else if (urlMap.equals("/SearchPackage")) {
			long id = Long.parseLong(request.getParameter("search"));
			PackageDao packageDao = new PackageDaoImpl();
			Package packages = packageDao.getPackage(id);
			request.setAttribute("search", true);
			request.setAttribute("packages", packages);
			request.getRequestDispatcher("WEB-INF/admin/view-package-details.jsp").forward(request, response);
		} else if (urlMap.equals("/EditPolicy")) {
			PolicyDao policyDao = new PolicyDaoImpl();
			Policy policy = policyDao.getPolicy();
			request.setAttribute("policy", policy);
			request.getRequestDispatcher("WEB-INF/admin/policy-edit.jsp").forward(request, response);
		} else if (urlMap.equals("/QuotationView")) {
			QuotationDao quoataionDao = new QuotationDaoImpl();
			List<Quotation> quotationList = quoataionDao.getAllQuotation();
			request.setAttribute("quotation", quotationList);
			request.getRequestDispatcher("WEB-INF/admin/quotation-view.jsp").forward(request, response);
		} else if (urlMap.equals("/QuotationUpdate")) {
			QuotationDao quoataionDao = new QuotationDaoImpl();
			int quotationId = Integer.parseInt(request.getParameter("quotationId"));
			if (quotationId == 0) {
				request.setAttribute("add", true);
				request.getRequestDispatcher("WEB-INF/admin/quotation-update.jsp").forward(request, response);
			} else {
				Quotation quotation = quoataionDao.getQuotation(quotationId);
				request.setAttribute("quotation", quotation);
				request.getRequestDispatcher("WEB-INF/admin/quotation-update.jsp").forward(request, response);
			}
		} else if (urlMap.equals("/Registration")) {
			if (request.getParameter("role").equals("a")) {
				request.setAttribute("loginType", request.getParameter("role"));
				request.getRequestDispatcher("WEB-INF/admin/admin-registration.jsp").forward(request, response);
			} else if (request.getParameter("role").equals("u")) {
				request.getRequestDispatcher("WEB-INF/user/user-registration.jsp").forward(request, response);
			} else if (request.getParameter("role").equals("s")) {
				request.setAttribute("loginType", request.getParameter("role"));
				request.getRequestDispatcher("WEB-INF/admin/admin-registration.jsp").forward(request, response);
			}
		} else if (urlMap.equals("/ShowWareHouseDetails")) {
			WareHouseDao adminDao = new WareHouseDaoImpl();
			String status = request.getParameter("status");
			if (status != null) {
				if (status.equals("add")) {
					request.setAttribute("addStatus", true);
				} else if (status.equals("update")) {
					request.setAttribute("updateStatus", true);
				} else if (status.equals("fail")) {
					request.setAttribute("failStatus", true);
				}
			}
			request.setAttribute("wareHouse", adminDao.getAllWareHouse());
			request.getRequestDispatcher("WEB-INF/admin/ware-house-admin.jsp").forward(request, response);
		} else if (urlMap.equals("/UpdateConsignment")) {
			// CONSIGNMENT UPDATION
			ConsignmentDao consignmentDao = new ConsignmentDaoImpl();
			WareHouseDao wareHouseDao = new WareHouseDaoImpl();
			ConsignmentListDao consignmentListDao = new ConsignmentListDaoImpl();
			String consignmentIdString = request.getParameter("consignmentId");
			long consignmentId = 0;
			if (null != consignmentIdString) {
				consignmentId = Long.parseLong(request.getParameter("consignmentId")); // data
																						// required
			}
			if (null != request.getAttribute("consignmentIdServlet")) {
				consignmentId = (Long) request.getAttribute("consignmentIdServlet");
			}
			Consignment consignment = consignmentDao.getConsignment(consignmentId);
			consignment.setFromWareHouse(wareHouseDao.getWareHouse(consignment.getFromWareHouse().getId()));
			consignment.setToWareHouse(wareHouseDao.getWareHouse(consignment.getToWareHouse().getId()));
			if (null != request.getParameter("consignmentStatus")) {
				consignment.setConsignmentStatus(request.getParameter("consignmentStatus"));// data
																							// required
				if (consignmentDao.updateConsignment(consignment) == true) {
					// update consignment status
				}
			}
			request.setAttribute("consignment", consignment);
			// PACKAGE LIST DETAILS
			if (consignment.getConsignmentStatus().equals("Booked")) {
				// get from warehouse location from the consignment
				PackageDao packageDao = new PackageDaoImpl();
				List<Package> packageList = new ArrayList<>();
				List<ConsignmentList> list = new ArrayList<ConsignmentList>();
				for (Package i : packageDao.getAllPackage()) {
					if (i.getWarehouseLocation().getLocation().equals(consignment.getFromWareHouse().getLocation())) {
						if (i.getStatus().equalsIgnoreCase("Booked")) {
							packageList.add(i);
						}
					}
				}
				request.setAttribute("bookedList", packageList);
				request.setAttribute("booked", true);
				// inTransitList
				for (ConsignmentList i : consignmentListDao.getListByConsignment(consignmentId)) {
					if (i.isPackageInCons()) {
						i.setPackageObj(packageDao.getPackage(i.getPackageObj().getPackageId()));
						list.add(i);
					}
				}
				request.setAttribute("intransit", "yes");
				request.setAttribute("inTransitList", list);
			} else if (consignment.getConsignmentStatus().equals("In Transit")) {// check the status
																					// of the package
				// inTransitList
				PackageDao packageDao = new PackageDaoImpl();
				List<ConsignmentList> list = new ArrayList<ConsignmentList>();
				for (ConsignmentList i : consignmentListDao.getListByConsignment(consignmentId)) {
					if (i.isPackageInCons()) {
						i.setPackageObj(packageDao.getPackage(i.getPackageObj().getPackageId()));
						list.add(i);
					}
				}
				request.setAttribute("inTransitList", list);
				request.setAttribute("intransit", true);
			} else if (consignment.getConsignmentStatus().equals("Delivered")) {
				PackageDao packageDao = new PackageDaoImpl();
				List<ConsignmentList> list = new ArrayList<ConsignmentList>();
				for (ConsignmentList i : consignmentListDao.getListByConsignment(consignmentId)) {
					if (i.isPackageInCons()) {
						i.setPackageObj(packageDao.getPackage(i.getPackageObj().getPackageId()));
						list.add(i);
					}
				}
				List<Consignment> newConsignments = new ArrayList<Consignment>();
				for (Consignment i : consignmentDao.getAllConsignments()) {
					if (i.getConsignmentStatus().equals("Booked")) {
						if (i.getFromWareHouse().getId() == consignment.getToWareHouse().getId()) {
							newConsignments.add(i);
						}
					}
				}
				request.setAttribute("deliveredList", list);
				request.setAttribute("delivered", true);
				request.setAttribute("newConsignments", newConsignments);
			}
			// request dispatcher
			String url = "WEB-INF/admin/update-consignment.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		if (urlMap.equals("/AddToThisConsignment")) {
			long consignmentId = Long.parseLong(request.getParameter("consignmentId"));
			long packageId = Long.parseLong(request.getParameter("packageId"));
			ConsignmentDao consignmentDao = new ConsignmentDaoImpl();
			ConsignmentListDao consignmentListDao = new ConsignmentListDaoImpl();
			PackageDao packageDao = new PackageDaoImpl();
			Consignment consignmentObj = consignmentDao.getConsignment(consignmentId);
			Package packageObj = packageDao.getPackage(packageId);
			// consignmentList with pa_id and co_id created
			ConsignmentList consignmentList = new ConsignmentList();
			consignmentList.setConsignmentObj(consignmentObj);
			consignmentList.setPackageObj(packageObj);
			consignmentList.setPackageInCons(true); // status is true
			consignmentListDao.createConsignmentList(consignmentList);
			// package status changed from booked to intransit
			packageObj.setWarehouseLocation(consignmentObj.getFromWareHouse());
			packageObj.setStatus("In Transit");
			packageDao.updatePackage(packageObj);
			request.getRequestDispatcher("UpdateConsignment").forward(request, response);
		}
		if (urlMap.equals("/PackageUpdateInConsignment")) {
			long oldConsignmentListId = Long.parseLong(request.getParameter("oldConsignmentListId"));
			String newConsignmentIdString = request.getParameter("newConsignmentId"); // CHECK FOR
																						// THE REQUEST
																						// parameter
																						// name
			long packageId = Long.parseLong(request.getParameter("packageId"));// CHECK for the
																				// request parameter
																				// name
			ConsignmentDao consignmentDao = new ConsignmentDaoImpl();
			ConsignmentListDao consignmentListDao = new ConsignmentListDaoImpl();
			PackageDao packageDao = new PackageDaoImpl();
			WareHouseDao wareHouseDao = new WareHouseDaoImpl();

			if (newConsignmentIdString.equals("In Delivery")) {
				// update the old consignmentList status to false
				ConsignmentList oldConsignmentList = consignmentListDao.getConsignmentList(oldConsignmentListId);
				Consignment consignmentObj = consignmentDao
						.getConsignment(oldConsignmentList.getConsignmentObj().getConsignmentId());
				oldConsignmentList.setConsignmentObj(consignmentObj);
				Package packageObj = packageDao.getPackage(oldConsignmentList.getPackageObj().getPackageId());
				oldConsignmentList.setPackageObj(packageObj);
				oldConsignmentList.setPackageInCons(false);
				consignmentListDao.updateConsignmentList(oldConsignmentList);

				// change the package status to in delivery
				packageObj.setStatus("In Delivery");
				String location = packageObj.getWarehouseLocation().getLocation();
				// change the package location to toWareHouse location
				packageObj.setWarehouseLocation(wareHouseDao.getWareHouseByLocation(location));
				packageDao.updatePackage(packageObj);

				request.setAttribute("consignmentIdServlet", oldConsignmentList.getConsignmentObj().getConsignmentId());
				request.getRequestDispatcher("UpdateConsignment").forward(request, response);

			} else {
				long newConsignmentId = Long.parseLong(newConsignmentIdString);
				// update the old consignmentList Status to false
				ConsignmentList oldConsignmentList = consignmentListDao.getConsignmentList(oldConsignmentListId);
				oldConsignmentList.setPackageInCons(false);
				consignmentListDao.updateConsignmentList(oldConsignmentList);

				// create a new consignmentListObj and set status to true
				ConsignmentList newConsignmentList = new ConsignmentList();
				newConsignmentList.setConsignmentObj(consignmentDao.getConsignment(newConsignmentId));
				newConsignmentList.setPackageObj(packageDao.getPackage(packageId));
				newConsignmentList.setPackageInCons(true);
				consignmentListDao.createConsignmentList(newConsignmentList);

				// change the package location to the Old consignmentList (towareHouseLocation)
				Package packageObj = packageDao.getPackage(packageId);
				packageObj.getWarehouseLocation()
						.setId(newConsignmentList.getConsignmentObj().getFromWareHouse().getId());
				packageDao.updatePackage(packageObj);
				request.setAttribute("consignmentIdServlet", oldConsignmentList.getConsignmentObj().getConsignmentId());
				request.getRequestDispatcher("UpdateConsignment").forward(request, response);
			}
		} else if (urlMap.equals("/UpdateWareHouse")) {
			int wareHouseId = Integer.parseInt(request.getParameter("wareHouseId"));
			request.setAttribute("from", (request.getParameter("from")));
			EmployeeDao employeeDao = new EmployeeDaoImpl();
			WareHouseDao wareHouse = new WareHouseDaoImpl();
			List<Employee> manager = employeeDao.getWareHouseEmployees();
			request.setAttribute("manager", manager);

			if (wareHouseId != 0) {
				WareHouse wareHouseObj = wareHouse.getWareHouse(wareHouseId);
				request.setAttribute("wareHouseList", wareHouseObj);
			}
			request.getRequestDispatcher("WEB-INF/admin/ware-house-update-admin.jsp").forward(request, response);
		} else if (urlMap.equals("/UserApproval")) {
			long userId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("approval_status", AdminController.setUserApprovalStatus(userId, "y"));
			request.getRequestDispatcher("ViewUserRegistrationRequest").forward(request, response);
		} else if (urlMap.equals("/UserReject")) {
			long userId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("denied_status", AdminController.setUserApprovalStatus(userId, "n"));
			request.getRequestDispatcher("ViewUserRegistrationRequest").forward(request, response);
		} else if (urlMap.equals("/ViewConsignment")) {
			String selectedWareHouseString = request.getParameter("wareHouse");
			String selectedFromTo = request.getParameter("source");
			Long selectedWareHouse;
			if (null == selectedWareHouseString || selectedWareHouseString.equals("All")) {
				selectedWareHouse = 0L;
			} else {
				selectedWareHouse = Long.parseLong(selectedWareHouseString);
			}
			WareHouseDao wareHouseDao = new WareHouseDaoImpl();
			List<WareHouse> wareHouse = wareHouseDao.getAllWareHouse();
			request.setAttribute("wareHouse", wareHouse);
			if (selectedWareHouse == 0L || selectedWareHouseString.equals("All")) {
				request.setAttribute("wareHouseIdOption", "All");
				request.setAttribute("wareHouseLocationOption", "All");
			}
			if (selectedWareHouse != 0) {
				request.setAttribute("wareHouseIdOption", selectedWareHouse);
				request.setAttribute("wareHouseLocationOption",
						wareHouseDao.getWareHouse(selectedWareHouse).getLocation());
			}
			if (null == selectedFromTo || selectedFromTo.equals("All")) {
				request.setAttribute("selectedFromToOption", "All");
			} else {
				request.setAttribute("selectedFromToOption", selectedFromTo);
			}
			if (selectedWareHouse == 0) {
				ConsignmentDao consignment = new ConsignmentDaoImpl();
				List<Consignment> consignmentList = consignment.getAllConsignments();
				request.setAttribute("consignmentList", consignmentList);
				request.getRequestDispatcher("WEB-INF/admin/view-consignment.jsp").forward(request, response);
			} else if (selectedWareHouse != 0) {
				request.setAttribute("consignmentList",
						AdminController.getConsignmentList(selectedWareHouse, selectedFromTo));
				request.getRequestDispatcher("WEB-INF/admin/view-consignment.jsp").forward(request, response);
			}
		} else if (urlMap.equals("/ViewAdminRegistrationRequest")) {
			HttpSession session = request.getSession(false);
			Employee employeeSession = (Employee) session.getAttribute("employeeSession");
			request.setAttribute("employeeSession", employeeSession);
			request.setAttribute("employeeList", AdminController.getEmployeeRegistrationList('a'));
			String url = "WEB-INF/super-admin/view-admin-registration-request.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if (urlMap.equals("/ViewStaffRegistrationRequest")) {
			HttpSession session = request.getSession(false);
			Employee employeeSession = (Employee) session.getAttribute("employeeSession");
			request.setAttribute("employeeSession", employeeSession);
			request.setAttribute("employeeList", AdminController.getEmployeeRegistrationList('s'));
			String url = "WEB-INF/admin/view-staff-registration-request.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if (urlMap.equals("/ViewUserRegistrationRequest")) {
			HttpSession session = request.getSession(false);
			User userSession = (User) session.getAttribute("userSession");
			request.setAttribute("userSession", userSession);
			request.setAttribute("userList", AdminController.getUserRegistrationList());
			String url = "WEB-INF/admin/view-user-registration-request.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if (urlMap.equals("/LogOut")) {
			request.setAttribute("loginType", request.getParameter("role"));
			request.setAttribute("logout_status", true);
			HttpSession session = request.getSession(false);
			session.invalidate();
			request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request, response);
		}
		if(urlMap.equals("/AboutUs")) {
			request.getRequestDispatcher("WEB-INF/admin/AboutUs.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlMap = request.getServletPath();
		if (urlMap.equals("/ParcelSave")) {
			ParcelTypeDao parcelTypeDao = new ParcelTypeDaoImpl();
			try {
				String parcelIdString = request.getParameter("parcelId");
				String parcelName = request.getParameter("parcelName");
				float price = Float.parseFloat(request.getParameter("price"));
				if (parcelIdString == "" || parcelIdString == null) {
					ParcelType parcel = new ParcelType();
					parcel.setParcelName(parcelName);
					parcel.setPrice(price);
					if (parcelTypeDao.createParcelType(parcel)) {
						request.setAttribute("success", true);
						request.getRequestDispatcher("WEB-INF/admin/parcel-update.jsp").forward(request, response);
					} else {
						request.setAttribute("failure", true);
						request.getRequestDispatcher("WEB-INF/admin/parcel-update.jsp").forward(request, response);
					}
				} else {
					int parcelId = Integer.parseInt(parcelIdString);
					ParcelType parcel = new ParcelType(parcelId, parcelName, price);
					if (parcelTypeDao.updateParcelType(parcel)) {
						request.setAttribute("success", true);
						request.getRequestDispatcher("WEB-INF/admin/parcel-update.jsp").forward(request, response);
					} else {
						request.setAttribute("failure", true);
						request.getRequestDispatcher("WEB-INF/admin/parcel-update.jsp").forward(request, response);
					}
				}
			} catch (RuntimeException e) {
				request.setAttribute("success", false);
				request.getRequestDispatcher("WEB-INF/admin/parcel-update.jsp").forward(request, response);
			}
		} else if (urlMap.equals("/AddConsignment")) {
			WareHouse fromWareHouse = new WareHouse();
			WareHouse toWareHouse = new WareHouse();
			Consignment consignment = new Consignment();
			ConsignmentDao consignmentDao = new ConsignmentDaoImpl();
			fromWareHouse.setId(Long.parseLong(request.getParameter("fromWareHouse")));
			toWareHouse.setId(Long.parseLong(request.getParameter("toWareHouse")));
			consignment.setConsignmentStatus(request.getParameter("consignmentStatus"));
			consignment.setFromWareHouse(fromWareHouse);
			consignment.setToWareHouse(toWareHouse);
			consignmentDao.createConsignment(consignment);
			response.sendRedirect("ViewConsignment");
		} else if (urlMap.equals("/EmployeeRegistration")) {
			String url = null;
			EmployeeDao employeeDao = new EmployeeDaoImpl();
			Employee employee = new Employee();
			employee.setUserName(request.getParameter("userId"));
			employee.setFirstName(request.getParameter("firstName"));
			employee.setLastName(request.getParameter("lastName"));
			employee.setGender(request.getParameter("gender"));
			employee.setEmail(request.getParameter("email"));
			employee.setContactNumber(Long.parseLong(request.getParameter("contactNumber")));
			employee.setPassword(request.getParameter("password"));
			employee.setSalary(0);
			employee.setDesignation(request.getParameter("designation"));
			employee.setCorrespondenceAddress(request.getParameter("correspondenceAddress"));
			employee.setPermanentAddress(request.getParameter("permanentAddress"));
			employee.setLoginType(request.getParameter("loginType").charAt(0));
			employee.setActive(request.getParameter("active").charAt(0));
			try {
				if (employeeDao.createEmployee(employee)) {
					request.setAttribute("loginType", request.getParameter("loginType"));
					request.setAttribute("registrationStatus", true);
					url = "WEB-INF/super-admin/login-page.jsp";
				}
			} catch (UserNameNotAvailableException e) {
				request.setAttribute("loginType", request.getParameter("loginType"));
				request.setAttribute("userNameAvailableStatus", true);
				url = "WEB-INF/admin/admin-registration.jsp";
			}
			request.getRequestDispatcher(url).forward(request, response);
		} else if (urlMap.equals("/LoginAuthentication")) {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String loginType = request.getParameter("role");
			HttpSession session = request.getSession(true);

			if (loginType.equals("u")) {// user login Authentication
				UserDao userDao = new UserDaoImpl();
				User user = null;
				user = userDao.getUser(userName);
				if (user != null) {// If user exist
					if (user.getActive().equals("y")) {// check active status
						if (user.getPassword().equals(password)) {// Check password
							session.setAttribute("userSession", user);

							request.getRequestDispatcher("WEB-INF/user/user-home-page.jsp").forward(request, response);
						} else {
							request.setAttribute("loginStatus", true);
							request.setAttribute("loginType", loginType);
							request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request,
									response);
						}
					} else if (user.getActive().equals("w")) {// check active status
						request.setAttribute("waitingStatus", true);
						request.setAttribute("loginType", loginType);
						request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request, response);
					} else {
						request.setAttribute("deniedStatus", true);
						request.setAttribute("loginType", loginType);
						request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request, response);
					}
				} else {// If user not exist
					request.setAttribute("loginStatus", true);
					request.setAttribute("loginType", loginType);
					request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request, response);
				}

			} else {
				// employee login Authentication
				EmployeeDao employeeDao = new EmployeeDaoImpl();
				Employee employee = null;
				employee = employeeDao.getEmployee(userName);
				String url = null;
				if (employee != null) {// check user
					if (employee.getActive() == 'y') { // check active status
						if (employee.getPassword().equals(password)) {// check password
							// session starts
							session.setAttribute("employeeSession", employee);
							if (employee.getUserName().equals("admin")) { // check login type
								url = "WEB-INF/super-admin/super-admin-home-page.jsp";
							} else if (employee.getLoginType() == 'a') {
								url = "WEB-INF/admin/admin-home-page.jsp";
							} else if (employee.getLoginType() == 's') {
								url = "WEB-INF/staff/staff-home-page.jsp";
							}
							request.getRequestDispatcher(url).forward(request, response);
						} else {
							request.setAttribute("loginStatus", true);
							request.setAttribute("loginType", loginType);
							request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request,
									response);
						}
					} else if (employee.getActive() == 'w') {
						request.setAttribute("waitingStatus", true);
						request.setAttribute("loginType", loginType);
						request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request, response);
					} else {
						request.setAttribute("deniedStatus", true);
						request.setAttribute("loginType", loginType);
						request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("loginStatus", true); // no user exists in that name
					request.setAttribute("loginType", loginType);
					request.getRequestDispatcher("WEB-INF/super-admin/login-page.jsp").forward(request, response);
				}

			}
		} else if (urlMap.equals("/CalculateBill")) {
			String parcelName = request.getParameter("parcelType");
			int distance = Integer.parseInt(request.getParameter("distance"));
			float weight = Float.parseFloat(request.getParameter("packageWeight"));
			User user = new User();
			WareHouse wareHouse = new WareHouse();
			ParcelType parcelType = new ParcelType();
			user.setUserName(request.getParameter("userName"));
			String userName = user.getUserName();
			parcelType.setParcelName(parcelName);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date bookDate = null;
			try {
				bookDate = format.parse(request.getParameter("bookDate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String senderAddress = request.getParameter("senderAddress");
			String receiverAddress = request.getParameter("receiverAddress");
			wareHouse.setLocation(request.getParameter("location"));
			String status = request.getParameter("status");
			Package packages = new Package(user, parcelType, bookDate, weight, distance, senderAddress, receiverAddress,
					status, wareHouse, 0.0f);
			ParcelTypeDao parcelTypeDao = new ParcelTypeDaoImpl();
			parcelType = parcelTypeDao.getParcelType(parcelName);
			QuotationDao quotationDao = new QuotationDaoImpl();
			Quotation quotation = quotationDao.getQuotation(distance);
			if (quotation != null) {
				float cost = (weight * parcelType.getPrice()) + (distance * quotation.getPrice());
				request.setAttribute("packages", packages);
				request.setAttribute("cost", cost);
				request.setAttribute("user_name_status", true);
				request.setAttribute("status", false);
				UserDao userDao = new UserDaoImpl();
				if (userDao.getUser(userName) != null) {
					request.setAttribute("user_name_status", false);
					request.setAttribute("status", true);
				}
				request.getRequestDispatcher("WEB-INF/admin/package-registration.jsp").forward(request, response);
			}

			else {
				request.setAttribute("distance_status", true);
				request.setAttribute("packages", packages);
				request.getRequestDispatcher("WEB-INF/admin/package-registration.jsp").forward(request, response);
			}
		} else if (urlMap.equals("/PackageSave")) {
			PackageDao packageDao = new PackageDaoImpl();
			Package packages = new Package();
			packages.setPackageId(Integer.parseInt(request.getParameter("packageId")));
			WareHouse wareHouse = new WareHouse();
			wareHouse.setLocation(request.getParameter("location"));
			packages.setWarehouseLocation(wareHouse);
			packages.setStatus(request.getParameter("status"));
			packageDao.updatePackage(packages);
			request.setAttribute("package_status", true);
			List<Package> packageList = new ArrayList<Package>();
			packageList = packageDao.getAllPackage();
			request.setAttribute("packageDetails", packageList);
			request.getRequestDispatcher("WEB-INF/admin/view-package-details.jsp").forward(request, response);
		} else if (urlMap.equals("/AddNewPackage")) {
			Package packages = new Package();
			User user = new User();
			user.setUserName(request.getParameter("userName"));
			packages.setUserName(user);
			ParcelType parcelType = new ParcelType();
			parcelType.setParcelName(request.getParameter("parcelType"));
			packages.setParcelType(parcelType);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = format.parse(request.getParameter("bookDate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			packages.setBookDate(date);
			packages.setPackageWeight(Float.parseFloat(request.getParameter("packageWeight")));
			packages.setDistance(Integer.parseInt(request.getParameter("distance")));
			packages.setSenderAddress(request.getParameter("senderAddress"));
			packages.setReceiverAddress(request.getParameter("receiverAddress"));
			WareHouse wareHouse = new WareHouse();
			wareHouse.setLocation(request.getParameter("location"));
			packages.setWarehouseLocation(wareHouse);
			packages.setStatus(request.getParameter("status"));
			packages.setCost(Float.parseFloat(request.getParameter("cost")));
			PackageDao packageDao = new PackageDaoImpl();
			packageDao.createPackage(packages);
			request.setAttribute("pa_id", packages.getPackageId());
			request.setAttribute("create_status", true);
			request.getRequestDispatcher("WEB-INF/admin/package-registration.jsp").forward(request, response);
		} else if (urlMap.equals("/UpdatePolicy")) {
			PolicyDao policyDao = new PolicyDaoImpl();
			Policy policy = new Policy();
			policy.setPolicyName(request.getParameter("policy"));
			if (policyDao.updatePolicy(policy)) {
				policy = policyDao.getPolicy();
				request.setAttribute("policy", policy);
				request.setAttribute("status", true);
				request.getRequestDispatcher("WEB-INF/admin/policy-edit.jsp").forward(request, response);
			} else {
				policy = policyDao.getPolicy();
				request.setAttribute("policy", policy);
				request.setAttribute("status", false);
				request.getRequestDispatcher("WEB-INF/admin/policy-edit.jsp").forward(request, response);
			}
		} else if (urlMap.equals("/QuotationSave")) {
			QuotationDao quoataionDao = new QuotationDaoImpl();
			try {
				String quotationIdString = request.getParameter("quotationId");
				int distance = Integer.parseInt(request.getParameter("distance"));
				float charges = Float.parseFloat(request.getParameter("cost"));
				if (quotationIdString == "" || quotationIdString == null) {
					Quotation quotation = new Quotation();
					quotation.setDistance(distance);
					quotation.setPrice(charges);
					if (quoataionDao.createQuotation(quotation)) {
						request.setAttribute("success", true);
						request.getRequestDispatcher("WEB-INF/admin/quotation-update.jsp").forward(request, response);
					} else {
						request.setAttribute("failure", true);
						request.getRequestDispatcher("WEB-INF/admin/quotation-update.jsp").forward(request, response);
					}
				} else {
					int quotationId = Integer.parseInt(quotationIdString);
					Quotation quotation = new Quotation(quotationId, distance, charges);
					if (quoataionDao.updateQuotation(quotation)) {
						request.setAttribute("success", true);
						request.getRequestDispatcher("WEB-INF/admin/quotation-update.jsp").forward(request, response);
					} else {
						request.setAttribute("failure", true);
						request.getRequestDispatcher("WEB-INF/admin/quotation-update.jsp").forward(request, response);
					}
				}
			} catch (RuntimeException e) {
				request.setAttribute("success", false);
				request.getRequestDispatcher("WEB-INF/admin/quotation-update.jsp").forward(request, response);
			}
		}
		if (urlMap.equals("/UserRegistration")) {
			String url = null;
			User user = new User();
			UserDao userDao = new UserDaoImpl();
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setGender(request.getParameter("gender"));
			user.setEmail(request.getParameter("email"));
			user.setContactNumber(Long.parseLong(request.getParameter("contact")));
			user.setUserName(request.getParameter("userId"));
			user.setPassword(request.getParameter("enterPassword"));
			user.setActive(request.getParameter("active"));
			try {
				if (userDao.createUser(user)) {
					request.setAttribute("registrationStatus", true);
					request.setAttribute("loginType", "u");
					url = "WEB-INF/super-admin/login-page.jsp";
				}
			} catch (UserNameNotAvailableException e) {
				request.setAttribute("userNameAvailableStatus", true);
				request.setAttribute("loginType", "u");
				url = "WEB-INF/user/user-registration.jsp";
			}
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}

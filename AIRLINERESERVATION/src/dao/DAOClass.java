package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import configuration.Config;
import model.BookTickets;
import model.FlightList;
import model.LoginUser;
import model.RegisterUser;

public class DAOClass {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean checkUserLogin(LoginUser user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Create Connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/5622db?useSSL=false", "root", "root");

			PreparedStatement ps = con.prepareStatement("select name from user_details where name=? and pass=?");
			ps.setString(1, user.getUname());
			ps.setString(2, user.getPass());

			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertUserService(RegisterUser reg) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/5622db?useSSL=false", "root", "root");

			int maxId = 0;
			ps = con.prepareStatement("select max(id) from user_details");
			rs = ps.executeQuery();
			if (rs.next()) {
				maxId = rs.getInt(1) + 1;
			} else {
				maxId = 0;
			}

			int data = 0;
			ps = con.prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, maxId);
			ps.setString(2, reg.getName());
			ps.setInt(3, reg.getAge());
			ps.setString(4, reg.getEmail());
			ps.setString(5, reg.getContact());
			ps.setString(6, reg.getPass());
			ps.setString(7, reg.getIdType());
			ps.setString(8, reg.getIdNum());

			data = ps.executeUpdate();
			if (data > 0) {
				System.out.println(
				   "--------------------------------------- REGISTERED SUCCESSFULLY :) --------------------------------");
				return true;

			}
			System.out.println(
					"--------------------------------- REGISTERED UNSUCCESSFULL :( ---------------------------------------");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public List<FlightList> searchFlight(String from, String to) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/5622db?useSSL=false", "root", "root");

			FlightList flights;
			List<FlightList> li = new ArrayList<>();
			ps = con.prepareStatement("select * from plane_details where flight_from like ? and flight_to like ?");
			ps.setString(1, "%" + from + "%");
			ps.setString(2, "%" + to + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				flights = new FlightList();
				flights.setId(rs.getInt("fid"));
				flights.setFlightCode(rs.getString("flight_code"));
				flights.setFrom(rs.getString("flight_from"));
				flights.setTo(rs.getString("flight_to"));
				flights.setDeparture(rs.getString("departure"));
				flights.setArrival(rs.getString("arrival"));
				flights.setPrice(rs.getInt("price"));
				flights.setAvailTickets(rs.getInt("avail_tickets"));
				li.add(flights);

			}
			return li;

		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean ticketBooking(BookTickets tickets) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/5622db?useSSL=false", "root", "root");

			int maxId = 0;
			int price = 0;
			int avail = 0;
			ps = con.prepareStatement("select max(tick_id) from booked_tickets");
			rs = ps.executeQuery();
			if (rs.next()) {
				maxId = rs.getInt(1) + 1;
			} else {
				maxId = 1;
			}

			ps = con.prepareStatement("select price,avail_tickets from plane_details");
			rs = ps.executeQuery();
			if (rs.next()) {
				price = rs.getInt(1);
				avail = rs.getInt(2);
			} else {
				throw new Exception();
			}

			int data = 0;
			ps = con.prepareStatement("insert into booked_tickets values(?,?,?,?,?,?)");
			ps.setInt(1, maxId);
			ps.setString(2, tickets.getFlight_code());
			ps.setString(3, tickets.getUname());
			ps.setInt(4, tickets.getBookedTickets());
			ps.setInt(5, (tickets.getBookedTickets() * price));
			ps.setString(6, String.valueOf(new Date()));

			data = ps.executeUpdate();
			if (data > 0) {
				ps = con.prepareStatement("update plane_details set avail_tickets = ? where flight_code = ?");
				ps.setInt(1, (avail - tickets.getBookedTickets()));
				ps.setString(2, tickets.getFlight_code());
				int i = ps.executeUpdate();
				if (i > 0) {
					return true;
				}

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<BookTickets> getAllTickets() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/5622db?useSSL=false", "root", "root");

			BookTickets tickets;
			List<BookTickets> li = new ArrayList<>();
			ps = con.prepareStatement("select * from booked_tickets ");
			rs = ps.executeQuery();
			while (rs.next()) {
				tickets = new BookTickets();
				tickets.setTick_id(rs.getInt("tick_id"));
				tickets.setFlight_code(rs.getString("flight_code"));
				tickets.setUname(rs.getString("uname"));
				tickets.setAmount(rs.getInt("amount"));
				tickets.setBookedDate(rs.getString("booked_date"));
				tickets.setBookedTickets(rs.getInt("ticket_count"));
				li.add(tickets);

			}
			return li;

		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean CancelService(int tickId) {
		Scanner sc = new Scanner(System.in);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/5622db?useSSL=false", "root", "root");

				String fc = null;
				int tc = 0;
				ps = connection
						.prepareStatement("select flight_code, ticket_count from booked_tickets where tick_id=?");
				ps.setInt(1, tickId);
				rs = ps.executeQuery();
				if (rs.next()) {
					fc = rs.getString(1);
					tc = rs.getInt(2);
				} else {
					System.out.println("ID Not Exists");
				}
				String sql1 = "DELETE FROM booked_tickets WHERE tick_id=?";
				ps = connection.prepareStatement(sql1);
				ps.setInt(1, tickId);

				int rowsDeleted = ps.executeUpdate();
				if (rowsDeleted > 0) {
//					System.out.println("A id was deleted successfully!\n");
					int at = 0;
					ps = connection.prepareStatement("select avail_tickets from plane_details where flight_code=?");
					ps.setString(1, fc);
					rs = ps.executeQuery();
					if (rs.next()) {
						at = rs.getInt(1);
					} else {
						System.out.println("Flight code does Not Exists");
					}

					// Update the record
					String sql2 = "Update plane_details set avail_tickets = ? where flight_code=?";
					ps = connection.prepareStatement(sql2);
					ps.setInt(1, (tc + at));
					ps.setString(2, fc);
					int rowUpdate = ps.executeUpdate();
					if (rowUpdate > 0) {
						return true;
//						System.out.println("\nRecord updated successfully!!\n");
					}
				}
			}

			catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return false;
	}

}

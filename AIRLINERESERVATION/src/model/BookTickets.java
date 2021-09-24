package model;

import java.util.Date;

public class BookTickets {

	private int tick_id;
	private String flight_code;
	private String uname;
	private int bookedTickets;
	private int amount;
	private String bookedDate;

	public int getTick_id() {
		return tick_id;
	}

	public void setTick_id(int tick_id) {
		this.tick_id = tick_id;
	}

	public String getFlight_code() {
		return flight_code;
	}

	public void setFlight_code(String flight_code) {
		this.flight_code = flight_code;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}

	public int getBookedTickets() {
		return bookedTickets;
	}

	public void setBookedTickets(int bookedTickets) {
		this.bookedTickets = bookedTickets;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
}

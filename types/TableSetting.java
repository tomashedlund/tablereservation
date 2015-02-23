package types;
import java.util.Date;

public class TableSetting {
	private Plate plate;
	private Seat seating;
	private Date date;
	
	public TableSetting(Date date) {
		this.plate = new Plate();
		this.seating = new Seat();
		this.date = date;
	}
}
package genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaUtility {

	public String getRequireDate(int days) {
		
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(cal.DAY_OF_MONTH, days);
		String requireDate = sim.format(cal.getTime());
		
		return requireDate;
	}
	
	public String getCurrentDate() {
		
		Date d = new Date();
		String date = d.toString().replace(" ","_".replace(":","_"));
		
		return date;
	}
}

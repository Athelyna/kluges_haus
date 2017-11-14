package controller;
import java.util.*;

import models.AirCon;
import models.Blind;

import java.text.SimpleDateFormat;

public class MainController {

	public static void main(String [] args) throws Exception{
		//boolean temp;
		//boolean windStrength;
		//Declare GUI sensor
		GUI gui = new GUI();
		gui.createGUI();
		do {
		Thread.sleep(5000);
		gui.receiveUpdate();
		}while(true);
		//To store the current time, format is HH:mm
		//String[] time;

		//To store the time in a form of either morning, noon or night
		//String day;

		//Created to randomize whether it is windy or cold or not windy or hot outside
		/*boolean [] arr = {false,true};
		Random random = new Random();

		//Forming a loop
		for(int i = 0; i<7;i++) {

			int selectWind = random.nextInt(arr.length);
			int selectTemp = random.nextInt(arr.length);
			windStrength = arr[selectWind];
			temp = arr[selectTemp];

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Date d = new Date();
			//morning 0500-1159
			//noon 1200 - 1759
			//night 1800-0459
			String testTime = sdf.format(d);
			time = testTime.split(":");
			int hour = Integer.parseInt(time[0]);
			int minute = Integer.parseInt(time[1]);

			//Condition to know whether the hour part of the time shows morning, noon or night
			if(isMorning(hour)) {
				day = "morning";
			}else if(isNoon(hour)) {
				day = "noon";
			}else {
				day = "night";
			}

			//To print the current time, format HH:mm
			System.out.println(hour + ":" + minute);
			//String time = "night";

			Blind blind = new Blind(temp,windStrength,day);
			AirCon ac = new AirCon(temp,windStrength,day);

			//Declare controller
			BlindController bc = new BlindController(blind);
			AirConController acc = new AirConController(ac);

			//Print out the actions
			System.out.println("Is High Temp : " + temp + " Is Windy : " + windStrength + " Time : " + day);
			System.out.println("Blind: " + bc.getAction());
			System.out.println("AC: " + acc.getAction() + "\n\n");
			
			Thread.sleep(5000);
			hour++;
		}*/
	}

	/*private static boolean isNoon(int hour) {
		return hour >= 12 && hour < 19;
	}

	private static boolean isMorning(int hour) {
		return hour >= 5 && hour < 12;
	}*/
}

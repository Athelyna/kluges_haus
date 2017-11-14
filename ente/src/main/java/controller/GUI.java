package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.AirCon;
import models.Blind;

public class GUI implements ActionListener, ChangeListener{
	
	//HOUR values for JSlider
	static final int HOUR_MIN = 0;
	static final int HOUR_MAX = 23;
	static final int HOUR_INIT = 9; //initial hour
	
	int hour;
	int minute;
	
	Blind blind;
	AirCon ac;

	//Declare controller
	BlindController bc;
	AirConController acc;
	
	//Declare textarea, buttons and slider
	JTextArea textArea;
	JRadioButton tempHigh;
	JRadioButton tempLow;
	JRadioButton windStrong;
	JRadioButton windWeak;
	JSlider hours;
	String time;
	Date d;
	SimpleDateFormat sdf;
	
	public void receiveUpdate() {
		
		//morning 0500-1159
		//noon 1200 - 1759
		//night 1800-0459

		if (minute == 59) {
			minute = 0;
			if (hour == 23) {
				// midnight
				hour = 1;
			} else {
				// normal hour with 59 on minute
				hour = hour + 2;
			}
		} else {
			minute++;
			if (hour == 23) {
				hour = 0;
			} else {
				hour++;
			}
		}
		//Randomizing the temperature and the wind condition
		boolean [] arr = {false,true};
		Random random = new Random();
		int selectWind = random.nextInt(arr.length);
		int selectTemp = random.nextInt(arr.length);
		
		String windStrength;
		boolean windStr = arr[selectWind];
		if(windStr){
			windStrength = "strong";
		}else {
			windStrength = "weak";
		}
		
		String temperature;
		boolean tempr = arr[selectTemp];
		if(tempr){
			temperature = "high";
		}else {
			temperature = "low";
		}
		
		String day = checkDay(hour);
		setController(tempr,windStr,day);
		
		//Printing initial result when GUI is opened
		//High temperature, windy and morning
		String s = String.format("%02d:%02d\n", hour, minute);
		textArea.setText(s);
		textArea.append("Temperature : " + temperature + ", Wind : " + windStrength + "\n");
		textArea.append("Blind: " + bc.getAction() + "\n");
		textArea.append("AC: " + acc.getAction() + "\n");
		
		System.out.printf("%02d:%02d\n", hour, minute);
		System.out.println("Temperature : " + temperature + ", Wind : " + windStrength + "\n");
		System.out.println("Blind: " + bc.getAction() + "\n");
		System.out.println("AC: " + acc.getAction() + "\n");
		
	}
	
	public GUI(){
		blind = new Blind(true,true,"morning");
		ac = new AirCon(true,true,"morning");
		d = new Date();
		sdf = new SimpleDateFormat("HH:mm");
		bc = new BlindController(blind);
		acc = new AirConController(ac);
		String testTime = sdf.format(d);
		String[] clock = testTime.split(":");
		hour = Integer.parseInt(clock[0]);
		minute = Integer.parseInt(clock[1]);
	}
	
	public void createGUI(){
		//Initialize frame and its layout
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		//Initialize panels
		JPanel temp = new JPanel();
		JPanel wind = new JPanel();
		JPanel time = new JPanel();
		
		JPanel result = new JPanel();
		textArea = new JTextArea(10,45);
		
		//Initialize labels
		JLabel tempLabel = new JLabel("Temperature: ");
		JLabel windLabel = new JLabel("Wind: ");
		JLabel timeLabel = new JLabel("Time(Hour): ");
		
		hours = new JSlider(JSlider.HORIZONTAL, HOUR_MIN, HOUR_MAX, HOUR_INIT);
		hours.setMajorTickSpacing(5);
		hours.setMinorTickSpacing(1);
		hours.setPaintTicks(true);
		hours.setPaintLabels(true);
		hours.addChangeListener(this);
		
		//Radio Buttons for wind strength
		windStrong = new JRadioButton("Strong");
		windStrong.setSelected(true);
		windStrong.addActionListener(this);
		windWeak = new JRadioButton("Weak");
		windWeak.addActionListener(this);
		
		//Radio Buttons for temperature
		tempHigh = new JRadioButton("High");
		tempHigh.setSelected(true);
		tempHigh.addActionListener(this);
		tempLow = new JRadioButton("Low");
		tempLow.addActionListener(this);
		
		//Button group for temperature radio buttons
		ButtonGroup tempButton = new ButtonGroup();
		tempButton.add(tempHigh);
		tempButton.add(tempLow);
		
		//Button group for wind strength radio buttons
		ButtonGroup windButton = new ButtonGroup();
		windButton.add(windStrong);
		windButton.add(windWeak);
		
		//Components added into JPanel temp 
		temp.add(tempLabel);
		temp.add(tempHigh);
		temp.add(tempLow);
		
		//Components added into JPanel wind
		wind.add(windLabel);
		wind.add(windStrong);
		wind.add(windWeak);
		
		//Components added into JPanel time
		time.add(timeLabel);
		time.add(hours);
		
		
		//Assigning system's time
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date d = new Date();
		//morning 0500-1159
		//noon 1200 - 1759
		//night 1800-0459
		String testTime = sdf.format(d);
		String[] clock = testTime.split(":");
		int hour = Integer.parseInt(clock[0]);
		int minute = Integer.parseInt(clock[1]);
		
		//Randomizing the temperature and the wind condition
		boolean [] arr = {false,true};
		Random random = new Random();
		int selectWind = random.nextInt(arr.length);
		int selectTemp = random.nextInt(arr.length);
		
		String windStrength;
		boolean windStr = arr[selectWind];
		if(windStr){
			windStrength = "strong";
		}else {
			windStrength = "weak";
		}
		
		String temperature;
		boolean tempr = arr[selectTemp];
		if(tempr){
			temperature = "high";
		}else {
			temperature = "low";
		}
		
		
		//Printing initial result when GUI is opened
		//High temperature, windy and morning
		String s = String.format("%02d:%02d\n", hour, minute);
		textArea.setText(s);
		textArea.append("Temperature : " + temperature + ", Wind : " + windStrength + "\n");
		textArea.append("Blind: " + bc.getAction() + "\n");
		textArea.append("AC: " + acc.getAction() + "\n");
		
		System.out.printf("%02d:%02d\n", hour, minute);
		System.out.println("Temperature : " + temperature + ", Wind : " + windStrength + "\n");
		System.out.println("Blind: " + bc.getAction() + "\n");
		System.out.println("AC: " + acc.getAction() + "\n");
		
		//Textarea is added into JPanel result
		result.add(textArea);
		
		//Components are added into JFrame
		frame.add(temp, BorderLayout.NORTH);
		frame.add(wind, BorderLayout.WEST);
		frame.add(time, BorderLayout.EAST);
		frame.add(result, BorderLayout.PAGE_END);
		
		//Setting JFrame (ex. title, size)
		frame.setTitle("Sensor GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(530, 450);
		frame.setVisible(true);
	}
	
	//If radio buttons are clicked
	public void actionPerformed(ActionEvent e){
		int time = hours.getValue();
		String clock = Integer.toString(time);
		
		boolean temp;
		String temperature;
		boolean windStr;
		String windStrength;
		
		if(isTwoDigits(clock)) {
			clock = clock + ":00";
		}else {
			clock = "0" + clock + ":00";
		}
		
		if(tempHigh.isSelected()){
			temp = true;
			temperature = "high";
		}else {
			temp = false;
			temperature = "low";
		}

		if(windStrong.isSelected()){
			windStr = true;
			windStrength = "strong";
		}else {
			windStr = false;
			windStrength = "weak";
		}
		
		String day = checkDay(time);
		setController(temp,windStr,day);
		
		String s = String.format("%02d:%02d\n", hour, minute);
		textArea.setText(s);
		textArea.append("Temperature : " + temperature + ", Wind : " + windStrength + "\n");
		textArea.append("Blind: " + bc.getAction() + "\n");
		textArea.append("AC: " + acc.getAction() + "\n");
		
		System.out.printf("%02d:%02d\n", hour, minute);
		System.out.println("Temperature : " + temperature + ", Wind : " + windStrength + "\n");
		System.out.println("Blind: " + bc.getAction() + "\n");
		System.out.println("AC: " + acc.getAction() + "\n");
	}

	//If the slider is moved
	public void stateChanged(ChangeEvent arg0) {
		int time = hours.getValue();
		String clock = Integer.toString(time);

		boolean temp;
		String temperature;
		boolean windStr;
		String windStrength;
		
		if(isTwoDigits(clock)) {
			clock = clock + ":00";
		}else {
			clock = "0" + clock + ":00";
		}
		
		if(tempHigh.isSelected()){
			temp = true;
			temperature = "high";
		}else {
			temp = false;
			temperature = "low";
		}

		if(windStrong.isSelected()){
			windStr = true;
			windStrength = "strong";
		}else {
			windStr = false;
			windStrength = "weak";
		}
		
		String day = checkDay(time);
		setController(temp,windStr,day);
		textArea.setText(clock + "\n");
		textArea.append("Temperature : " + temperature + ", Wind : " + windStrength + "\n");
		textArea.append("Blind: " + bc.getAction() + "\n");
		textArea.append("AC: " + acc.getAction() + "\n");
	}

	private boolean isTwoDigits(String clock) {
		return clock.length()>1;
	}
	
	//The hour got from slider and check whether it is morning, noon or night
	private String checkDay(int hour){
		if(isMorning(hour)) {
			return "morning";
		}else if(isNoon(hour)) {
			return "noon";
		}else {
			return "night";
		}
	}
	
	private boolean isNoon(int hour) {
		return hour >= 12 && hour < 19;
	}

	private boolean isMorning(int hour) {
		return hour >= 5 && hour < 12;
	}
	
	//Set controller after buttons are clicked or slider is moved
	private void setController(boolean temperature, boolean windStrength, String day){
		blind.setTemp(temperature);
		blind.setTime(day);
		blind.setWindStrength(windStrength);
		
		ac.setTemp(temperature);
		ac.setTime(day);
		ac.setWindStrength(windStrength);

		bc.setBlindSensor(blind);
		acc.setAc(ac);
	}
}

package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class TimeController {
	private int Hr = 0;
	private int Hr2 = 0;
	private int Min = 0;
	private int Min2 = 0;
	private int Sec = 0;
	private int Sec2 = 0;
	private String buttonType = null;
	private Timeline timeLine = new Timeline();
	private Timeline timeLine2 = new Timeline();
	private boolean isRunning = false;
	private boolean isRunning2 = false;
	private boolean missFire;
	private boolean missFire2;
	@FXML
    private TextField hr;

    @FXML
    private TextField min;

    @FXML
    private TextField sec;

    @FXML
    private Button start;
    
    @FXML
    private Button stop;
    
    @FXML
    private Button reset;
    //timer 2
    @FXML
    private TextField hr1;

    @FXML
    private TextField min1;

    @FXML
    private TextField sec1;
    
    @FXML
    private Button start1;

    @FXML
    private Button stop1;

    @FXML
    private Button reset1;

    @FXML//When user presses start timer the timer is started and necessary objects get updated as time passes
    void startTimer(ActionEvent event){
    	try{
    		if((event.getSource() == start) && (hr.getText().length() <=2) && (min.getText().length() <=2) && (sec.getText().length() <=2)){
    			buttonType = "start";
    			Hr = Integer.parseInt(hr.getText()); //gets appropriate values from what user inputted in specific places
    			Min = Integer.parseInt(min.getText());
    			Sec = Integer.parseInt(sec.getText());
    			hr.setEditable(false);
    			min.setEditable(false);
    			sec.setEditable(false);
    			missFire = Hr == 0 && Min == 0 && Sec == 0;
    		}
    		else if((event.getSource() == start1) && (hr1.getText().length() <=2) && (min1.getText().length() <=2) && (sec1.getText().length() <=2)){
    			buttonType = "start1";
    			Hr2 = Integer.parseInt(hr1.getText());
    			Min2 = Integer.parseInt(min1.getText());
    			Sec2 = Integer.parseInt(sec1.getText());
    			hr1.setEditable(false);
    			min1.setEditable(false);
    			sec1.setEditable(false);
    			missFire2 = Hr2 == 0 && Min2 == 0 && Sec2 == 0;
    		}
    		else
    			throw new Exception();
    	} catch(Exception e){
			missFire = true;
			missFire2 = true;
    		if(event.getSource() == start){
    			hr.setText("00");
        		min.setText("00");
        		sec.setText("00");
    		}
    		else{
    			hr1.setText("00");
        		min1.setText("00");
        		sec1.setText("00");
    		}	
    	}
    	if(isRunning == false && (event.getSource() == start)){
    		KeyFrame time = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
    			public void handle(ActionEvent event){
    				boolean isTimerDone = Hr == 0 && Sec == 0 && Min == 0;
    				if(isTimerDone){
    					timeLine.stop();
    					Sec = 0;
    					Min = 0;
    					Hr = 0;
    					hr.setEditable(true);
    	    			min.setEditable(true);
    	    			sec.setEditable(true);
    					if (!missFire){ //Timer plays alarm when alarm is finished
    						String pathAlarm = "FireAlarm.mp3";
    						Media alarm = new Media(new File(pathAlarm).toURI().toString());
    						MediaPlayer alarmPlay = new MediaPlayer(alarm);
	    					alarmPlay.play();
    					}
    				}
    				else{
	    				Sec--;
	    				boolean isSecondsZero = Sec == -1;
	    				if(isSecondsZero){
	    					Min--;
	    					Sec = 59;	
	    				}
	    				boolean isMinutesZero = Min == -1;
	    				if(isMinutesZero){
	    					Hr--;
	    					Min = 59;
						}
    				}	
    				display();
				}
    		});
			timeLine.setCycleCount(timeLine.INDEFINITE);
			timeLine.getKeyFrames().add(time);
			timeLine.playFromStart();
			isRunning = true;
    	}
    	else
    		timeLine.play();
    	if(isRunning2 == false && (event.getSource() == start1)){
    		KeyFrame time2 = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
    			public void handle(ActionEvent event){
    				boolean isTimerDone = Hr2 == 0 && Sec2 == 0 && Min2 == 0;
    				if(isTimerDone){
    					timeLine2.stop();
    					Sec2 = 0;
    					Min2 = 0;
    					Hr2 = 0;
    					hr1.setEditable(true);
    	    			min1.setEditable(true);
    	    			sec1.setEditable(true);
    					if (!missFire2){
    						String pathAlarm = "FireAlarm.mp3";
    						Media alarm = new Media(new File(pathAlarm).toURI().toString());
    						MediaPlayer alarmPlay = new MediaPlayer(alarm);
	    					alarmPlay.play();
    					}
    				}
    				else{
	    				Sec2--;
	    				boolean isSecondsZero = Sec2 == -1;
	    				if(isSecondsZero){
	    					Min2--;
	    					Sec2 = 59;	
	    				}
	    				boolean isMinutesZero = Min2 == -1;
	    				if(isMinutesZero){
	    					Hr2--;
	    					Min2 = 59;
						}
    				}	
    				display2();
				}
    		});
			timeLine2.setCycleCount(timeLine.INDEFINITE);
			timeLine2.getKeyFrames().add(time2);
			timeLine2.playFromStart();
			isRunning2 = true;
    	}
    	else
    		timeLine2.play();
    }
    @FXML //When user presses stop timer is stopped
    void stopTimer(ActionEvent event){
    	if (event.getSource() == stop)
    		timeLine.pause();
    	else
    		timeLine2.pause();
    }
    @FXML //When user pressses reset all values are reset
    void resetTimer(ActionEvent event){
    	if(event.getSource() == reset){
    		timeLine.stop();
    		hr.setText("00");
    		min.setText("00");
    		sec.setText("00");
    		hr.setEditable(true);
			min.setEditable(true);
			sec.setEditable(true);
			Sec = 0;
			Min = 0;
			Hr = 0;
    	}
    	else{
    		timeLine2.stop();
    		hr1.setText("00");
    		min1.setText("00");
    		sec1.setText("00");
    		hr1.setEditable(true);
			min1.setEditable(true);
			sec1.setEditable(true);
			Sec2 = 0;
			Min2 = 0;
			Hr2 = 0;
    	}
    	
    	
    }
    
    private void display(){
		if (Sec <= 0)
			sec.setText("00");
		else if (Sec >= 1 && Sec < 10)
			sec.setText("0" + Integer.toString(Sec));
		else
			sec.setText(Integer.toString(Sec));
		if(Min <= 0)
			min.setText("00");
		else if (Min >= 1 && Min < 10)
			min.setText("0" + Integer.toString(Min));
		else
			min.setText(Integer.toString(Min));
		if(Hr <= 0)
			hr.setText("00");
		else if (Hr >= 1 && Hr < 10)
			hr.setText("0" + Integer.toString(Hr));
		else
			hr.setText(Integer.toString(Hr));
    }
    private void display2(){
		if (Sec2 <= 0)
			sec1.setText("00");
		else if (Sec2 >= 1 && Sec2 < 10)
			sec1.setText("0" + Integer.toString(Sec2));
		else
			sec1.setText(Integer.toString(Sec2));
		if(Min2 <= 0)
			min1.setText("00");
		else if (Min2 >= 1 && Min2 < 10)
			min1.setText("0" + Integer.toString(Min2));
		else
			min1.setText(Integer.toString(Min2));
		if(Hr2 <= 0)
			hr1.setText("00");
		else if (Hr2 >= 1 && Hr2 < 10)
			hr1.setText("0" + Integer.toString(Hr2));
		else
			hr1.setText(Integer.toString(Hr2));
    }
    

    
    @FXML
    private void initialize(){
    	assert hr != null : "hr not injected: check FXML file timerGUI.fxml";
    	assert min != null : "min not injected: check FXML file timerGUI.fxml";
    	assert sec != null : "sec not injected: check FXML file timerGUI.fxml";
    	assert hr1 != null : "hr1 not injected: check FXML file timerGUI.fxml";
    	assert min1 != null : "min1 not injected: check FXML file timerGUI.fxml";
    	assert sec1 != null : "sec1 not injected: check FXML file timerGUI.fxml";
    	assert start != null : "start not injected: check FXML file timerGUI.fxml";
    	assert stop != null : "stop not injected: check FXML file timerGUI.fxml";
    	assert reset != null : "reset not injected: check FXML file timerGUI.fxml";
    	assert start1 != null : "start1 not injected: check FXML file timerGUI.fxml";
    	assert stop1 != null : "stop1 not injected: check FXML file timerGUI.fxml";
    	assert reset1 != null : "reset1 not injected: check FXML file timerGUI.fxml";
    	
    }

}



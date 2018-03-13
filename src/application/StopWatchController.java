package application;


import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class StopWatchController {
	private long currentTime = 0;
	private boolean isRunning = false;
	private Timeline timeLine = new Timeline();
	@FXML
    private Label minLabel;
	@FXML
    private Label secLabel;
	@FXML
	private Label milLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button resetButton;
    
       
    
    @FXML
    void displayWatchTime(ActionEvent event) {

    }

    @FXML
    void start(ActionEvent event) {
    	if(isRunning == false){
	    	timeLine.setCycleCount(timeLine.INDEFINITE);
	    	timeLine.getKeyFrames().add(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>(){
	    		public void handle(ActionEvent event){
	    			currentTime ++;
	    			milLabel.setText(String.format("%02d", currentTime % 60));
	    			secLabel.setText(String.format("%02d",(currentTime/1000) % 60));
	    			minLabel.setText(String.format("%02d",(currentTime/(1000*60)) % 60));
	    		}
	    	}));
	    	timeLine.playFromStart();
	    	isRunning = true;
    	}
    	else{
    		timeLine.play();
    	}
    }

    @FXML
    void stop(ActionEvent event) {
    	timeLine.pause();
    }

    @FXML
    void reset(ActionEvent event) {
    	timeLine.stop();
    	milLabel.setText("00");
    	secLabel.setText("00");
    	minLabel.setText("00");
    	currentTime = 0;
    	isRunning = false;
    }

    @FXML
    private void initialize(){
    	assert milLabel != null : "milLabel not injected: check FXML file stopWatchGUI.fxml";
    	assert secLabel != null : "secLabel not injected: check FXML file stopWatchGUI.fxml";
    	assert minLabel != null : "minLabel not injected: check FXML file stopWatchGUI.fxml";
    	assert startButton != null : "startButton not injected: check FXML file stopWatchGUI.fxml";
    	assert stopButton != null : "stopButton not injected: check FXML file stopWatchGUI.fxml";
    	assert resetButton != null : "resetButton not injected: check FXML file stopWatchGUI.fxml";
    }
}



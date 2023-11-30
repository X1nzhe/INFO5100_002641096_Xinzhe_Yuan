/*
NEU INFO 5100, Final Project - Number Recognizer
Author:
    Name: Xinzhe Yuan, NUID:
    Name: Jia Xu, NUID:
Date: 13 Nov 2023
Version: 0.1

Reference
https://stackoverflow.com/questions/49343256/threads-in-javafx-not-on-fx-application-thread
https://stackoverflow.com/questions/34198190/javafx-progressbar-animation-or-transition
https://www.tensorflow.org/jvm
https://stackoverflow.com/questions/52472046/alerts-in-javafx-do-not-close-when-x-button-is-pressed


 */

package com.neu.info5100.numberrecognizer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

abstract class NumberRecognizerBase {
    NumberRecognizerModel model;

    protected abstract void setProgressBar(ProgressBar pb);
    protected abstract void saveAsPNG();

    public void cleanTmp(){
        String tmpPath = "./tmp";
        File tmp = new File(tmpPath);
        if(tmp.exists() && tmp.isDirectory()){
            File[] files = tmp.listFiles();
            if(files != null){
                for (File f : files){
                    f.delete();
                }
            }
        }
    }

    protected Image resizeImage(Image image, int width, int height){
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView.snapshot(null,null);
    }
    protected void saveImage(Image image, String path){
        File file = new File(path);
        try{
            ImageIO.write(SwingFXUtils.fromFXImage(image,null), "png",file);
        }catch(IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    protected Image invertImage(Image image){
        WritableImage invertedImage = new WritableImage((int) image.getWidth(), (int)image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = invertedImage.getPixelWriter();

        for (int x=0; x< image.getWidth();x++){
            for(int y=0; y<image.getHeight();y++){
                Color color = pixelReader.getColor(x,y);
                pixelWriter.setColor(x,y,color.invert());
            }
        }
        return invertedImage;
    }


    protected abstract void showResult(ModelLoader.Prediction p);

}

public class NumberRecognizerController extends NumberRecognizerBase{
        public NumberRecognizerController(){
            model = new NumberRecognizerModel();
            Runtime.getRuntime().addShutdownHook(new Thread(this::cleanTmp) );
        }

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;
    @FXML
    private ProgressBar progressBar;

    @FXML
    protected void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(15.0);

        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        Task<Void> loadModelTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                loadModel();
                return null;
            }
        };
        new Thread(loadModelTask).start();
    }
    protected static void initialAlert(){
        ButtonType okButton = new ButtonType("Ok");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"",okButton);
        alert.setTitle("CNN Digit Recognizer");
        alert.setHeaderText("Draw a digit from [0-9] to be recognized.");
        alert.showAndWait();
    }

    protected static void errorAlert(String errorMSG){
        ButtonType goodButton = new ButtonType("Good");
        Alert alert = new Alert(Alert.AlertType.ERROR,"",goodButton);
        alert.setTitle("CNN Digit Recognizer");
        alert.setHeaderText("Error!");
        alert.setContentText(errorMSG);
        alert.showAndWait();

    }

    @FXML
    private double lastX,lastY;
//    @FXML
//    private void onMousePressed(MouseEvent event){
//        lastX = event.getX();
//        lastY = event.getY();
//        gc.beginPath();
//        gc.moveTo(lastX,lastY);
//        gc.stroke();
//    }
//    @FXML
//    private void onMouseDragged(MouseEvent event){
//        double newX = event.getX();
//        double newY = event.getY();
//
//        gc.fillOval(newX,newY,15, 15);
//        lastY = newY;
//        lastX = newX;
//    }
@FXML
private void onMousePressed(MouseEvent event) {
    lastX = event.getX();
    lastY = event.getY();
    gc.beginPath();
    gc.moveTo(lastX, lastY);
}

    @FXML
    private void onMouseDragged(MouseEvent event) {
        double newX = event.getX();
        double newY = event.getY();

        //gc.setLineWidth(15);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);
        gc.lineTo(newX, newY);
        gc.stroke();

        lastY = newY;
        lastX = newX;
    }
    @FXML
    private void cleanCanvas(){
        gc.clearRect(0.0,0.0,canvas.getWidth(),canvas.getHeight());
    }

    @FXML
    private void submitBtnEvent() {
        progressBar.setVisible(true);
        Thread predictionThread = new Thread(()->{
            try{
                ModelLoader.Prediction prediction = recognizeNumber();
                Platform.runLater(()->{
                    progressBar.setVisible(false);
                    showResult(prediction);
                    cleanCanvas();
                });
            } catch (Exception e) {
               e.printStackTrace();
            }
        });

        setProgressBar(progressBar);
        predictionThread.start();

    }
    @Override
    public void showResult(ModelLoader.Prediction prediction){
        int mostPossibleNum = prediction.getMostPossibleNum();
        int secondPossibleNum = prediction.getSecondPossibleNum();
        float mostPossibleNumPossibility = prediction.getMostPossibleNumPossibility();
        float secondPossibleNumPossibility = prediction.getSecondPossibleNumPossibility();

        ButtonType goodButton = new ButtonType("Good");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"",goodButton);
        alert.setTitle("CNN Digit Recognizer");
        alert.setHeaderText("Result");
        DecimalFormat df = new DecimalFormat("#.##");
        alert.setContentText("The most likely digit is ["+ mostPossibleNum+"] with possibility "+df.format(mostPossibleNumPossibility)+"%.\n"+"The second most likely digit is ["+secondPossibleNum+"]  with possibility "+df.format(secondPossibleNumPossibility)+"%.");

        alert.showAndWait();
    }

    @Override
    public void saveAsPNG() {
        cleanTmp();
        Platform.runLater(()->{
            Image image = canvas.snapshot(null,null);
            Image resizedImage = resizeImage(image,28,28);
            Image invertedImage = invertImage(resizedImage);

            File tmpDir = new File("tmp");
            if(!tmpDir.exists()) {
                boolean created = tmpDir.mkdir();
                if(!created){
                    System.out.println("Error: Cannot create tmp dir");
                    return;
                }
            }
            saveImage(invertedImage,"./tmp/image.png");
        });

    }


    public synchronized ModelLoader.Prediction recognizeNumber() throws Exception {
        saveAsPNG();
        Path imagePath = Paths.get(ModelLoader.IMAGE_PATH);
        while(!Files.exists(imagePath)){
            Thread.sleep(100);
        }
        return model.predict();
    }

    public synchronized void loadModel() throws Exception {
        saveAsPNG();
        Path imagePath = Paths.get(ModelLoader.IMAGE_PATH);
        while(!Files.exists(imagePath)){
            Thread.sleep(100);
        }
        model.predict();
    }
    @Override
    public void setProgressBar(ProgressBar pb){
        pb.setProgress(0.3);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(pb.progressProperty(),1);
        KeyFrame keyFrame = new KeyFrame(new Duration(300),keyValue);
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
    }
}
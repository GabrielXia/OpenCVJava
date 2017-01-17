package colorTrackingFromFile;

import colorTrackingFromCamera.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ControllerFromFile {

    @FXML
    private Button startFromFile;

    @FXML
    private ImageView currentFrameFromFile;

    // a timer for acquiring the video stream
    private ScheduledExecutorService timer;

    // the OpenCV object that realizes the video capture
    private VideoCapture capture = new VideoCapture();

    // a flag to change the button behavior
    private boolean cameraActive = false;

    // the id of the camera to be used, the default value is 0
    //private static int cameraId = 0;

    //runs when the bottom pressed
    @FXML
    protected void startCamera() {

        if (!this.cameraActive) {
            // start the video capture
            //this.capture.open(cameraId);
            this.capture.open("/Users/XIAJin/Downloads/20170111_102817.mp4");
            //print fps
            System.out.println("frequence: "+capture.get(5));



            if (capture.isOpened()) {

                // grab a frame every 33 ms (30 frames/sec)
                Runnable frameGrabber = new Runnable() {

                    @Override
                    public void run() {
                        // effectively grab and process a single frame
                        //grab every frame takes about 10+ s
                        long startTime=System.currentTimeMillis();
                        Mat frame = grabFrame();
                        // convert and show the frame
                        long endTime=System.currentTimeMillis();
                        System.out.println(endTime-startTime);

                        //show the picture take 10+ms
                        Image imageToShow = Utils.mat2Image(frame);
                        updateImageView(currentFrameFromFile, imageToShow);
                    }
                };
                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);

                this.cameraActive=true;
                // update the button content
                this.startFromFile.setText("Stop Camera");
            } else {
                // log the error
                System.err.println("Impossible to open the camera connection...");
            }
        }
        else{

            // the camera is not active at this point
            this.cameraActive = false;
            // update again the button content
            this.startFromFile.setText("Start Camera");
            // stop the timer
            this.stopAcquisition();
        }
    }

    /**
     * Get a frame from the opened video stream (if any)
     *
     * @return the {@link Mat} to show
     */
    private Mat grabFrame()
    {
        // init everything
        Mat frame = new Mat();

        // check if the capture is open
        if (this.capture.isOpened())
        {
            try
            {
                // read the current frame
                this.capture.read(frame);

                //90 degree rotation, because in my pc the image by default is vertical
                //Core.flip(frame.t(), frame, -1);

                // if the frame is not empty, process it
                if (!frame.empty())
                {   //scalar（blue,green,yellow）
                    //Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
                    //Core.inRange(frame, new Scalar(0,0,0), new Scalar(255,255,255), frame);
                }

            }
            catch (Exception e)
            {
                // log the error
                System.err.println("Exception during the image elaboration: " + e);
            }
        }

        return frame;
    }

    private void updateImageView(ImageView view, Image image)
    {
        Utils.onFXThread(view.imageProperty(), image);
    }

    public void setClosed()
    {
        this.stopAcquisition();
    }

    /**
     * Stop the acquisition from the camera and release all the resources
     */
    private void stopAcquisition() {

        if (this.timer!=null && !this.timer.isShutdown())
        {
            try
            {
                // stop the timer
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e)
            {
                // log any exception
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }

        if (this.capture.isOpened())
        {
            // release the camera
            this.capture.release();
        }
    }

}

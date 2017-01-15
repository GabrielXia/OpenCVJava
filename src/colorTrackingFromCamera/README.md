#colorTrackingFromCamera
Code referenced from [opencv-java/getting-started](https://github.com/opencv-java/getting-started/tree/master/FXHelloCV/src/it/polito/elite/teaching/cv), 
Here is an example of tracking a red book :
![Example](/example.png)
##Files
###Main
There are some initializes jobs to do, don't forget to load openCV library

###Controller
Some functions to remember : 
- Runnable to run a thread
```
 Runnable  = new Runnable() {
        @Override 
            run(){}
     }
```
- OpenCV 
    - ```Core.flip(Mat scr,Mat dst, int)``` to rotate an image for 90,180,270 degrees
    - ```Core.inRange(Mat scr,Scala min, Scala max, scr)``` to filter certain colors
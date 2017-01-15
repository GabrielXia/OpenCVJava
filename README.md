# OpenCVJava 
There are some projects using OpenCV implements on Java
##Installation OpenCV Java
In OSX, I use Macports to install, it's simple (Xcode version needs to over 8): 
- In terminal, enter  `sudo port update`
- Enter `sudo port install opencv +java`
- If you need to know where it is installed, enter `port contents opencv | grep java`
 
 In an OpenCV Java project, you need:
- add openCV project library to this project, for Idea, it's `File >> Project Structure >> Libraries`
- If there is a UnsatisfiedLinkError, in osx you need :
    - Go to your openCV directory, for example `/usr/***/local/share/OpenCV/java`
    - You will see `libopencv_javaXXX.so`, `opencv-XXX.jar`, enter in terminal `ln -s libopencv_javaXXX.so libopencv_javaXXX.dylib`
    - Then add the path to your JVM arguments `-Djava.library.path=/usr/***/local/share/OpenCV/java`, for idea, it's `Run >> Edit Confighurations >> VMoptions`
    - Enjoy !!!
    
##Packages
Here are some little projects using OpenCV

###colorTrackingFromCamera
This project uses javafx, it will use the camera of your PC and create a window to show the video that selects certain color, the color selected is chosen by users
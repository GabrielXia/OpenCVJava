#colorTrackingFromFile
Just modify a little from package colorTrackingFromCamera,I want to do it a object oriented way, so I use main function in package colorTrackingFromCamera.

##File
###ControllerFromFile
The modifications from Controller from package colorTrackingFromCamera:
- Use ```VideoCapture.open(path);``` to open the video file
- Use ```VideoCapture.get(int);```to get parameters of input video 
- Use ```System.currentTimeMillis();```to record time

TO DO:
- Analyse of the video (find the position of ball)
- Find ways to speed up this analyse

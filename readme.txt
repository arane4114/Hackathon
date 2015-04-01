YADrone - Yet Another Drone Framework 
===============================================

YADrone is yet another open framework for controlling the AR.Drone 2. It features:

* Easy, yet powerful framework to control the drone
* Support for sensor readings and NavData, video and drone configuration
* A control center that visualizes sensor readings and allows to control the drone via keyboard
* Also supports Android (but no video)
* Framework is 100% pure Java and is easily extensible 

Of course, YADrone is still beta. There are some minor (known) bugs, but these mainly concern the control center and will be fixed someday soon. Nevertheless, you use this framework at your own risk. It is provided as is and YOU are responsible for any harm or drone damage. Please be careful !

YADrone is open: comments, bug reports and contributions are warmly welcome ! 

YADrone was initially based on the ARDroneForP5 project and added a NavData parser from the Javadrone project. Eventually, the code has been rebased on the lab-drone project a former extension of YADrone.
Credits and thanks go to all guys working on these projects, especially Shigeo Yoshida and Rian Wouters ! 

For further information, documentation and tutorials please see
http://vsis-www.informatik.uni-hamburg.de/projects/yadrone/index.html

Never worked with Github before ?
http://wiki.eclipse.org/EGit/User_Guide#Getting_Started

=======================================================================================

This repository contains the final project code for the Hackarizona team Honey Badgers. It aims to control the drone with the team motion SDK (separate installation required) and send video output to an oculus rift. Although JOVR is included in the bundle, we never got a chance to integrate it into the code. Due to time constraints it was never removed from the eclipse project.

Controls:
w,a,s,d control forward back left and right at a static altitude.
The up and down arrow keys control altitude.
The left and right keys spin the drone in place.
q clears the command queue and places a hover command in the queue, effectively stoping the drone.
e clears the command queue and orders the drone to land.
o tells the drone to takeoff. The drone will perform a calibration test on the ground, and it is best launched from a flat surface. It will also perform an in air calibration. After a spin the drone is ready for flight.
p tells the drone to land after it is does with all pending commands.
c pulls a leap motion frame. Having your hand above the middle of the box will cause an increase in altitude, and vive versa. Tilting the hand will cause the drone to fly in that direction. These to commands can be mixed. 
/ cycles the selected camera 

Known Bugs:
A time out error may interrupt the drone feed. There is no mid air fix for this.
There is a 1 in 4 chance of starting the video feed. If it doesn’t work, restart the code.

Credits:
Abhishek Rane
Bryce Hammod
Pierre Hicks
Sarah Schnoor
//Overall Method: Robot will start driving, and will stop when sensor senses white, and then Timer will getTime().

#include <Timer.h>
#include <LineShield.h>
#include <DriveShield.h>
//declare robot display timer
Timer timer;
LineShield lineReader;
DriveShield robot;
void setup() {
  Serial.begin(9600);
  Serial.println("ready");
  double robotSpeed = callibrate(71.65);
  Serial.println(robotSpeed); //units: cm/s
  testDistance(robotSpeed, 30.0);
}

void loop() {
  // put your main code here, to run repeatedly:
  //robot name: WALL-E JR.
} 

/**
 * Distance unit: centimeters
 */
double callibrate(double distance){
  timer.resetTime();
  while(!lineReader.isOnWhite()){
    robot.driveForward();
  }
  robot.stopDrive();
  return distance/(timer.getTime()/1000.0);
  // put your setup code here, to run once:
  //While loop runs until sensor hits white
  //driveFoward should not have any arguments because there will be a lag.
}

/**
 * This method test if the speed of the robot is correct by running the robot a certain distance.
 * Parameters: distance unit: centimeters
 */
void testDistance(double robotSpeed, double distance) {
  robot.driveForward(distance/robotSpeed);  //Drive the robot a certain time, which is (distance/robotSpeed)
}

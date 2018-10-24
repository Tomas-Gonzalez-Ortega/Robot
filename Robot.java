/*
 * BEGINNING COMMENTS
 * Classes to implement a Robot using Classes and Objects
 * @author Tomas Gonzalez
 * @version 2017-05-17 10:46pm
 * @see https://d2l.langara.bc.ca/d2l/le/calendar/88736/event/2546571/detailsview#2546571
 * Solves CPSC 1181 assignement 2
 * Instructor : Jeremy Hilliker
 * using RObotTester2
 */

// PACKAGE AND IMPORT STATEMENTS
import java.awt.geom.Point2D;

// CLASS AND INTERFACE DECLARATIONS

/**
 A class that implements the robot functions
 */

public class Robot
{
   // CONSTANTS
   private final static double MAX_RADIANS = Math.PI*2;
   private final static long SERIAL_LHS = 10*1000*1000*1000L;
   private final static int WARRANTY = 100 * 1000;
   // VARIABLES
   private final String model;
   private String name;
   private final double turnRate;
   private double direction = 0;
   private long serial;
   private static long lastDigits = 101;
   private double speed;
   private Point2D.Double position = new Point2D.Double(0,0);
   private double odometer = 0;
   private double warrantyLeft;

   /**
    * Contructs a robot with a model, a name and a turn rate
    * @param String aModel, the actual model of the robot
    * @param String aName, the actual name of the robot
    * @param double aTurnRate, turn rate of the robot
    */
   public Robot(String aModel, String aName, double aTurnRate)
   {
      model = aModel.trim();
      name = aName.trim();
      turnRate = aTurnRate;
   }

   /**
    * Retrieves the model of the robot
    * @return String model
    */
   public String getModel()
   {
      return model;
   }

   /**
    * Implements the serial number of the robot
    * @return long serial
    */
   public long getSerial()
   {
      serial = SERIAL_LHS * (model.hashCode());
      
      if (serial < 0)
         serial = Math.abs(serial);
      long remainder = serial % SERIAL_LHS;
      serial = (serial - remainder) + lastDigits;
      lastDigits++;
      
      return serial;
   }

   /**
    * Retrieves the name of the robot
    * @return String name
    */
   public String getName()
   {
      return name;
   }

   /**
    * Updates the name of the robot
    * @param String aName, the new name of the robot
    * @return String name
    */
   public void setName(String aName)
   {
      name = aName;
   }

   /**
    * Retrieves the direction of the robot
    * @return double direction
    */
   public double getDirection()
   {
      return direction;
   }

   /**
    * Retrieves the turn rate of the robot
    * @return double turn rate
    */
   public double getTurnRate()
   {
       return turnRate;
   }

   /**
    * Retrieves the speed of the robot
    * @return double speed
    */
   public double getSpeed()
   {
       return speed;
   }

   /**
    * Sets the speed of the robot
    * @param double aSpeed, the speed of the robot
    * @return double speed
    */
   public void setSpeed(double aSpeed)
   {
      speed = aSpeed;
   }

   /**
    * Turns right setting a new direction
    */
   public void turnRight()
   {
      if(direction - turnRate < 0)
         direction = direction + MAX_RADIANS - turnRate;
      else
         direction = direction - turnRate;
   }

   /**
    * Turns left setting a new direction
    */
   public void turnLeft()
   {
      if(direction + turnRate >= MAX_RADIANS)
         direction = direction + turnRate - MAX_RADIANS;
      else
         direction = direction + turnRate;
   }

   /**
    * Retrieves the position of the robot
    * @return Point2D.Double position
    */
   public Point2D.Double getPosition()
   {
      return position;
   }

   /**
    * Updates the position of the robot in terms of speed and direction
    * Updates the odometer whenever it moves
    */
   public void move()
   {
      double xPos = speed * Math.cos(direction);
      double yPos = speed * Math.sin(direction);
      double currentX = position.getX();
      double currentY = position.getY();
      double x = currentX + xPos;
      double y = currentY + yPos;
      
      position.setLocation(x, y);
      
      odometer += speed;
   }

   /**
    * Retrieves the current value of the odometer
    * @return double odometer
    */
   public double getOdometer()
   {
      return odometer;
   }

   /**
    * Retrieves the remaining warranty time from the robot
    * @return double warranty
    */
   public double getWarrantyLeft()
   {
      warrantyLeft = WARRANTY - odometer;
      return warrantyLeft;
   }

   /**
    * Checks whereas the robot still has warranty time
    * @return true if the robot is still under warranty
    * @return false if the warranty has already expired
    */
   public boolean isUnderWarranty()
   {
      if (warrantyLeft > 0)
         return true;
      else
         return false;
   }
}

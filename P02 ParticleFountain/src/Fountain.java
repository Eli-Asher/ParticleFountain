//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Files: (a list of all source files used by that program)
// Course: (course number, term, and year)
//
// Author: (Elijah Asher)
// Email: (asher3@wisc.edu)
// Lecturer's Name: (MOUNA AYARI BEN HADJ KACEM)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;

/**
 * Class Fountain is a fountain of object of type particle, showing said particles emerging from a
 * source, then falling down as they disappear
 * 
 * @author eliasher
 *
 */
public class Fountain {
  private static Random randGen;
  private static Particle[] particles;
  private static int positionX; // middle of the screen (left to right): 400
  private static int positionY; // middle of the screen (top to bottom): 300
  private static int startColor; // blue: Utility.color(23,141,235)
  private static int endColor; // lighter blue: Utility.color(23,200,255)
  private static int size;

  /**
   * Initializes instance variables and initializes new particle array tests removing and updating
   * particles
   */
  public static void setup() {

    System.out.println("testRemoveOldParticles(): " + testRemoveOldParticles());

    System.out.println("testUpdateParticle: " + testUpdateParticle());

    particles = new Particle[800];
    positionX = 400;
    positionY = 300;
    startColor = Utility.color(100, 200,  43);
    endColor = Utility.color(440, 240,  200);
    size = 100;



  }

  /**
   * Updates the particles within the array and calls necessary methods to modify them
   */
  public static void update() {
    Utility.background(Utility.color(235, 213, 186));

    createNewParticles(10);
    // updates Y velocity and coordinates of all particles in array
    for (int i = 0; i < particles.length; i++) {
      if (particles[i] != null) {

        updateParticle(i);
      }
    }
    removeOldParticles(80);
  }

  /**
   * Sets the origin of the fountain to the coordinates of the mouse when it is clicked
   * 
   * @param x x-coordinate of mouse
   * @param y y-coordinate of mouse
   */
  public static void mousePressed(int x, int y) {
    Fountain.positionY = y;
    Fountain.positionX = x;
  }

  /**
   * Creates a single particle at position (3,3) with velocity (-1,-2). Then checks whether calling
   * updateParticle() on this particle's index correctly results in changing its position to
   * (2,1.3).
   * 
   * @return true when no defect is found, and false otherwise
   */
 ////METHOD TEST MAY BE FAULTY
  private static boolean testUpdateParticle() {
    particles = new Particle[1];
    Particle p = new Particle(300, 300, 250, endColor);
    p.setVelocityX(-100);
    p.setVelocityY(-2);
    particles[0] = p;
    updateParticle(0);
    double posX = particles[0].getPositionX();
    double posY = particles[0].getPositionY();
    // checks to see if posY == 1.3 +/- .0001 or so, casts to int to make comparison easier
    int helperY = (int) (1.3 - posY);
    if (posX == 2.0 && helperY == 0) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * Calls removeOldParticles(6) on an array with three particles (two of which have ages over six
   * and another that does not). Then checks whether the old particles were removed and the young
   * particle was left alone.
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldParticles() {
    particles = new Particle[3];
    boolean testPassed = false;

    // randomly assigns two particles an age over 6
    Particle p1 = new Particle(3, 3, 20, 7);
    p1.setAge(7);
    particles[0] = p1;
    Particle p2 = new Particle(3, 3, 20, 7);
    p2.setAge(7);
    particles[1] = p2;

    // give particle a random age < 6
    Particle p3 = new Particle(3, 3, 20, 7);
    int p3Age = 5;
    p3.getAge();
    p3.setAge(p3Age);
    particles[2] = p3;
    removeOldParticles(6);
    if (particles[0] != null) {
      testPassed = false;
    } else if (particles[1] != null) {
      testPassed = false;
    } else if (particles[2] == null) {
      testPassed = false;
    } else {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Takes a screenshot when 'p' is pressed
   * 
   * @param p the character that activates the method
   */
  public static void keyPressed(char p) {
    if (p == 'p') {
      Utility.save("screenshot.png");
    }
  }

 
  /**
   * Removes all particles above maxAge
   * 
   * @param maxAge - the threshold age for a particle
   */
  private static void removeOldParticles(int maxAge) {
    for (int i = 0; i < particles.length; i++) {
      if (particles[i] != null && particles[i].getAge() > maxAge) {
        particles[i] = null;
      }
    }
  }// removeOldParticles

 
  /**
   * Updates any particles velocity and position within particles
   * 
   * @param index index of the particle to be updated
   */
  private static void updateParticle(int index) {
    // updates Particle's X velocity
    particles[index]
        .setPositionX(particles[index].getPositionX() + particles[index].getVelocityX());
    // adds gravity to Y velocity
    float gravity = (float) .1;
    particles[index].setVelocityY(particles[index].getVelocityY() + gravity);
    // updates Particle's Y velocity
    particles[index]
        .setPositionY(particles[index].getPositionY() + particles[index].getVelocityY());
    // draw particle
    Utility.circle(particles[index].getPositionX(), particles[index].getPositionY(),
        particles[index].getSize());
    // color particle
    Utility.fill(particles[index].getColor());
    // updates and checks age of particle
    particles[index].setAge(particles[index].getAge() + 1);
  }// updateParticle

  
  /**
   * Creates new particles to be added to particle array
   * 
   * @param newParts the number of new particles to be created
   */
  private static void createNewParticles(int newParts) {
    int count = newParts;
    for (int i = 0; i < particles.length; i++) {
      if (count == 0) {
        break;
      }
      if (particles[i] == null) {
        randGen = new Random();

        float posX = Fountain.positionX - 3 + randGen.nextFloat() * 6;
        float posY = Fountain.positionY - 3 + randGen.nextFloat() * 6;
        size = 4 + randGen.nextInt(8);
        int color = Utility.lerpColor(startColor, endColor, randGen.nextFloat());
        float velocityX = -1 + randGen.nextFloat() * 3;
        float velocityY = -5 - randGen.nextFloat() * 5;
        int trans = 32 + randGen.nextInt(96);
        particles[i] = new Particle(posX, posY, size, color);
        particles[i].setAge(randGen.nextInt(41));
        particles[i].setTransparency(trans);
        particles[i].setVelocityX(velocityX);
        particles[i].setVelocityY(velocityY);
        count--;
      }
    }
  }// createNewParticles

  
  /**
   * The main method calls Utility.runApplication to start the fountain
   * 
   * @param args
   */
  public static void main(String[] args) {

    Utility.runApplication();


  }// mainMethod
}// class

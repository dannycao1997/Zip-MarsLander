public class Vehicle {
    public static final int gravity = 100;
    /* The rate in which the spaceship descents in free fall (in ten seconds) */


    // Various end-of-game messages and status result codes.
    String dead = "\nCRASH!!\n\tThere were no survivors.\n\n";
    public static final int DEAD = -3;
    String crashed = "\nThe Starship crashed. Good luck getting back home. Elon is pissed.\n\n";
    public static final int CRASHED = -2;
    String emptyfuel = "\nThere is no fuel left. You're floating around like Major Tom.\n\n";
    public static final int EMPTYFUEL = -1;
    String success = "\nYou made it! Good job!\n\n";


    public static final int SUCCESS = 0;
    public static final int FLYING = 1;
    private int altitude;
    private int prevAltitude;
    private int velocity;
    private int fuel;
    private int burn;
    private int status;


    public Vehicle(int initialAltitude) {
        // initialize the altitude AND previous altitude to initialAltitude
        // this is initial vehicle setup

        this.altitude = initialAltitude;
        this.velocity = 1000;
        this.fuel = 12000;
    }

    public Vehicle() {}

    public String checkFinalStatus() {
        String s = "";
        if (this.altitude <= 0) {
            if (this.velocity > 10) {
                s = dead;

            }
            if (this.velocity < 10 && this.velocity > 3) {
                s = crashed;

            }
            if (this.velocity < 3) {
                s = success;

            }
        } else {
            if (this.altitude > 0) {
                s = emptyfuel;
            }
        }
        return s;
    }

    public int computeDeltaV(int burnAmount) {
        // return velocity + gravity - burn amount
        return velocity + gravity - burnAmount;
    }

    public void adjustForBurn(int burnAmount) {
        // set burn to burnamount requested
        // save previousAltitude with current Altitude
        // set new velocity to result of computeDeltaV function.
        // subtract speed from Altitude
        // subtract burn amount fuel used from tank
        if (fuel >= burnAmount) {
            fuel -= burnAmount;
        } else {
            burnAmount = fuel;
            fuel = 0;
        }
        int deltaV = computeDeltaV(burnAmount);
        velocity += deltaV;
        altitude -= velocity;

        if (altitude < 0) {
            altitude = 0;
        }
    }

    public boolean stillFlying() {
        // return true if altitude is positive
        return altitude > 0;
    }
    public boolean outOfFuel() {
        return fuel <= 0;
    }

    public DescentEvent getStatus(int tick) {
        return new DescentEvent(tick, velocity, fuel, altitude, status);
    }

}

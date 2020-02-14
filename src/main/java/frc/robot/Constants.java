package frc.robot;

public final class Constants {
    public static final class Ports
    {
        public static final int LEFT_MASTER_PORT = 4,
                                LEFT_SLAVE_PORT = 3,
                                RIGHT_MASTER_PORT = 2,
                                RIGHT_SLAVE_PORT = 1;
    }
    public static final class Wheel 
    {
        private static final double c = 381/25.0 * Math.PI / 100.0;
        public static final double WHEEL_RADIUS = c;
        public static final double UNITS_PER_ROUND = 4096.0;
    }
    public static final class Gyro
    {
        public static final double TurnP = 1,
                                   TurnI = 0,
                                   TurnD = 0,

                                   StableP = 1,
                                   StableI = 0.5,
                                   StableD = 0,
//Tolerence, should be increased/?
                                   PositionTolerence = 0,
                                   VelocityTolerence = 0;                           
    }
    public static final class Drive
    {
        public static final double angle_kP = 1/1024.0,
                                   angle_kI = 0.003,
                                   angle_kD = 0.01;
        public static final double angleErrorTolerence = 1;
        public static final double distance_kP = 0.7,
                                   distance_kI = 0.1,
                                   distance_kD = 0;
        public static final double distanceErrorTolerance = 0.1;
        
    }
    public static final class Turn
    {
        public static final double turn_kP = 1.0/180,
                                   turn_kI = 0,
                                   turn_kD = 0;
        public static final double angleErrorTolerence = 0.1;
    }
  
}
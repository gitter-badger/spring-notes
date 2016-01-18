package cz.sycha.notes.database;

/**
 * Database configuration class
 * @license MIT
 * @author Jakub Sycha
 */
public class DBConfig {
    // Keep this uncommented if you want to load settings from environment variables
    private static final String DB_HOST = System.getenv("SPRING_NOTE_DB_HOST");
    private static final int DB_PORT = Integer.parseInt(System.getenv("SPRING_NOTE_DB_PORT"));

    private static final String DB_USER = System.getenv("SPRING_NOTE_DB_USER");
    private static final String DB_PASS = System.getenv("SPRING_NOTE_DB_PASSWORD");

    private static final String DB_NAME = "Notes";
    private static final String DB_COLLECTION = "Notes";

    /*
        !! Uncomment this if you want to specify the config yourself !!
        private static final String DB_HOST = "127.0.0.1";
        private static final int DB_PORT = 27017;

        private static final String DB_USER = null;
        private static final String DB_PASS = null;
     */

    /**
     * Returns the database host
     *
     * @return String DB_HOST
     */
    public static String getDbHost() {
        return DB_HOST;
    }

    /**
     * Returns database port
     *
     * @return int DB_PORT
     */
    public static int getDbPort() {
        return DB_PORT;
    }

    /**
     * Returns Database User
     *
     * @return String DB_USER
     */
    public static String getDbUser() {
        return DB_USER;
    }

    /**
     * Returns Database password
     *
     * @return String DB_PASS
     */
    public static String getDbPass() {
        return DB_PASS;
    }

    /**
     * returns Database Name
     *
     * @return String DB-NAME
     */
    public static String getDbName() {
        return DB_NAME;
    }

    /**
     * Returns DB collection
     *
     * @return String DB_COLLECTION
     */
    public static String getDbCollection() {
        return DB_COLLECTION;
    }
}

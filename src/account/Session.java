package account;

/*
    Session allows global access of the user profile that is logged in.
 */
public class Session {
    private static Session session; // Singleton instance
    private static Profile profile; // The profile logged in

    public static Session getSession(){
        if(session == null){
            session = new Session();
        }
        return session;
    }

    public static void setProfile(Profile profile) {
        if(profile == null){
            Session.profile = profile;
        }
    }

    public static Profile getProfile(){
        return profile;
    }
}

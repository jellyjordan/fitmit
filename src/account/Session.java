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

    /*
        Binds a profile to the session, and syncs the profile
        with the current time which updates daily activities
     */
    public static void setProfile(Profile profile) {
        /*
            Users must log out and start a new session with
            a new profile in order to access another account
         */
        if(Session.profile == null){
            Session.profile = profile;
            Session.profile.syncProfile();
        }
    }

    /*
        Gives global access the profile which is binded to
        the session, which makes it easier for the controllers
        to make alterations
     */
    public static Profile getProfile(){
        return profile;
    }
}

package account;

import java.io.*;
import java.util.ArrayList;

/*
    Class is used for saving and loading the state of a
    user profile
 */
public class ProfileParser {

    /*
        Saves the profile to bytecode into the Profiles.ser file
        in the running directory
     */
    public static void saveProfile(Profile profile){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("Profiles.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(profile);
            objectOutputStream.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    /*
        Loads all the profiles stored in the saved file and returns
        the entire list
     */
    public static ArrayList<Profile> loadProfiles(){
        ArrayList<Profile> profiles = new ArrayList<Profile>();
        try{
            // Opens the stream
            FileInputStream fileInputStream = new FileInputStream("Profiles.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Keeps loading objects until we hit the end
            Object loadedProfile;
            while ( (loadedProfile = objectInputStream.readObject()) != null){
                Profile profile = (Profile) loadedProfile;
                profiles.add(profile);
            }
            objectInputStream.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return profiles;
    }
}

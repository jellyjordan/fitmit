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
            boolean newProfile = true;
            // Loads profiles
            ArrayList<Profile> profiles = loadProfiles();

            // Update profiles
            for(Profile loadedProfile : profiles){
                if(profile.getUserName().equals(loadedProfile.getUserName())){
                    newProfile = false;
                    loadedProfile = profile;
                    break;
                }
            }
            // Add new profile if it is not an updated profile
            if(newProfile){
                profiles.add(profile);
            }
            FileOutputStream fileOutputStream = new FileOutputStream("Profiles.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(profiles);
            objectOutputStream.close();
            fileOutputStream.close();
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
            File profileFile = new File("Profiles.ser");
            if(profileFile.exists()){
                FileInputStream fileInputStream = new FileInputStream("Profiles.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                // Loads the object expected to be arraylist of profiles
                Object loadedProfiles;
                loadedProfiles = objectInputStream.readObject();
                profiles = (ArrayList<Profile>) loadedProfiles;
                objectInputStream.close();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return profiles;
    }

    /*
        Fetches the specific profile with the matching username
     */
    public static Profile getProfile(String userName){
        ArrayList<Profile> profiles = loadProfiles();
        for(Profile profile : profiles){
            if(profile.getUserName().equals(userName)){
                return profile;
            }
        }
        return null;
    }

}

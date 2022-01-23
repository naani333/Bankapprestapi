package com.example.demo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Restservice {

    @Autowired
    UserdetailsRepository userdetailsRepository;

    public  void  saveUser(String user){

        Userdetails userdetails = new Userdetails();
        JsonObject jsonObject2 = new JsonParser().parse(user).getAsJsonObject();
        JsonObject jsonObject = jsonObject2.getAsJsonObject("userdetails");
        System.out.println(jsonObject);
        userdetails.setUsername(jsonObject.get("username").getAsString());
        userdetails.setFirstname(jsonObject.get("firstname").getAsString());
        userdetails.setLastname(jsonObject.get("lastname").getAsString());
        userdetails.setPassword(jsonObject.get("password").getAsString());
        userdetails.setEmail(jsonObject.get("email").getAsString());
        userdetails.setOccupation(jsonObject.get("occupation").getAsString());
        userdetails.setContact(jsonObject.get("contact").getAsInt());
        userdetailsRepository.save(userdetails);
    }

    public String getUser(String user){
        System.out.println(user);
        JsonObject jsonObject = new Gson().fromJson(user,JsonObject.class);

        String result = new Gson().toJson(userdetailsRepository.findById(jsonObject.get("propUser").getAsString()));
        return  result;
    }
    public String updateUser(String user){
        System.out.println(user);
        JsonObject jsonObject = new Gson().fromJson(user,JsonObject.class);
        Userdetails userdetails = new Gson().fromJson(jsonObject.get("editedUser"),Userdetails.class);
        Userdetails updatedUser= userdetailsRepository.save(userdetails);
        return  new Gson().toJson(userdetails);
    }

    public String validateUser(String user){
        System.out.println(user);
        JsonObject obj = new Gson().fromJson(user,JsonObject.class);
        JsonObject json = (JsonObject) obj.get("validateuser");
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();

        System.out.println("-----"+username + "-------" + password + "-------");
        String test= "hello";
        System.out.println("*****"+test);
        String expectedPassword = "";

        if(userdetailsRepository.findById(username).isPresent()){
             expectedPassword = userdetailsRepository.findById(username).get().getPassword();
        }else{
            return "Invalid Username";
        }
        if(expectedPassword.equals(password)){
            return "Success";
        }else{
            return "Invalid Password";
        }
    }
}
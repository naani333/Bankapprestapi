package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Accountservice {

    @Autowired
    AccountsRepository accountsRepository;

    public  void  saveAccount(String account){
        System.out.println(account);
        JsonObject jsonObject=new Gson().fromJson(account, JsonObject.class);
        Accounts accounts = new Accounts();
        accounts.setUser(jsonObject.getAsJsonObject("account").get("user").getAsString());
        accounts.setLoan(jsonObject.getAsJsonObject("account").get("loan").getAsString());
        accounts.setAmount(jsonObject.getAsJsonObject("account").get("amount").getAsInt());
        accountsRepository.save(accounts);
    }
    public String getAccounts(String user){
        JsonObject json = new Gson().fromJson(user,JsonObject.class);
        System.out.println(json+"---account");
        return new Gson().toJson(accountsRepository.findByUser(json.get("username").getAsString()));
    }
}
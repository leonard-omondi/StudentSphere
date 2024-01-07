package com.leonard;

import com.leonard.controller.Controller;
import com.leonard.dao.AccountDAO;
import com.leonard.dao.MessageDAO;
import com.leonard.model.Account;
import com.leonard.util.Database;

public class App {
    public static void main(String[] args) {

        Database.setup();
        Controller controller = new Controller();
        controller.setup();

    }
}

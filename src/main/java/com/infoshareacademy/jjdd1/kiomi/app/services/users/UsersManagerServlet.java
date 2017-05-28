package com.infoshareacademy.jjdd1.kiomi.app.services.users;

import com.google.common.base.Strings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by marcin on 28.05.17.
 */


@WebServlet(urlPatterns = "/usermanager")
public class UsersManagerServlet extends HttpServlet {

    UsersPersist users = new UsersPersist();
    UsersList newUser = new UsersList();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UsersList> usersLists = users.getAllUsers();
        req.setAttribute("usersLists", usersLists);
        RequestDispatcher dispatcher = req.getRequestDispatcher("//TODO");
        dispatcher.forward(req,resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean userFirstNameIsEmpty;
        boolean userLastNameIsEmpty;
        boolean userEmailIsEmpty;
        boolean emailOfUserToRemoveIsEmpty;


        String userFirstName = req.getParameter("userFirstName");
        String userLastName = req.getParameter("userLastName");
        String userEmail = req.getParameter("userEmail");
        String emailOfUserToRemove = req.getParameter("emailOfUserToRemove");

        userFirstNameIsEmpty = (Strings.isNullOrEmpty(userFirstName));
        userLastNameIsEmpty = (Strings.isNullOrEmpty(userLastName));
        userEmailIsEmpty = (Strings.isNullOrEmpty(userEmail));
        emailOfUserToRemoveIsEmpty = (Strings.isNullOrEmpty(emailOfUserToRemove));




        if (!(userFirstNameIsEmpty || userLastNameIsEmpty || userEmailIsEmpty) ) {

            newUser.setFirstname(userFirstName);
            newUser.setLastname(userLastName);
            newUser.setEmail(userEmail);
            newUser.setEntryDate(new Date());

            users.addUser(newUser);

        }

        if (!emailOfUserToRemoveIsEmpty) {

            users.removeUser(emailOfUserToRemove);
        }



        List<UsersList> currentUsersLists = users.getAllUsers();
        req.setAttribute("usersLists", currentUsersLists);
        RequestDispatcher dispatcher = req.getRequestDispatcher("//TODO");
        dispatcher.forward(req,resp);

    }
}

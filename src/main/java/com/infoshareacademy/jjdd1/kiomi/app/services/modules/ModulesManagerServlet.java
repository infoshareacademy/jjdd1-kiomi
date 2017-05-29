package com.infoshareacademy.jjdd1.kiomi.app.services.modules;

import com.google.common.base.Strings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by marcin on 28.05.17.
 */
@WebServlet(name = "ModulesManagerServlet")
public class ModulesManagerServlet extends HttpServlet {
    ModulesPersist modulesPersist = new ModulesPersist();
    Modules module = new Modules();




    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Modules> modulesList = modulesPersist.getAllModules();
        req.setAttribute("modulesList", modulesList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("//TODO");
        dispatcher.forward(req,resp);

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String moduleToAdd = req.getParameter("moduleToAdd");
        boolean statusOfModuleToAdd = Boolean.parseBoolean(req.getParameter("statusOfModuleToAdd"));
        String moduleToUpdate = req.getParameter("moduleToUpdate");
        boolean statusToUpdate = Boolean.parseBoolean(req.getParameter("statusToUpdate"));
        String moduleGetStatus = req.getParameter("moduleGetStatus");
        String moduleToDelete = req.getParameter("moduleToDelete");



        boolean moduleToAddIsempty;
        boolean moduleToUpdateIsempty;
        boolean moduleGetStatusIsempty;
        boolean moduleToDeleteIsempty;
        boolean currentStatusOfModule;


        moduleToAddIsempty = (Strings.isNullOrEmpty(moduleToAdd));
        moduleToUpdateIsempty = (Strings.isNullOrEmpty(moduleToUpdate));
        moduleGetStatusIsempty = (Strings.isNullOrEmpty(moduleGetStatus));
        moduleToDeleteIsempty = (Strings.isNullOrEmpty(moduleToDelete));

        if (!moduleToAddIsempty) {

            module.setModuleName(moduleToAdd);
            module.setStatus(statusOfModuleToAdd);
            modulesPersist.addModule(module);
        }

        if (!moduleToUpdateIsempty) {

            modulesPersist.updateModule(moduleToUpdate, statusToUpdate);

        }

        if (!moduleGetStatusIsempty) {

            currentStatusOfModule = modulesPersist.getStatusOfModule(moduleGetStatus);
            req.setAttribute("currentStatusOfModule", currentStatusOfModule);
        }

        if (!moduleToDeleteIsempty) {

            modulesPersist.deleteModule(moduleToDelete);

        }


        List<Modules> modulesList = modulesPersist.getAllModules();
        req.setAttribute("modulesList", modulesList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("//TODO");
        dispatcher.forward(req,resp);

    }

}

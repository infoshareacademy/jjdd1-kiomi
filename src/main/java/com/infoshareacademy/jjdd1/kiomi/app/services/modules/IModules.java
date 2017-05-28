package com.infoshareacademy.jjdd1.kiomi.app.services.modules;

import java.util.List;

/**
 * Created by marcin on 28.05.17.
 */
public interface IModules {

    void addModule(Modules module);

    void updateModule(String name, boolean status);

    boolean getStatusOfModule(String name);

    void deleteModule(String name);


    List<Modules> getAllModules();
}

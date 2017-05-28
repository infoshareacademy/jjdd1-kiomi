package com.infoshareacademy.jjdd1.kiomi.app.services.modules;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by marcin on 28.05.17.
 */

@Entity
@Table(name = "module")
public class Modules {

        @Id
        @GeneratedValue(generator = "increment")
        @GenericGenerator(name = "increment", strategy = "increment")
        private Long id;


        @Column(name = "module_name", length = 20)
        private String moduleName;

        @Column(name = "status")
        private boolean status;

    public Modules() {
    }

    public Long getId() {
        return id;
    }

    public Modules setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Modules setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Modules setStatus(boolean status) {
        this.status = status;
        return this;
    }
}



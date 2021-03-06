/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc.impl;

import java.io.Serializable;

/**
 * @author higa
 *
 */
public class Department implements Serializable {

    private static final long serialVersionUID = -1031433105487668130L;

    /**
     * 
     */
    public static final String TABLE = "DEPT";

    private int deptno;

    private String dname;

    private String loc;

    private int versionNo;

    private boolean active;

    /**
     * 
     */
    public Department() {
    }

    /**
     * @return
     */
    public int getDeptno() {
        return this.deptno;
    }

    /**
     * @param deptno
     */
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    /**
     * @return
     */
    public java.lang.String getDname() {
        return this.dname;
    }

    /**
     * @param dname
     */
    public void setDname(java.lang.String dname) {
        this.dname = dname;
    }

    /**
     * @return
     */
    public java.lang.String getLoc() {
        return this.loc;
    }

    /**
     * @param loc
     */
    public void setLoc(java.lang.String loc) {
        this.loc = loc;
    }

    /**
     * @return
     */
    public int getVersionNo() {
        return this.versionNo;
    }

    /**
     * @param versionNo
     */
    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Department))
            return false;
        Department castOther = (Department) other;
        return this.getDeptno() == castOther.getDeptno();
    }

    public int hashCode() {
        return this.getDeptno();
    }
}

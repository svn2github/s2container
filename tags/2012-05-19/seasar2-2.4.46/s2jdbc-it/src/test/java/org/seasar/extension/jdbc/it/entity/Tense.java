/*
 * Copyright 2004-2012 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.it.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author taedium
 * 
 */
@Entity
public class Tense {

    /** */
    @Id
    public int id;

    /** */
    @Temporal(TemporalType.DATE)
    public Date dateDate;

    /** */
    @Temporal(TemporalType.TIME)
    public Date dateTime;

    /** */
    @Temporal(TemporalType.TIMESTAMP)
    public Date dateTimestamp;

    /** */
    @Temporal(TemporalType.DATE)
    public Calendar calDate;

    /** */
    @Temporal(TemporalType.TIME)
    public Calendar calTime;

    /** */
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar calTimestamp;

    /** */
    public java.sql.Date sqlDate;

    /** */
    public Time sqlTime;

    /** */
    public Timestamp sqlTimestamp;
}

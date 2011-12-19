/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;


/**
 * Creates an event that will expire and issue a notification.
 * @author ieugen
 */
@Entity
public class Event extends Model{
    
    @Required
    @ManyToOne
    public EventType eventType;
    @Required
    public Date starts;
    @Required
    public Date expires;
    @ManyToOne
    public Car target;

    public Event(EventType eventType, Date starts, Date expires) {
        this.eventType = eventType;
        this.starts = starts;
        this.expires = expires;
    }

    @Override
    public String toString() {
        return eventType.toString();
    }
}
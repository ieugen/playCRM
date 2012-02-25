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

import com.google.common.base.Objects;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a schema mapping for a Car. We extend Model so we get all the goodies
 * that come with that.
 *
 * @author ieugen
 */
@Entity
public class Car extends Model {

    @Column(unique = true)
    public String plates;
    @Column(unique = true)
    public String serial;
    public String info;
    @ManyToOne
    public Customer owner;
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL)
    public List<Event> events;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    public List<CarFile> carFiles;
    @OneToOne
    public Insurance insurance;
    @OneToOne
    public Inspection inspection;

    public Car(String serial, String plates, String info, Customer owner) {
	this.serial = serial;
	this.info = info;
	this.plates = plates;
	this.owner = owner;
	this.events = new ArrayList<Event>();
	this.carFiles = new ArrayList<CarFile>();
    }

    @Override
    public int hashCode() {
	return Objects.hashCode(plates, serial);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Car) {
	    final Car other = (Car) obj;
	    return Objects.equal(plates, other.plates) &&
		   Objects.equal(serial, other.serial);
	} else {
	    return false;
	}
    }

    @Override
    public String toString() {
	return Objects.toStringHelper(this).add("plates", plates).
		add("serial", serial).toString();
    }
}

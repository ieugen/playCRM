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

import play.db.jpa.*;
import play.data.validation.*;

/**
 * Customer domain model.
 * @author ieugen
 */
@Entity
public class Customer extends Model {

    public String fullName;
    public String address;
    public String city;
    @ElementCollection
    public List<String> telephones;
    @Column(unique = true)
    public String ssn;
    @Lob
    public String info;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    public List<Car> cars;

    public Customer(String fullName, String address, String city, String ssn, String info) {
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.telephones = new ArrayList<String>();
        this.ssn = ssn;
        this.info = info;
        this.cars = new ArrayList<Car>();
    }

    public Customer addCar(String serial, String plates, String info) {
        Car newCar = new Car(serial, plates, info, this).save();
        this.cars.add(newCar);
        this.save();
        return this;
    }

    public Customer addTelephone(String telephone) {
        this.telephones.add(telephone);
        this.save();
        return this;
    }

    @Override
    public int hashCode() {
        return ssn.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if ((this.ssn == null) ? (other.ssn != null) : !this.ssn.equals(other.ssn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + id + " " + fullName + " " + ssn + " " + city + "]";
    }
}

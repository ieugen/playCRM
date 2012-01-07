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

import models.Car;
import models.Customer;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.List;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void createAndRetrieveCustomer() {
        Customer bob = new Customer("Bob", "Earth", "0720111222", "0720111333", "New York", "123456789", "a welcomed guy");
        bob.save();
        Customer customer = Customer.find("bySsn", "123456789").first();
        assertNotNull(customer);
        assertEquals("Bob", customer.fullName);
        assertTrue(customer.phone1.equals("0720111222"));
        assertTrue(customer.phone2.equals("0720111333"));
    }

    @Test
    public void createCar() {
        Customer bob = new Customer("Bob", "Earth", "0720111222", "0720111333", "New York", "123456789", "a welcomed guy").save();
        new Car("123", "ph-07-AAA", "white Mercedes", bob).save();

        assertEquals(1, Car.count());

        List<Car> bobCars = Car.find("byOwner", bob).fetch();
        assertEquals(1, bobCars.size());

        Car firstCar = bobCars.get(0);
        assertNotNull(bobCars);
        assertEquals(bob, firstCar.owner);
        assertEquals("123", firstCar.serial);
        assertEquals("ph-07-AAA", firstCar.plates);
        assertEquals("white Mercedes", firstCar.info);
    }

    @Test
    public void fullTest() {
        Fixtures.loadModels("data.yml");
        assertEquals(2, Customer.count());
        assertEquals(3, Car.count());
        Customer bob = Customer.find("byFullName", "Bob").first();
        Customer ioan = Customer.find("byFullName", "Ioan").first();
        assertNotNull(bob);
        assertNotNull(ioan);
        assertTrue(bob.phone1.equals("0720111222"));
        assertTrue(bob.phone2,equals("0720111333"));
        assertEquals(2, bob.cars.size());
        assertEquals(1, ioan.cars.size());
    }

}

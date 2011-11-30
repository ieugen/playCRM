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
package controllers;

import java.util.List;
import models.Customer;

import play.data.validation.*;
import play.mvc.*;

/**
 * Controller for customer related requests.
 * @author ieugen
 */
public class Customers extends Controller {

    public static void list() {
        List<Customer> customers = Customer.find("order by fullName asc").fetch();
        render(customers);
    }

    public static void show(Long id) {
        Customer customer = Customer.findById(id);
        render(customer);
    }

    public static void addCustomer(@Required String fullName,
            String address,
            String city,
            String ssn,
            String info) {
        Customer customer = new Customer(fullName, address, city, ssn, info).save();
        show(customer.getId());
    }
}
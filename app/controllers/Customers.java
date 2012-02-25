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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import models.Customer;
import models.CustomerFile;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
import play.mvc.With;

/**
 * Controller for customer related requests.
 *
 * @author ieugen
 */
@With(Secure.class)
public class Customers extends CRUD {

    public static void uploadFile(@Required Long id, @Required File customerFile) throws FileNotFoundException {
        Customer customer = Customer.findById(id);
        Blob blob = new Blob();
        blob.set(new FileInputStream(customerFile), MimeTypes.getContentType(customerFile.getName()));
        CustomerFile file1 = new CustomerFile(customerFile.getName(), blob);
        file1.owner = customer;
        customer.customerFiles.add(file1);
        file1.save();
        redirect(request.controller + ".show", Long.toString(id));
    }

    public static void listCustomers(){
	redirect(request.controller + ".list");
    }
}

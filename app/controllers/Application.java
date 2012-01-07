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

import models.Car;
import models.CarFile;
import models.CustomerFile;
import play.Logger;
import play.mvc.Controller;

/**
 * Controller for website index and misc methods.
 */
public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void getCarFile(long id) {
        CarFile file = CarFile.findById(id);
        Logger.info("Sending file: "+ file.toString());
        renderBinary(file.blob.get(),file.fileName, file.blob.type(),false);
    }

    public static void deleteCarFile(Long carId, Long id){
        Logger.info(request.controllerClass.toString());
        CarFile file = CarFile.findById(id);
        file.delete();
        redirect("Cars.show", Long.toString(carId));
    }

    public static void deleteCustomerFile(Long carId, Long id){
        Logger.info(request.controllerClass.toString());
        CustomerFile file = CustomerFile.findById(id);
        file.delete();
        redirect("Customers.show", Long.toString(carId));
    }

    public static void getCustomerFile(long id) {
        CustomerFile file = CustomerFile.findById(id);
        Logger.info("Sending file: "+ file.toString());
        renderBinary(file.blob.get(),file.fileName, file.blob.type(),false);
    }

}
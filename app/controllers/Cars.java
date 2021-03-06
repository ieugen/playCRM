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
import models.Car;
import models.CarFile;
import play.Logger;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.libs.MimeTypes;
import play.mvc.With;


/**
 * Controller for Car.
 *
 * @author ieugen
 */
@With(Secure.class)
public class Cars extends CRUD {

    public static void uploadFile(@Required Long id, @Required File file) throws FileNotFoundException {
        Logger.info("Uploading file :" + file.toString() + " " + MimeTypes.getContentType(file.getName()));
        Car car = Car.findById(id);
        Blob blob = new Blob();
        blob.set(new FileInputStream(file), MimeTypes.getContentType(file.getName()));
        CarFile carFile = new CarFile(file.getName(), blob);
        carFile.owner = car;
        car.carFiles.add(carFile);
        carFile.save();
        redirect(request.controller + ".show", Long.toString(id));
    }
}

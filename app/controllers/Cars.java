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
import models.Picture;
import play.Logger;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.libs.MimeTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Controller for Car.
 *
 * @author ieugen
 */
public class Cars extends CRUD {

    public static void uploadPicture(@Required Long carID, @Required File image) throws FileNotFoundException {
        /*long id = 0L;
        try {
            id  = params.get("carUID",Long.class);
        } catch (Exception e){
            Logger.warn("Missing ");
        }*/
        Car car = Car.findById(carID);
        Logger.info(car.toString());
        Blob imageBlob = new Blob();
        imageBlob.set(new FileInputStream(image), MimeTypes.getContentType(image.getName()));
        Picture picture = new Picture(image.getName(), imageBlob);
        car.carPictures.add(picture);
        picture.save();
        Picture.createThumbnail(picture.image.getFile());
        System.out.println(Picture.count());
        System.out.println(Picture.all().fetch());
        redirect(request.controller + ".show", Long.toString(carID));
    }


}

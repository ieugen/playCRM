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
import models.CarPicture;
import models.CustomerPicture;
import models.Picture;
import play.Logger;
import play.mvc.Controller;

public class Application extends Controller {


    public static void index() {
        Car c = Car.findById(1L);
        Logger.info("Info for car with id 1" + c.toString());
        for (Picture p: c.carPictures){
            Logger.info("Pictures "+ p.toString());
        }
        render();
    }

    public static void getCarPicture(long id) {
        CarPicture picture = CarPicture.findById(id);
        Logger.info("Sending picture: "+ picture.toString());
        response.setContentTypeIfNotSet(picture.image.type());
        renderBinary(picture.image.get());
    }

    public static void getCarPictureThumbnail(long id) {
        CarPicture picture = CarPicture.findById(id);
        Logger.info("Sending thumbnail: "+ picture.toString());
        response.setContentTypeIfNotSet(picture.image.type());
        renderBinary(Utils.thumbnailFile(picture.image.getFile()));
    }

    public static void getCustomerPicture(long id) {
        CustomerPicture picture = CustomerPicture.findById(id);
        Logger.info("Sending picture: "+ picture.toString());
        response.setContentTypeIfNotSet(picture.image.type());
        renderBinary(picture.image.get());
    }

    public static void getCustomerPictureThumbnail(long id){
        CustomerPicture picture = CustomerPicture.findById(id);
        Logger.info("Sending thumbnail: "+ picture.toString());
        response.setContentTypeIfNotSet(picture.image.type());
        renderBinary(Utils.thumbnailFile(picture.image.getFile()));
    }

}
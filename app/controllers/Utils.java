package controllers; /******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one                 *
 * or more contributor license agreements.  See the NOTICE file               *
 * distributed with this work for additional information                      *
 * regarding copyright ownership.  The ASF licenses this file                 *
 * to you under the Apache License, Version 2.0 (the                          *
 * "License"); you may not use this file except in compliance                 *
 * with the License.  You may obtain a copy of the License at                 *
 *                                                                            *
 *   http://www.apache.org/licenses/LICENSE-2.0                               *
 *                                                                            *
 * Unless required by applicable law or agreed to in writing,                 *
 * software distributed under the License is distributed on an                *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                     *
 * KIND, either express or implied.  See the License for the                  *
 * specific language governing permissions and limitations                    *
 * under the License.                                                         *
 ******************************************************************************/

import net.coobird.thumbnailator.Thumbnails;
import play.Logger;
import play.Play;
import play.libs.Files;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Make me change this description.
 * User: ieugen
 * Date: 26.12.2011
 * Time: 13:56
 */
public class Utils {

    public static final String THUMBNAIL_PREFIX = "thumbs_";

    /**
     * Create e thumbnail for a given file. Does not check if file is an image..
     *
     * @param image the image for which you wish to create a thumbnail.
     */
    public static void generateThumbnail(File image) {
        File thumbnail = thumbnailFile(image);
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(thumbnail));
            Thumbnails.of(image)
                    .size(160, 160)
                    .toOutputStream(out);
        } catch (IOException e) {
            Logger.info("Exception creating thumbnail for image {}", thumbnail.toString());
        }
    }

    /**
     * Generate a thumbnail file for a given picture.
     * Linux path separator (/) is assumed.
     * @param image
     * @return
     */
    public static File thumbnailFile(File image) {
        return new File(image.getParent() + "/" + THUMBNAIL_PREFIX + image.getName());
    }

}

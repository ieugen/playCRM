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

import net.coobird.thumbnailator.Thumbnails;
import play.Logger;
import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Model for pictures.
 *
 * @author ieugen
 */
@Entity
public class Picture extends Model {

    public String pictureName;
    @CheckWith(PictureCheck.class)
    public Blob image;

    public Picture(String pictureName, Blob image) {
        this.pictureName = pictureName;
        this.image = image;
    }

    @Override
    public String toString() {
        return pictureName + "\t" + image.length() / 1024 + " KB";
    }

    public static void createThumbnail(File image) {
        String name = image.getParent() + "/thumb-" + image.getName();
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(new File(name)));
            Thumbnails.of(image)
                    .size(160, 160)
                    .toOutputStream(out);
        } catch (IOException e) {
            Logger.info("Exception creating thumbnail for image {}", name);
        }
    }

    static class PictureCheck extends Check {

        public final static int MAX_SIZE = 4048;
        public final static int MAX_HEIGHT = 1920;
        public final static int MAX_WIDTH = 1080;

        @Override
        public boolean isSatisfied(Object parent, Object image) {
            if (!(image instanceof Blob)) {
                return false;
            }

            if (!((Blob) image).type().equals("image/jpeg") && !((Blob) image).type().equals("image/png")) {
                return false;
            }

            // size check
            if (((Blob) image).getFile().length() > MAX_SIZE) {
                return false;
            }


            try {
                BufferedImage source = ImageIO.read(((Blob) image).getFile());
                int width = source.getWidth();
                int height = source.getHeight();

                if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                    return false;
                }
            } catch (IOException exption) {
                return false;
            }
            return true;
        }
    }
}

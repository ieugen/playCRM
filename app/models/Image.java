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
 * For representing an image.
 * 
 */
@Entity
public class Image extends Model {

    public String fileName;
    public String description;
    @Lob
    public byte[] content;

    public Image(String fileName, String description, byte[] content) {
        this.fileName = fileName;
        this.description = description;
        this.content = content;
    }   
    
    @Override
    public int hashCode() {
        return fileName.hashCode() * description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Image other = (Image) obj;
        if ((this.fileName == null) ? (other.fileName != null) : !this.fileName.equals(other.fileName)) {
            return false;
        }
        if ((this.content == null) ? (other.content != null) : (!(this.content.length == other.content.length))) {
            return false;
        }
        return true;
    }
}

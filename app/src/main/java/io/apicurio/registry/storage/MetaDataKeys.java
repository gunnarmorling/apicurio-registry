/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.registry.storage;

import io.apicurio.registry.types.ArtifactType;

import java.util.Date;
import java.util.Map;

/**
 * @author Ales Justin
 */
public class MetaDataKeys {
    public static String ARTIFACT_ID = "artifact_id";
    public static String CONTENT = "content"; // TODO discuss
    public static String GLOBAL_ID = "global_id";
    public static String VERSION = "version";
    public static String NAME = "name";
    public static String TYPE = "type";
    public static String DESCRIPTION = "description";
    public static String CREATED_BY = "createdBy";
    public static String CREATED_ON = "createdOn";
    public static String MODIFIED_BY = "modifiedBy";
    public static String MODIFIED_ON = "modifiedOn";

    // Internal

    public static String DELETED = "_deleted";

    // Helpers

    public static ArtifactMetaDataDto toArtifactMetaData(Map<String, String> content) {
        ArtifactMetaDataDto dto = new ArtifactMetaDataDto();

        dto.setId(content.get(ARTIFACT_ID));

        String createdOn = content.get(CREATED_ON);
        String modifiedOn = content.get(MODIFIED_ON);
        
        dto.setCreatedBy(content.get(CREATED_BY));
        if (createdOn != null) {
            dto.setCreatedOn(new Date(Long.parseLong(createdOn))); // TODO discuss
        }
        dto.setModifiedBy(content.get(MODIFIED_BY));
        if (modifiedOn != null) {
            dto.setModifiedOn(new Date(Long.parseLong(modifiedOn))); // TODO discuss
        }
        dto.setDescription(content.get(DESCRIPTION));
        dto.setName(content.get(NAME));
        dto.setType(ArtifactType.fromValue(content.get(TYPE))); // TODO null check
        dto.setVersion(Integer.parseInt(content.get(VERSION)));
        dto.setGlobalId(Long.parseLong(content.get(GLOBAL_ID)));
        return dto;
    }

    public static ArtifactVersionMetaDataDto toArtifactVersionMetaData(Map<String, String> content) {
        ArtifactVersionMetaDataDto dto = new ArtifactVersionMetaDataDto();
        String createdOn = content.get(CREATED_ON);
        if (createdOn != null) {
            dto.setCreatedOn(new Date(Long.parseLong(createdOn))); // TODO discuss
        }
        dto.setCreatedBy(content.get(CREATED_BY));
        dto.setDescription(content.get(DESCRIPTION));
        dto.setName(content.get(NAME));
        dto.setType(ArtifactType.fromValue(content.get(TYPE)));
        dto.setVersion(Integer.parseInt(content.get(VERSION)));
        dto.setGlobalId(Long.parseLong(content.get(GLOBAL_ID)));
        return dto;
    }

    public static String toContent(Map<String, String> cMap) {
        return cMap.get(CONTENT);
    }
}

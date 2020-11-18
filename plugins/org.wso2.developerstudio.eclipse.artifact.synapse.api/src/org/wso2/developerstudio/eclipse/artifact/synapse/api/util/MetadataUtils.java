package org.wso2.developerstudio.eclipse.artifact.synapse.api.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.wso2.developerstudio.eclipse.artifact.synapse.api.model.APIArtifactModel;


public class MetadataUtils {
    public static void createMedataFile(IContainer metadataLocation, APIArtifactModel artifactModel)
            throws IOException {

        IFile swaggerFile = metadataLocation.getFile(new Path(artifactModel.getName() + "_metadata.yaml"));

        File newFile = new File(swaggerFile.getLocationURI().getPath());
        if (!newFile.exists()) {
            try (FileWriter fw = new FileWriter(newFile);) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date now = new Date();
                String strDate = sdf.format(now);
                StringBuilder builder = new StringBuilder();
                
                // Creating the YAML file
                builder.append("---\n");
                builder.append("id: \"id1\"\n");
                builder.append("name : \"").append(artifactModel.getName()).append("\"\n");
                builder.append("displayName : \"").append(artifactModel.getName()).append("\"\n");
                builder.append("description: \"Sample API\"\n");
                builder.append("version: \"1.0.0\"\n");
                builder.append("serviceUrl: \"http://localhost:8290/").append(artifactModel.getName()).append("\"\n");
                builder.append("securityType: \"BASIC\"\n");
                builder.append("mutualSSLEnabled: false\n");
                builder.append("usage: 1\n");
                builder.append("createdTime: \"").append(strDate).append("\"\n");
                builder.append("lastUpdatedTime: \"").append(strDate).append("\"\n");
                builder.append("etag: \"32c890312cfadc94a7c1153f65a4f100\"");
                fw.write(builder.toString());
            }
        }
    }

}

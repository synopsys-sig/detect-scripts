/*
 * synopsys-detect-scripts
 *
 * Copyright (c) 2024 Black Duck Software, Inc.
 *
 * Use subject to the terms and conditions of the Black Duck Software End User Software License and Maintenance Agreement. All rights reserved worldwide.
 */
package com.blackduck.integration.detect.scripts;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

import com.blackduck.integration.detect.scripts.scripts.ScriptBuilder;
import com.synopsys.integration.exception.IntegrationException;

import freemarker.template.TemplateException;

public class Application {
    public static void main(final String[] args) throws IOException, IntegrationException, TemplateException, URISyntaxException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide two arguments, the operation and the output directory of the scripts.");
        }
        final String operation = args[0];
        final String outputDirectoryPath = args[1];
        final File outputDirectory = new File(outputDirectoryPath);
        outputDirectory.mkdirs();

         if (operation.equals("scripts")) {
            ScriptBuilder scriptBuilder = new ScriptBuilder();
            scriptBuilder.generateScripts(outputDirectory);
        } else if (operation.equals("cert")) {
            final File certSource = new File("src/main/resources/jar_verification.crt");
            final File certDestination = new File(outputDirectory, "jar_verification.crt");
            FileUtils.copyFile(certSource, certDestination);
        } else {
            throw new IllegalArgumentException("Unknown operation. Must be 'scripts' or 'certs': " + operation);
        }

    }
}

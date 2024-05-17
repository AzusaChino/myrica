package jp.haru.myrica.support;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import jp.haru.myrica.consts.CommonConstants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Helper (deploy so files to current directory)
 */
public final class JniDeploy {

    public static void deploy() throws IOException {
        File libFolder = new File(".");
        File helloLib = new File(libFolder.getPath(), CommonConstants.LIB_MYRICA);
        if (!helloLib.exists()) {
            ClassPathResource cpr = new ClassPathResource(CommonConstants.LIB_MYRICA);
            try (InputStream is = cpr.getInputStream()) {
                FileUtils.copyInputStreamToFile(is, helloLib);
            }
        }
    }
}

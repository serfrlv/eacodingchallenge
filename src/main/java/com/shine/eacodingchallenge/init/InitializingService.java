package com.shine.eacodingchallenge.init;

import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

public class InitializingService implements InitializingBean, Serializable {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("======init======");
        Runnable r = ()->{System.out.println("");};
        new Thread(r).start();
        File file = new File("");
        try (InputStream is = new FileInputStream(file)) {

        }finally{

        }

    }


}

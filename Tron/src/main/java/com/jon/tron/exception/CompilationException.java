package com.jon.tron.exception;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 14.09.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class CompilationException extends RuntimeException {

    public Map.Entry<String, String> getResult() {
        return null;
    }
}
